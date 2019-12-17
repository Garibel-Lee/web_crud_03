package com._520it.smis.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._520it.smis.dao.IStudentDAO;
import com._520it.smis.dao.impl.StudentDAOImpl;
@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;
	public void init() throws ServletException {
		dao=new StudentDAOImpl();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		dao.delete(id);
		resp.sendRedirect("/student/list");
	}

}
