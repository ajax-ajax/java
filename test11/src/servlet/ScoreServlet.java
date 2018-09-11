package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.ProjectDao;
import dao.ScoreDao;
import dao.DepartmentDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import entity.Department;
import util.Constant;
import util.Panigation;



public class ScoreServlet extends HttpServlet {
	private static final String path="WEB-INF/score/";
	
	DepartmentDao depDao = new DepartmentDao();
	List<Department> depList = new ArrayList<>();
	ProjectDao proDao = new ProjectDao();
	List<Project> proList = new ArrayList<>();
	ScoreDao scDao=new ScoreDao();
	List<Score> scList = new ArrayList<>();
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
		} else if ("input".equals(type)) {
			input(request, response);
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
		proList=proDao.serch();
		request.setAttribute("proList", proList);
		
        String name="";
        if(request.getParameter("name")!=null) {
        	name=request.getParameter("name");
        }
        
        int depId=-1;
        if(request.getParameter("depId")!=null&&!"".equals(request.getParameter("depId"))) {
       	  depId=Integer.parseInt(request.getParameter("depId"));
        }
        int proId=-1;
        if(request.getParameter("proId")!=null&&!"".equals(request.getParameter("proId"))) {
       	  proId=Integer.parseInt(request.getParameter("proId"));
        }
        int value=-1;
        if(request.getParameter("value")!=null&&!"".equals(request.getParameter("value"))) {
       	  value=Integer.parseInt(request.getParameter("value"));
        }
        String grade="";
        if(request.getParameter("grade")!=null) {
        	grade=request.getParameter("grade");
        }
        
        
     
	 Score condition=new Score();
	 Employee emp=new Employee();
	 emp.setName(name);
	 Department dep=new Department();
	 dep.setId(depId);
	 emp.setDep(dep);
	 condition.setEmp(emp);
	 Project pro=new Project();
	 pro.setId(proId);
	 condition.setPro(pro);
	 condition.setValue(value);
	 condition.setGrade(grade);  
  
		int curPage=1;
		  int count = scDao.searchByCountCondition(condition);
		if (request.getParameter("ys") != null) {
			curPage = Integer.parseInt(request.getParameter("ys"));		
		} 
	 
	   Panigation p=new Panigation(curPage,count,Constant.NUM_IN_PAGE,Constant.NUM_OF_PAGE);
     scList = scDao.searchByCondition(condition,p.getM(),Constant.NUM_IN_PAGE);
     
     request.setAttribute("scList", scList);
     request.setAttribute("condition", condition);
     request.setAttribute("p", p);
     
  

	try {
		request.getRequestDispatcher(path+"score.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void input(HttpServletRequest request, HttpServletResponse response) {
	try {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		boolean flag = false;
		int id=Integer.parseInt(request.getParameter("id"));
		int value = Integer.parseInt(request.getParameter("value"));
		
		int e_id=Integer.parseInt(request.getParameter("e_id"));
		int p_id=Integer.parseInt(request.getParameter("p_id"));
		Score sc=new Score();
		  Employee emp=new Employee();
		    Project pro=new Project();
		    emp.setId(e_id);
		    pro.setId(p_id);
		    sc.setEmp(emp);
		    sc.setPro(pro);
		    sc.setValue(value);
		if(id==0) {
		 id= scDao.add(sc);
		 if(id>0) {
			 flag=true;
		 }
	    }else {
	    	sc.setId(id);
	    	flag=scDao.update(sc);
	    }
		Score score=scDao.serch(id);
		if(flag) {
			JSON json=JSONObject.fromObject(score);
			out.print(json);
		}else {
			out.print(false);
		}
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
