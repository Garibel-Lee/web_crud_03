package com._520it.smis.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;

import com._520it.smis.dao.IStudentDAO;
import com._520it.smis.dao.impl.StudentDAOImpl;
import com._520it.smis.domain.Student;

@WebServlet("/student/list")
public class ListStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;
	
	public void init() throws ServletException {
		dao=new StudentDAOImpl();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1接受请求参数，封装对象
		
		//2调用业务方法处理请求
		List<Student> list= dao.listall();

		//3控制页面跳转  servlet不擅长界面输出  重定向无法进入web-inf  只能用请求转发
		req.setAttribute("students",list);	//把结果共享list.jsp
		req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
	}
		
}
