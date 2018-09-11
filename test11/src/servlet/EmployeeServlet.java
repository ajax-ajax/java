package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import util.Constant;
import util.Panigation;

public class EmployeeServlet extends HttpServlet {
	
	private static final String path="WEB-INF/employee/";
	List<Employee> list = new ArrayList<>();
	EmployeeDao empDao = new EmployeeDao();
	List<Department> depList = new ArrayList<>();
	DepartmentDao depDao = new DepartmentDao();

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
		} else if ("deleteAll".equals(type)) {
			deleteAll(request, response);
		} else if ("showChangeAll".equals(type)) {
			showChangeAll(request, response);
		} else if ("showChangeAll2".equals(type)) {
			showChangeAll2(request, response);
		} else if ("changeAll".equals(type)) {
			changeAll(request, response);
		} else if ("changeAll2".equals(type)) {
			changeAll2(request, response);
		} else if ("changeAll3".equals(type)) {
			changeAll3(request, response);
		} else if ("yselect".equals(type)) {
			showByCondition(request, response);
		}else if ("yselect1".equals(type)) {
			showByCondition(request, response);
		}else if ("yselect2".equals(type)) {
			showByCondition(request, response);
		}else if ("showByCondition".equals(type)) {
			showByCondition(request, response);
		} else if ("showAdd2".equals(type)) {
			showAdd2(request, response);
		} else if ("upload".equals(type)) {
			upload(request, response);
		}else if ("add2".equals(type)) {
			add2(request, response);
		} else if ("deleteFile".equals(type)) {
			deleteFile(request, response);
		}   
	}

	private void deleteFile(HttpServletRequest request, HttpServletResponse response) {
		try {
	    boolean flag=false;
	    String str=request.getParameter("str");
	    String spath="F:/test1/"+str;
	    File file = new File(spath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    
		
	    PrintWriter out= response.getWriter();
		
		out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
	
	private void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path="F:/test1";
			
		FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		
		String pic="";
		for(int i=0;i<items.size();i++) {
		FileItem item=items.get(0);
		if(item.getFieldName().equals("myFile")) {
			UUID uuid = UUID.randomUUID();
			String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
			pic=uuid.toString() + houzhui;
			File savedFile = new File(path, pic);
	        item.write(savedFile);	
		}
		}
		PrintWriter out=response.getWriter();
		out.print(pic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void add2(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		Integer d_id=null;
		if(!"".equals(request.getParameter("depName"))) {
		d_id = Integer.parseInt(request.getParameter("depName"));
		}
		String pic[] = request.getParameterValues("pic");
	
		for(int i=0;i<pic.length;i++) {
		Employee emp = new Employee();
		Department dep=new Department();
		dep.setId(d_id);
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(age);
		emp.setDep(dep);
		emp.setPic(pic[i]);
		EmployeeDao empDao = new EmployeeDao();
		
		empDao.add(emp);
		}
		
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void showByCondition(HttpServletRequest request, HttpServletResponse response) {
		     depList=depDao.serch();
             String name=request.getParameter("name");
             
             String sex=request.getParameter("sex");
             int d_id=-1;
             if(request.getParameter("depName")!=null&&!"".equals(request.getParameter("depName"))) {
            	  d_id=Integer.parseInt(request.getParameter("depName"));
             }
           
             
             int age=-1;
             if(request.getParameter("age")!=null&&!"".equals(request.getParameter("age"))) {
            	 age=Integer.parseInt(request.getParameter("age"));
             }
		  Employee condition=new Employee();
		  Department dep=new Department();
		  dep.setId(d_id);
		  condition.setName(name);
		  condition.setSex(sex);
		  condition.setAge(age);
		  condition.setDep(dep);
		  
			int curPage=1;
			  int count = empDao.searchCountByCondition(condition);
			if (request.getParameter("ys") != null) {
				curPage = Integer.parseInt(request.getParameter("ys"));		
			} 
		 
		   Panigation p=new Panigation(curPage,count,Constant.NUM_IN_PAGE,Constant.NUM_OF_PAGE);
	      list = empDao.searchByCondition(condition,p.getM(),Constant.NUM_IN_PAGE);
	      
	      request.setAttribute("list", list);
	      request.setAttribute("condition", condition);
	      request.setAttribute("p", p);
	      request.setAttribute("depList", depList);
	   

		try {
			request.getRequestDispatcher(path+"emps.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		depList=depDao.serch();
		request.setAttribute("depList",depList);
		try {
			request.getRequestDispatcher(path+"add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		depList=depDao.serch();
		request.setAttribute("depList",depList);
		try {
			request.getRequestDispatcher(path+"add2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showChange(HttpServletRequest request, HttpServletResponse response) {
		depList=depDao.serch();
		int id = Integer.parseInt(request.getParameter("id"));
		Employee emp = empDao.searchById(id);
		request.setAttribute("emp", emp);
		request.setAttribute("depList",depList);
		try {
			request.getRequestDispatcher(path+"change.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path="F://test1";
			
		FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		String name="";
		String sex="";
		int age=-1;
		Integer d_id=-1;
		String pic="";
		for(int i=0;i<items.size();i++) {
		FileItem item=items.get(i);
		if(item.getFieldName().equals("myFile")) {
			UUID uuid = UUID.randomUUID();
			String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
			File savedFile = new File(path, uuid.toString() + houzhui);
			pic=uuid.toString() + houzhui;
	    item.write(savedFile);
		}else if(item.getFieldName().equals("picName")) {
			
		}else if(item.getFieldName().equals("name")) {
			 name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
			
		}else if(item.getFieldName().equals("sex")) {
			sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
			
		}else if(item.getFieldName().equals("age")) {
			age=Integer.parseInt(item.getString());
		}else if(item.getFieldName().equals("depName")) {
			d_id=Integer.parseInt(item.getString());
		}
		}
		Employee emp = new Employee();
		Department dep=new Department();
		dep.setId(d_id);
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(age);
		emp.setDep(dep);
		emp.setPic(pic);
		EmployeeDao empDao = new EmployeeDao();
		
		empDao.add(emp);

	
				response.sendRedirect("emps");
				
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean f = empDao.delete(id);
		if (f) {
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteAll(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");

		boolean f = empDao.delete(ids);
		if (f) {
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void change(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		int d_id = Integer.parseInt(request.getParameter("depName"));

		Employee emp = new Employee();
		Department dep=new Department();
		dep.setId(d_id);
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(age);
		emp.setId(id);
		emp.setDep(dep);
		EmployeeDao empDao = new EmployeeDao();

		boolean f = empDao.chag(emp);

		if (f) {
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void showChangeAll(HttpServletRequest request, HttpServletResponse response) {
		depList=depDao.serch();
		request.setAttribute("depList", depList);
		String ids = request.getParameter("ids");
		List<Employee> list = new ArrayList<>();
		list = empDao.searchById(ids);
		Employee emp = new Employee();
		emp = list.get(0);
		request.setAttribute("emp", emp);
		request.setAttribute("ids", ids);
		try {
			request.getRequestDispatcher(path+"changeAll.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showChangeAll2(HttpServletRequest request, HttpServletResponse response) {
		depList=depDao.serch();
		request.setAttribute("depList", depList);
		String ids = request.getParameter("ids");
		EmployeeDao empDao = new EmployeeDao();
		List<Employee> changeList = new ArrayList<>();
		changeList = empDao.searchById(ids);
		request.setAttribute("list", changeList);

		try {
			request.getRequestDispatcher(path+"changeAll2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeAll(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String name=request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		int d_id = Integer.parseInt(request.getParameter("depName"));

		Employee emp = new Employee();
		Department dep=new Department();
		dep.setId(d_id);
        emp.setName(name);
		emp.setSex(sex);
		emp.setAge(age);
		emp.setDep(dep);

		EmployeeDao empDao = new EmployeeDao();

		boolean f = empDao.chagByids(emp, ids);

		if (f) {
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void changeAll2(HttpServletRequest request, HttpServletResponse response) {

		String emps = request.getParameter("emps");

		String array[] = emps.split(";");

		List<Employee> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			String temp[] = array[i].split(",");
			Employee emp = new Employee();
			Department dep=new Department();
			emp.setId(Integer.parseInt(temp[0]));
			emp.setName(temp[1]);
			emp.setSex(temp[2]);
			emp.setAge(Integer.parseInt(temp[3]));
			dep.setId(Integer.parseInt(temp[4]));
			emp.setDep(dep);
			list.add(emp);
		}

		boolean f = empDao.changeAll2(list);

		if (f) {
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void changeAll3(HttpServletRequest request, HttpServletResponse response) {

		String emps = request.getParameter("emps");
		JSONArray jsonArray = JSONArray.fromObject(emps);
		List<Employee> list = (List<Employee>) jsonArray.toCollection(jsonArray, Employee.class);
        for(int i=0;i<list.size();i++) {
        	Department dep=new Department();
        	
        	dep.setId(list.get(i).getD_id());
        	list.get(i).setDep(dep);
        }
		boolean f = empDao.changeAll2(list);

		if (f) {
			try {
				response.sendRedirect("emps");
				// request.setAttribute("emps", list);
				// request.getRequestDispatcher("emps.jsp").forward(request,response);
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
