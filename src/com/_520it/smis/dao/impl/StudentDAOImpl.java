package com._520it.smis.dao.impl;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com._520it.smis.dao.IStudentDAO;
import com._520it.smis.domain.Student;
import com._520it.smis.handler.BeanHandler;
import com._520it.smis.handler.BeanlistHandler;
import com._520it.smis.until.JdbcTemplate;


public class StudentDAOImpl implements IStudentDAO {

	public void save(Student stu) {
		JdbcTemplate.update("insert into t_student (name,age) values (?,?)", stu.getName(),stu.getAge());
	}

	public void delete(Long id) {
		JdbcTemplate.update("delete from t_student where id = ?", id);
	}

	public void update(Long id, Student stu) {
		JdbcTemplate.update("update t_student SET  name= ? , age= ? where id = ?",stu.getName(),stu.getAge(),id);
	}

	public Student get(Long id) {
		//List<Student> list= JdbcTemplate.query("select * from t_student where id = ?",new StudentResultSetHandler(),id);
		//return list.size()==1?list.get(0):null;
		return  JdbcTemplate.query("select * from t_student where id = ?",new BeanHandler<>(Student.class),id);
	}

	public List<Student> listall() {
	 //return JdbcTemplate.query("select * from t_student",new StudentResultSetHandler());
	return JdbcTemplate.query("select * from t_student",new BeanlistHandler<>(Student.class));
	}
}

//处理student代码把结果集合每一行数据封装成student对象
/*class StudentResultSetHandler implements IResultSetHandler<List<Student>>{
	public List<Student> handle(ResultSet rs) throws Exception {
		List<Student> list=new ArrayList<Student>();
		while (rs.next()) {
		Student st = new Student();
		list.add(st);
		st.setId(rs.getLong("id"));
		st.setAge(rs.getInt("age"));
		st.setName(rs.getString("name"));
	}
		return list;
	}
	
}*/
