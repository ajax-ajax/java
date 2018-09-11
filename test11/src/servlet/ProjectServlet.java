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
import dao.ProManagerDao;
import dao.ProjectDao;
import entity.Department;
import entity.Project;
import util.Constant;
import util.Panigation;

public class ProjectServlet extends HttpServlet {
	private static final String path="WEB-INF/project/";
	List<Project> list = new ArrayList<>();
	ProjectDao proDao = new ProjectDao();
	List<Project> proList = new ArrayList<>();
	ProManagerDao promaDao =new ProManagerDao();
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
		}else if ("add".equals(type)) {
			add(request, response);
		} else if ("delete".equals(type)) {
			delete(request, response);
		} else if ("showChange".equals(type)) {
			showChange(request, response);
		} else if ("change".equals(type)) {
			change(request, response);
		} else if ("showByDep".equals(type)) {
			showByDep(request, response);
		} else if ("deleteByDep".equals(type)) {
			deleteByDep(request, response);
		}else if ("addByDep".equals(type)) {
			addByDep(request, response);
		}else if ("showByDep2".equals(type)) {
			showByDep2(request, response);
		} else if ("addByDep2".equals(type)) {
			addByDep2(request, response);
		}else if ("deleteByDep2".equals(type)) {
			deleteByDep2(request, response);
		}else if ("showByDep3".equals(type)) {
			showByDep3(request, response);
		}else if ("addByDep3".equals(type)) {
			addByDep3(request, response);
		}else if ("deleteByDep3".equals(type)) {
			deleteByDep3(request, response);
		}
	}
	public void showByCondition(HttpServletRequest request, HttpServletResponse response) {
		proList=proDao.serch();
		request.setAttribute("proList", proList);
        String name=request.getParameter("name");
	  Project condition=new Project();
	  condition.setName(name);
		int curPage=1;
		  int count = proDao.searchCountByCondition(condition);
		if (request.getParameter("ys") != null) {
			curPage = Integer.parseInt(request.getParameter("ys"));		
		}  
	   Panigation p=new Panigation(curPage,count,Constant.NUM_IN_PAGE,Constant.NUM_OF_PAGE);
     list = proDao.searchByCondition(condition,p.getM(),Constant.NUM_IN_PAGE);
     request.setAttribute("pros", list);
     request.setAttribute("p", p);
     
  

	try {
		request.getRequestDispatcher(path+"pros.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	public void showByDep(HttpServletRequest request, HttpServletResponse response) {
		
		
		int d_id=Integer.parseInt(request.getParameter("d_id"));
		
		
		DepartmentDao depDao=new DepartmentDao();
		Department dep=depDao.searchById(d_id);
		String dName=dep.getName();
		
	
	 
	 
        list = promaDao.proSearch(d_id);
        
       
		proList=promaDao.proByDid(d_id);
		request.setAttribute("proList", proList);
     
     request.setAttribute("pros", list);
     request.setAttribute("d_id", d_id);
 
     request.setAttribute("dName", dName);
  

	try {
		request.getRequestDispatcher(path+"pros2.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public void showByDep2(HttpServletRequest request, HttpServletResponse response) {
		
		
		int d_id=Integer.parseInt(request.getParameter("d_id"));
		
		
		DepartmentDao depDao=new DepartmentDao();
		Department dep=depDao.searchById(d_id);
		String dName=dep.getName();
		
	
	 
	 
        list = promaDao.proSearch(d_id);
        
       
		proList=promaDao.proByDid(d_id);
		request.setAttribute("proList", proList);
     
     request.setAttribute("pros", list);
     request.setAttribute("d_id", d_id);
 
     request.setAttribute("dName", dName);
  

	try {
		request.getRequestDispatcher(path+"pros3.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public void showByDep3(HttpServletRequest request, HttpServletResponse response) {
	
	
	int d_id=Integer.parseInt(request.getParameter("d_id"));
	
	
	DepartmentDao depDao=new DepartmentDao();
	Department dep=depDao.searchById(d_id);
	String dName=dep.getName();
	

 
 
    list = promaDao.proSearch(d_id);
    
   
	proList=promaDao.proByDid(d_id);
	request.setAttribute("proList", proList);
 
 request.setAttribute("pros", list);
 request.setAttribute("d_id", d_id);

 request.setAttribute("dName", dName);


try {
	request.getRequestDispatcher(path+"pros4.jsp").forward(request, response);
} catch (ServletException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
}
}
public void showAdd(HttpServletRequest request, HttpServletResponse response) {

	try {
		request.getRequestDispatcher(path+"proAdd.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public void showAddByDep(HttpServletRequest request, HttpServletResponse response) {
	int d_id=Integer.parseInt(request.getParameter("d_id"));
	request.setAttribute("d_id", d_id);
	try {
		request.getRequestDispatcher(path+"proAdd.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public void showChange(HttpServletRequest request, HttpServletResponse response) {
	proList=proDao.serch();
	request.setAttribute("proList", proList);
	int id = Integer.parseInt(request.getParameter("id"));
	Project pro = proDao.searchById(id);
	request.setAttribute("pro", pro);

	try {
		request.getRequestDispatcher(path+"proChange.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void add(HttpServletRequest request, HttpServletResponse response) {

	String name = request.getParameter("name");
	Project pro = new Project();
	pro.setName(name);

	ProjectDao proDao = new ProjectDao();

	boolean f = proDao.add(pro);

	if (f) {
		try {
			response.sendRedirect("pros");
			// request.setAttribute("pros", list);
			// request.getRequestDispatcher("pros.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public void addByDep(HttpServletRequest request, HttpServletResponse response) {
	
	
	int  d_id=Integer.parseInt(request.getParameter("d_id"));
	
	int p_id=Integer.parseInt(request.getParameter("p_id"));
	   promaDao.addpro(d_id, p_id);
	 
      

	
		try {
			response.sendRedirect("pros?type=showByDep&d_id="+d_id+"");
			// request.setAttribute("pros", list);
			// request.getRequestDispatcher("pros.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
public void addByDep2(HttpServletRequest request, HttpServletResponse response) {
	
	try {
		PrintWriter out=response.getWriter();
		int  d_id=Integer.parseInt(request.getParameter("d_id"));
		
		int p_id=Integer.parseInt(request.getParameter("p_id"));
		   boolean flag=promaDao.addpro(d_id, p_id);
	   if(flag) {
		out.print(flag);
	}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

  
		
	
}

public void addByDep3(HttpServletRequest request, HttpServletResponse response) {
	
	try {
		PrintWriter out=response.getWriter();
		int  d_id=Integer.parseInt(request.getParameter("d_id"));
		
		String p_ids = request.getParameter("p_ids");
		p_ids=p_ids.substring(0,p_ids.length()-1);
		System.out.println(p_ids);
		   boolean flag=promaDao.addproAll(d_id, p_ids);
	   if(flag) {
		out.print(flag);
	}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

}
public void delete(HttpServletRequest request, HttpServletResponse response) {
	int id = Integer.parseInt(request.getParameter("id"));

	boolean f = proDao.delete(id);
	
		try {
			response.sendRedirect("pros");
			// request.setAttribute("pros", list);
			// request.getRequestDispatcher("pros.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

public void deleteByDep2(HttpServletRequest request, HttpServletResponse response) {
	try {
		PrintWriter out=response.getWriter();
		int d_id = Integer.parseInt(request.getParameter("d_id"));
		
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		 boolean flag=promaDao.deletepro(d_id, p_id);
		 if(flag) {
				out.print(flag);
		 }
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
		
}

public void deleteByDep3(HttpServletRequest request, HttpServletResponse response) {
	try {
		PrintWriter out=response.getWriter();
		int d_id = Integer.parseInt(request.getParameter("d_id"));
		
		String p_ids = request.getParameter("p_ids");
		p_ids=p_ids.substring(0,p_ids.length()-1);
		 boolean flag=promaDao.deleteproAll(d_id, p_ids);
		 if(flag) {
				out.print(flag);
		 }
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
		
}
public void deleteByDep(HttpServletRequest request, HttpServletResponse response) {
	
	int d_id = Integer.parseInt(request.getParameter("d_id"));
	
	int p_id = Integer.parseInt(request.getParameter("p_id"));
	 promaDao.deletepro(d_id, p_id);
	
		try {
			response.sendRedirect("pros?type=showByDep&d_id="+d_id+"");
			// request.setAttribute("pros", list);
			// request.getRequestDispatcher("pros.jsp").forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
public void change(HttpServletRequest request, HttpServletResponse response) {
	int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");



	Project pro = new Project();
	pro.setName(name);

	
	pro.setId(id);
	

	boolean f = proDao.chag(pro);

	if (f) {
		try {
			response.sendRedirect("pros");
			// request.setAttribute("pros", list);
			// request.getRequestDispatcher("pros.jsp").forward(request,response);
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

