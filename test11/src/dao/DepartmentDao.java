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
import entity.Employee;

public class DepartmentDao extends BaseDao{

	public List<Department> serch() {
		Statement stat=null;
		Connection conn=null;
		ResultSet rs=null;
		List<Department> list=new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","456789");
			 stat=conn.createStatement();
			 rs=stat.executeQuery("select * from department" );
			while(rs.next()) {
				Department dep=new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));
			
				list.add(dep);
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
public Department searchById(int id){
		
	Department dep=new Department();
		Connection conn=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
		Statement stat = conn.createStatement();
		ResultSet rs=stat.executeQuery("select * from department where id='"+id+"'");
		while(rs.next()) {
			dep.setId(rs.getInt("id"));
			dep.setName(rs.getString("name"));
			
			dep.setEmp_count(rs.getInt("emp_count"));
		
			
			
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
		return dep;
		
	}
	
	
	public boolean add(Department dep) {
		Statement stat=null;
		Connection conn=null;
	
		boolean f=false;
		try {
	
			Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","456789");
			 stat=conn.createStatement();
			int rs=stat.executeUpdate("insert into department(name) values('"+dep.getName()+"')");
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
			
			String sql = "delete from department where id =?";
			
			pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, id);
			int rs = pstat.executeUpdate();
			
			sql="update employee set d_id=null where d_id=?";
			pstat = conn.prepareStatement(sql);
			 pstat.setInt(1, id);
			 rs = pstat.executeUpdate();
			
			 sql="delete from r_d_id_p_id where d_id=?";
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

	
	public int searchCountByCondition(Department condition) {
		
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
			
			if (condition.getEmp_count()!=-1) {
			where+="and ifnull(emp_count,0)="+condition.getEmp_count()+"";
			}
			
			String sql="select count(*) as a from department "+where;
		
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
	
	public List<Department> searchByCondition(Department condition,int m,int size) {
		List<Department> list = new ArrayList<>();
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
			
			if (condition.getEmp_count()!=-1) {
			where+="and ifnull(emp_count,0)="+condition.getEmp_count()+"";
			}
			
			String sql="select * from department "+where+" limit "+m+","+size;
		
			 rs = stat.executeQuery(sql);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				
				dep.setEmp_count(rs.getInt("emp_count"));
				list.add(dep);
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
	
	public boolean chag(Department dep) {
		Statement stat=null;
		Connection conn=null;
		
		boolean f = false;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "456789");
			 stat = conn.createStatement();
			int rs = stat.executeUpdate("update department set name='" + dep.getName() + "'where id=" + dep.getId()+ "");
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
			closeAll(stat,conn,null);
		}
		return f;

	}
}
