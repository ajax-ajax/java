package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDao;
import dao.DepartmentDao;
import entity.Department;
import entity.Department;
import util.Constant;
import util.Panigation;



public class IndexServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
	
		String type = request.getParameter("type");
		if (type == null) {
			index(request, response);
		} 
	}
	
	

	
	private void index(HttpServletRequest request, HttpServletResponse response){
	
		try {
			request.getRequestDispatcher("WEB-INF/index/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
