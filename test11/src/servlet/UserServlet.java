package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;
import util.CreateMD5;
import util.RandomNumber;
import util.ValidateCode;

public class UserServlet extends HttpServlet {
	private static final String path="WEB-INF/denglu/";
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type == null) {
		showRegister(request, response);
		}else if(type.equals("showLogin")){
			showLogin(request, response);
		}else if(type.equals("doLogin")){
			doLogin(request, response);
		}else if(type.equals("randomImage")) {
			randomImage(request, response);
		}else if(type.equals("doRegister")){
			doRegister(request, response);
		}
	}
	
	
	private void showRegister(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			request.getRequestDispatcher(path+"register.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		
		UserDao userDao=new UserDao();
		try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPassword(CreateMD5.getMd5(password+""));
		boolean flag=userDao.add(user);
		if(flag) {
			response.sendRedirect("user?type=showLogin");
		}else {
			response.sendRedirect("user");
		}
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	private void randomImage(HttpServletRequest request, HttpServletResponse response) {
		try {
		RandomNumber rn=new RandomNumber();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		ValidateCode vc=rn.generateImage();
		ServletOutputStream outStream;
		
			outStream = response.getOutputStream();
		
		ImageIO.write(vc.getImage(), "JPEG", outStream);
		outStream.close();
		request.getSession().setAttribute("rand", vc.getRand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String random = request.getParameter("random");
		if(random.equals(session.getAttribute("rand"))) {
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPassword(CreateMD5.getMd5(password+""));
		UserDao ud=new UserDao();
		boolean f=ud.search(user);
		if(f) {
			
			session.setAttribute("user", username);
			
			Cookie cookie=new Cookie("username", username);
			cookie.setMaxAge(30);
			response.addCookie(cookie);
			
			response.sendRedirect("index");
		}else {
			response.sendRedirect("user?type=showLogin");
		}
		}else {
			request.setAttribute("mes", "��֤�����");
			request.getRequestDispatcher(path+"login.jsp").forward(request, response);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	private void showLogin(HttpServletRequest request, HttpServletResponse response) {
		String name="";
    Cookie[] cookies=request.getCookies();
    if(cookies!=null) {
    	for(int i=0;i<cookies.length;i++) {
    		if("username".equals(cookies[i].getName())) {
    			
    			name=cookies[i].getValue();
    		
    		}
    	}
    }
	request.setAttribute("name", name);
		try {
			request.getRequestDispatcher(path+"login.jsp").forward(request, response);
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
