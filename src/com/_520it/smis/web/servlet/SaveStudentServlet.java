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

@WebServlet("/student/save")
public class SaveStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IStudentDAO dao;
	
	public void init() throws ServletException {
		dao=new StudentDAOImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//1接受请求参数，封装对象   直对post有效果
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		
		Student stu=new Student(name, Integer.valueOf(age));
		//2调用业务方法处理请求	
		String id=req.getParameter("id");
		System.out.println("我的id"+id);
		if(hasLength(id)){
			dao.update(Long.valueOf(id), stu);
		}else {
			System.out.println("Cuowu ");
			dao.save(stu);
		}
		
		//3控制页面跳转  servlet不擅长界面输出  重定向无法进入web-inf  只能用请求转发
		resp.sendRedirect("/student/list");
	}

	private boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
		
}
