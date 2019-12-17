package com._520it.smis.test;


import java.util.List;
import org.junit.Test;
import com._520it.smis.dao.IStudentDAO;
import com._520it.smis.dao.impl.StudentDAOImpl;
import com._520it.smis.domain.Student;


public class IStudentDAOTest {
	private  IStudentDAO dao=new StudentDAOImpl();

/*	@Test
	public void testSave() {
		 Student stu =new Student();
		 stu.setAge(12);
		 stu.setName("元ooo尊");
		 dao.save(stu);
	}
*/
/*	@Test
	public void testDelete() {
		dao.delete(37L);;
	}*/

	@Test
	public void testUpdate() {
		 Student stu =new Student();
		 stu.setAge(122);
		 stu.setName("郭靖");
		 dao.update(1L,stu);
	}

/*	@Test
	public void testGet() {
		Student student=dao.get(2L);
		System.out.println(student.toString());
	}*/

/*	@Test
	public void testListall() {
		List<Student> student = dao.listall();
		System.out.println(student.toString());
	}*/
	/*@Test
	public void testGetCount() throws Exception{
		String sql="Select COUNT(id) from t_student";
		Long total=JdbcTemplate.query(sql,new IResultSetHandler<Long>() {
			public Long handle(ResultSet rs) throws Exception {
				if(rs.next())
				return	rs.getLong(1);
				return 0l;
			}
		});
		System.out.println(total);
	}*/

}
