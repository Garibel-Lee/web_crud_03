package com._520it.smis.until;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUntil {
	private static DataSource ds =null;
	
	static {
		try {
			//加载读取资源文件
			Properties properties=new Properties();			
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			ds=DruidDataSourceFactory.createDataSource(properties);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//返回链接
	public static Connection getConn() {
		try {
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	//释放资源 还给连接池
	public static void close(Connection conn,Statement st,ResultSet rs ) {
		try {
			if(rs!=null) 
				rs.close();
		} catch (Exception e) {
		}finally {
			try {
				if(st!=null)
					st.close();
			} catch (Exception e) {
			}finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
