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

@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;
	public void init() throws ServletException {
		dao=new StudentDAOImpl();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1接受请求参数，封装对象
		String sid= req.getParameter("id");
		//2调用业务方法处理请求	
		if(hasLength(sid)) {
			Student stu=dao.get(Long.valueOf(sid));
			req.setAttribute("student",stu);
		}
		//3控制页面跳转  servlet不擅长界面输出  重定向无法进入web-inf  只能用请求转发
		req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
	}
	private boolean hasLength(String str ) {
		return str!=null && !"".equals(str.trim());
		
	}
	
}
