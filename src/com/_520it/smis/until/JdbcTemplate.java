package com._520it.smis.until;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com._520it.smis.handler.IResultSetHandler;

public class JdbcTemplate {
	/**
	 * 查询DQL模板
	 * 查询多个学生，返回一个list<student>
	 * 查询一个学生，返回长度为一的list
	 * @param sql
	 * @param params
	 * @return	返回list集合
	 * @throws Exception 
	 */
	public static <T>T query(String sql,IResultSetHandler<T> rsh,Object... params ){
 		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			conn=JdbcUntil.getConn();
			ps = conn.prepareStatement(sql);
			for(int index=0; index<params.length;index++) {
				ps.setObject(index+1, params[index]);
			}
			rs=ps.executeQuery();
			/*while (rs.next()) {
				Student st = new Student();
				list.add(st);
				st.setId(rs.getLong("id"));
				st.setAge(rs.getInt("age"));
				st.setName(rs.getString("name"));
			}*/
			return rsh.handle(rs);
		} catch ( Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUntil.close(conn, ps, rs);
		}
		throw new RuntimeException("查询操作有误");
	}

	/**
	 * DML操作模板 增删改
	 * @param sql		DML操作的SQL模板带有占位符
	 * @param params	SQL？对应参数值
	 * @return 			受影响行数
	 */
	public static int update(String sql,Object... params ) {
		Connection conn = null;
		PreparedStatement ps=null ;
		try {
			conn=JdbcUntil.getConn();//贾琏
			ps=conn.prepareStatement(sql);//欲
			for(int index=0; index<params.length;index++) {
				System.out.println("DML操作："+params[index]);
				ps.setObject(index+1,params[index]);
			}
			return ps.executeUpdate();			
		} catch (Exception e) {
		}finally {
			JdbcUntil.close(conn, ps, null);
		}
		return 0;
	}
}
