package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import entity.User;

public class UserDao extends BaseDao {
public boolean search(User user) {
	boolean f=false;
	Connection conn=null;
	PreparedStatement pstat=null;
	ResultSet rs=null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
	String sql="select * from user where username=? and password=?";
	 
	pstat = conn.prepareStatement(sql);
	pstat.setString(1, user.getUsername());
	pstat.setString(2, user.getPassword());
	 rs = pstat.executeQuery();
	if (rs.next()) {
		f=true;	
	}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
	closeAll(pstat,conn,rs);
	}
	return f;
}
public boolean add(User user) {
	
	Connection conn=null;
	PreparedStatement pstat=null;
	int rs=0;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
	String sql="insert into user(username,password) values(?,?) ";
	 
	pstat = conn.prepareStatement(sql);
	pstat.setString(1, user.getUsername());
	pstat.setString(2, user.getPassword());
	 rs = pstat.executeUpdate();
	
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
	closeAll(pstat,conn,null);
	}
	return rs>0;
}
}
