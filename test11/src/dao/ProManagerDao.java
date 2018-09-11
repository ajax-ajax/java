package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;

public class ProManagerDao extends BaseDao{
public int searchCountByDep(int id) {
		
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		int a=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			

			String sql="select count(p_name) as a from v_dep_pro where d_id="+id;
		
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
	public List<Project> proSearch(int id,int curPage,int size) {
		List<Project> list = new ArrayList<>();
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			 
			String sql="select  * from v_dep_pro where d_id="+id+" limit "+curPage+","+size+"";
			
			 rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				list.add(pro);
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
	public List<Project> proSearch(int id) {
		List<Project> list = new ArrayList<>();
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			 
			String sql="select  * from v_dep_pro where d_id="+id;
			
			 rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				list.add(pro);
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
	public List<Project> proByDid(int depId) {
		List<Project> list = new ArrayList<>();
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
		
			
			String sql="select * from project where id not in( select p_id from v_dep_pro where d_id=" + depId + ")";
			 rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
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
	public boolean addpro(int d_id,int p_id) {
		boolean f=false;
		Statement stat=null;
		Connection conn=null;
	
		try {

			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","456789");
			 stat=conn.createStatement();
			int rs=stat.executeUpdate("insert into r_d_id_p_id(d_id,p_id) values('"+d_id+"','"+p_id+"')");
			if(rs>0) {
			f=true;
			}
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(stat,conn,null);
		}
		return f;
		
	}
	public boolean addproAll(int d_id,String p_ids) {
		boolean f=false;
		Statement stat=null;
		Connection conn=null;
	
		try {

			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","456789");
			 stat=conn.createStatement();
			 
			 String array[]=p_ids.split(",");
			 for (int i = 0; i < array.length; i++) {
			 int p_id=Integer.parseInt(array[i]);
			
			int rs=stat.executeUpdate("insert into r_d_id_p_id(d_id,p_id) values('"+d_id+"','"+p_id+"')");
			if(rs>0) {
				f=true;
				}
			 }
			
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(stat,conn,null);
		}
		return f;
		
	}	
	public boolean deletepro(int d_id,int p_id) {
		
		boolean f = false;
		PreparedStatement pstat = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"456789");
			//事务
			String sql = "delete from r_d_id_p_id where d_id =? and p_id=?";
			pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, d_id);
	        pstat.setInt(2, p_id);
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				
			f = true;
				
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
		return f;

	}
public boolean deleteproAll(int d_id,String p_ids) {
		
		boolean f = false;
		PreparedStatement pstat = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"456789");
			//事务
			String sql = "delete from r_d_id_p_id where d_id =? and p_id in (" + p_ids + ")";
			pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, d_id);
	        
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				
			f = true;
				
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
		return f;

	}
	 
}
