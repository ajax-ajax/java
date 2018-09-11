package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.DepartmentDao;
import entity.Department;
import entity.Department;
import util.Constant;
import util.Panigation;



public class DepartmentServlet extends HttpServlet {
	private static final String path="WEB-INF/department/";
	List<Department> list = new ArrayList<>();
	DepartmentDao depDao = new DepartmentDao();
	List<Department> depList = new ArrayList<>();
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type == null) {
			showByCondition(request, response);
		} else if ("showAdd".equals(type)) {
			showAdd(request, response);
		} else if ("add".equals(type)) {
			add(request, response);
		} else if ("delete".equals(type)) {
			delete(request, response);
		} else if ("showChange".equals(type)) {
			showChange(request, response);
		} else if ("change".equals(type)) {
			change(request, response);
		}  else if ("yselect".equals(type)) {
			showByCondition(request, response);
		}else if ("yselect1".equals(type)) {
			showByCondition(request, response);
		}else if ("yselect2".equals(type)) {
			showByCondition(request, response);
		}
	}
	public void showByCondition(HttpServletRequest request, HttpServletResponse response) {
		depList=depDao.serch();
		request.setAttribute("depList", depList);
        String name=request.getParameter("name");
        int emp_count=-1;
        if(request.getParameter("emp_count")!=null&&!"".equals(request.getParameter("emp_count"))) {
        	emp_count=Integer.parseInt(request.getParameter("emp_count"));
        }
	  Department condition=new Department();
	  condition.setName(name);
	  condition.setEmp_count(emp_count);
	  
		int curPage=1;
		  int count = depDao.searchCountByCondition(condition);
		if (request.getParameter("ys") != null) {
			curPage = Integer.parseInt(request.getParameter("ys"));		
		} 
	 
	   Panigation p=new Panigation(curPage,count,Constant.NUM_IN_PAGE,Constant.NUM_OF_PAGE);
     list = depDao.searchByCondition(condition,p.getM(),Constant.NUM_IN_PAGE);
     
     request.setAttribute("deps", list);
     request.setAttribute("condition", condition);
     request.setAttribute("p", p);
     
  

	try {
		request.getRequestDispatcher(path+"deps.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}


public void showAdd(HttpServletRequest request, HttpServletResponse response) {

	try {
		request.getRequestDispatcher(path+"depAdd.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void showChange(HttpServletRequest request, HttpServletResponse response) {
	depList=depDao.serch();
	request.setAttribute("depList", depList);
	int id = Integer.parseInt(request.getParameter("id"));
	Department dep = depDao.searchById(id);
	request.setAttribute("dep", dep);

	try {
		request.getRequestDispatcher(path+"depChange.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void add(HttpServletRequest request, HttpServletResponse response) {

	String name = request.getParameter("name");

	

	Department dep = new Department();
	dep.setName(name);
	

	
	DepartmentDao depDao = new DepartmentDao();

	boolean f = depDao.add(dep);

	if (f) {
		try {
			response.sendRedirect("deps");
			// request.setAttribute("deps", list);
			// request.getRequestDispatcher("deps.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public void delete(HttpServletRequest request, HttpServletResponse response) {
	int id = Integer.parseInt(request.getParameter("id"));

	boolean f = depDao.delete(id);
	
		try {
			response.sendRedirect("deps");
			// request.setAttribute("deps", list);
			// request.getRequestDispatcher("deps.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
public void change(HttpServletRequest request, HttpServletResponse response) {
	int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");



	Department dep = new Department();
	dep.setName(name);

	
	dep.setId(id);
	

	boolean f = depDao.chag(dep);

	if (f) {
		try {
			response.sendRedirect("deps");
			// request.setAttribute("deps", list);
			// request.getRequestDispatcher("deps.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
