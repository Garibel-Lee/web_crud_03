package com._520it.smis.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._520it.smis.dao.IStudentDAO;
import com._520it.smis.dao.impl.StudentDAOImpl;
import com._520it.smis.domain.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private IStudentDAO dao;
	
	public void init() throws ServletException {
		dao=new StudentDAOImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cmd=req.getParameter("cmd");
		if("save".equals(cmd)) {
			this.saveorupdate(req, resp);
		}else if ("edit".equals(cmd)) {
			this.edit(req, resp);
		}else if ("delete".equals(cmd)) {
			this.delete(req, resp);
		}else {
			this.list(req, resp);
		}
	}
	
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1接受请求参数，封装对象
		
				//2调用业务方法处理请求
				List<Student> list= dao.listall();
				//3控制页面跳转  servlet不擅长界面输出  重定向无法进入web-inf  只能用请求转发
				req.setAttribute("students",list);	//把结果共享list.jsp
				req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		dao.delete(id);
		resp.sendRedirect("/student");
	}
	protected void saveorupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		resp.sendRedirect("/student");
	}


}
