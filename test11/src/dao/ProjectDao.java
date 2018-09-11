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


public class ProjectDao extends BaseDao{
public int searchCountByCondition(Project condition) {
		
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		int a=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			String where=" where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
	         where+="and name='"+condition.getName()+"'";
			}

			String sql="select count(*) as a from project "+where;
		
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
	
	public List<Project> searchByCondition(Project condition,int m,int size) {
		List<Project> list = new ArrayList<>();
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			String where=" where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
	         where+="and name='"+condition.getName()+"'";
			}
			
	
			
			String sql="select * from project "+where+" limit "+m+","+size;
		
			System.out.println(sql);
			
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
		}finally{
			closeAll(stat,conn,rs);
		}

		return list;

	}
	
	public Project searchById(int id){
		
		Project pro=new Project();
			Connection conn=null;
			try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
			Statement stat = conn.createStatement();
			ResultSet rs=stat.executeQuery("select * from project where id='"+id+"'");
			while(rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
		
				
			}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return pro;
			
		}
		
	
	public boolean add(Project pro) {
		boolean f=false;
		Statement stat=null;
		Connection conn=null;
		try {
	
			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","456789");
			 stat=conn.createStatement();
			int rs=stat.executeUpdate("insert into Project(name) values('"+pro.getName()+"')");
			if(rs>0) {
			f=true;
			}
			
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
	public List<Project> serch() {
		List<Project> list=new ArrayList<>();
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","456789");
			 stat=conn.createStatement();
			 rs=stat.executeQuery("select * from Project" );
			while(rs.next()) {
				Project pro=new Project();
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
	
	public boolean chag(Project pro) {
		boolean f=false;
		Connection conn=null;
		PreparedStatement pstat=null;
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","456789");
			String sql="update project set name=? where id=?";
	        pstat =conn.prepareStatement(sql);
	        pstat.setString(1, pro.getName());
	        pstat.setInt(2, pro.getId());
	        int rs=pstat.executeUpdate();
			if(rs>0) {
				f=true;
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
	
	public List<Project> serchByCondition(Project condition) {
		List<Project> list = new ArrayList<>();
		Statement stat=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
			 stat = conn.createStatement();
			 String where="where 1=1";
			 if (condition.getId()!=-1) {
	              where=where+" and id='"+condition.getId()+"'";
				}
			if (!condition.getName().equals("")) {
              where=where+" and name='"+condition.getName()+"'";
			}
			
			String sql="select * from Project "+where;
			System.out.println(sql);
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
	
public boolean delete(int id) {
	
	boolean f = false;
	PreparedStatement pstat = null;
	Connection conn = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
				"456789");

		//ÊÂÎñ
		conn.setAutoCommit(false);
		
		String sql = "delete from project where id =?";
		
		pstat = conn.prepareStatement(sql);
        pstat.setInt(1, id);
		int rs = pstat.executeUpdate();
		
		sql="delete from r_d_id_p_id where p_id=?";
		pstat = conn.prepareStatement(sql);
		 pstat.setInt(1, id);
		 rs = pstat.executeUpdate();
		conn.commit();
		 
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

