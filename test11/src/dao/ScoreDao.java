package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import entity.Score;

public class ScoreDao extends BaseDao{

	public List<Score> serch() {
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		List<Score> list = new ArrayList<>();
		ScoreDao scDao=new ScoreDao();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "456789");
			 stat = conn.createStatement();
			 rs = stat.executeQuery("select * from v_emp_sc");
			while (rs.next()) {
				Score sc = new Score();
				 Employee emp=new Employee();
				 emp.setId(rs.getInt("e_id"));
				    emp.setName(rs.getString("e_name"));
				    Department dep=new Department();
				    dep.setId(rs.getInt("d_id"));
				    dep.setName(rs.getString("d_name"));
				    emp.setDep(dep);
				    sc.setEmp(emp);
				    Project pro=new Project();
				    pro.setId(rs.getInt("p_id"));
				    pro.setName(rs.getString("p_name"));
				    sc.setPro(pro);
				    sc.setValue((Integer)rs.getObject("value"));
				    sc.setGrade(rs.getString("grade"));
				    list.add(sc);
			
			}
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(stat,conn,rs);
		}

		return list;

	}
	
	
	public Score serch(int id) {
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		Score sc = new Score();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "456789");
			 stat = conn.createStatement();
			 rs = stat.executeQuery("select * from v_emp_sc where s_id="+id);
			while (rs.next()) {
				
				sc.setId(rs.getInt("s_id"));
				 Employee emp=new Employee();
				 emp.setId(rs.getInt("e_id"));
				    emp.setName(rs.getString("e_name"));
				    Department dep=new Department();
				    dep.setId(rs.getInt("d_id"));
				    dep.setName(rs.getString("d_name"));
				    emp.setDep(dep);
				    sc.setEmp(emp);
				    Project pro=new Project();
				    pro.setId(rs.getInt("p_id"));
				    pro.setName(rs.getString("p_name"));
				    sc.setPro(pro);
				    sc.setValue((Integer)rs.getObject("value"));
				    sc.setGrade(rs.getString("grade"));
				   
			
			}
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(stat,conn,rs);
		}

		return sc;

	}
//	public String  serch(int id) {
//		Statement stat=null;
//		Connection conn=null;
//		ResultSet rs=null;
//
//      String grade="";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "456789");
//			 stat = conn.createStatement();
//			 rs = stat.executeQuery("select * from score where id="+id);
//			while (rs.next()) {
//				grade=rs.getString("grade");
//			
//			}
//			
//
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			closeAll(stat,conn,rs);
//		}
//
//		return grade;
//
//	}
	public List<Score> searchByCondition(Score condition,int curPage,int size) {
		List<Score> list = new ArrayList<>();
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			String where=" where 1=1 ";
			if (!condition.getEmp().getName().equals("")) {
             where+="and e_name='"+condition.getEmp().getName()+"'";
			}
			if (condition.getEmp().getDep().getId()!=-1) {
			 where+=" and d_id="+condition.getEmp().getDep().getId()+"";
			}
			if (condition.getPro().getId()!=-1) {
				 where+=" and p_id="+condition.getPro().getId()+"";
				}
			if (condition.getValue()!=-1) {
			where+=" and value="+condition.getValue()+"";
			}
			if (!condition.getGrade().equals("")) {
				 where+=" and grade='"+condition.getGrade()+"'";
				}
			
			String sql="select * from v_emp_sc"+where+" limit "+curPage+","+size ;
System.out.println(sql);
			 rs = stat.executeQuery(sql);
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("s_id"));
				 Employee emp=new Employee();
				 emp.setId(rs.getInt("e_id"));
				    emp.setName(rs.getString("e_name"));
				    Department dep=new Department();
				    dep.setId(rs.getInt("d_id"));
				    dep.setName(rs.getString("d_name"));
				    emp.setDep(dep);
				    sc.setEmp(emp);
				    Project pro=new Project();
				    pro.setId(rs.getInt("p_id"));
				    pro.setName(rs.getString("p_name"));
				    sc.setPro(pro);
				    sc.setValue((Integer)rs.getObject("value"));
				    sc.setGrade(rs.getString("grade"));
				    list.add(sc);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(stat,conn,rs);
		}

		return list;

	}
	
	public int searchByCountCondition(Score condition) {

		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		int a=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			String where=" where 1=1 ";
			if (!condition.getEmp().getName().equals("")) {
	             where+="and e_name='"+condition.getEmp().getName()+"'";
				}
				if (condition.getEmp().getDep().getId()!=-1) {
				 where+=" and d_id='"+condition.getEmp().getDep().getId()+"'";
				}
				if (condition.getPro().getId()!=-1) {
					 where+=" and p_id='"+condition.getPro().getId()+"'";
					}
				if (condition.getValue()!=-1) {
				where+=" and value="+condition.getValue()+"";
				}
				if (!condition.getGrade().equals("")) {
					 where+=" and grade='"+condition.getGrade()+"'";
					}
			
			String sql="select count(*) as a from v_emp_sc"+where ;
		
			 rs = stat.executeQuery(sql);
			while (rs.next()) {
				a=rs.getInt("a");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(stat,conn,rs);
		}

		return a;

	}

	public int  add(Score sc) {
		PreparedStatement pstat=null;
		Connection conn=null;
		int id=0;
		int rs=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "456789");
			 String sql="insert into score(e_id,p_id,value) values(?,?,?)";
			 pstat = conn.prepareStatement(sql);
		     pstat.setInt(1, sc.getEmp().getId());
		     pstat.setInt(2, sc.getPro().getId());
		     pstat.setInt(3, sc.getValue());
		     rs= pstat.executeUpdate();
			pstat.close();
		     sql="select LAST_INSERT_ID()";
		     pstat = conn.prepareStatement(sql);
		    ResultSet r= pstat.executeQuery();	
		    if(r.next()) {
		    	id=r.getInt(1);
		    }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(pstat,conn,null);
		}

		return id;

	}
	public boolean update(Score sc) {
		PreparedStatement pstat=null;
		Connection conn=null;
		int rs=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "456789");
			 String sql="update score set value=? where id=?";
			 pstat = conn.prepareStatement(sql);
		     pstat.setInt(1, sc.getValue());
		     pstat.setInt(2, sc.getId());
		     
		     rs=pstat.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(pstat,conn,null);
		}

		return rs >0;

	}
	}
	

