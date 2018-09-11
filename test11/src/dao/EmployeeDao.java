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

public class EmployeeDao extends BaseDao {

	public List<Employee> search(){
		
		List<Employee> list=new ArrayList<>();
		Connection conn=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
		Statement stat = conn.createStatement();
		ResultSet rs=stat.executeQuery("select * from employee");
		while(rs.next()) {
			Employee emp=new Employee();
			emp.setId(rs.getInt("id"));
			emp.setName(rs.getString("name"));
			emp.setSex(rs.getString("sex"));
			emp.setAge(rs.getInt("age"));
			emp.setD_id(rs.getInt("d_id"));
			list.add(emp);
			
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
		return list;
		
	}
	
public Employee searchById(int id){
		
	Employee emp = new Employee();
		Connection conn=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
		Statement stat = conn.createStatement();
		ResultSet rs=stat.executeQuery("select e.*,d.id as dId,d.name as dName,d.emp_count as dEmp_count from employee as e left join department as d on e.d_id=d.id where e.id='"+id+"'");
		while(rs.next()) {
			
			emp.setId(rs.getInt("id"));
			emp.setName(rs.getString("name"));
			emp.setSex(rs.getString("sex"));
			emp.setAge(rs.getInt("age"));
			Department dep=new Department();
			dep.setId(rs.getInt("dId"));
			dep.setName(rs.getString("dName"));
			dep.setEmp_count(rs.getInt("dEmp_count"));
            emp.setDep(dep);
			
			
			
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
		return emp;
		
	}
public List<Employee> searchById(String ids){
	List<Employee> list = new ArrayList<>();
	Connection conn=null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
	Statement stat = conn.createStatement();
	ResultSet rs=stat.executeQuery("select e.*,d.id as dId,d.name as dName,d.emp_count as dEmp_count from employee as e left join department as d on e.d_id=d.id where e.id in("+ids+")");

	while(rs.next()) {
		Employee emp = new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setSex(rs.getString("sex"));
		emp.setAge(rs.getInt("age"));
		Department dep=new Department();
		dep.setId(rs.getInt("dId"));
		dep.setName(rs.getString("dName"));
		dep.setEmp_count(rs.getInt("dEmp_count"));
        emp.setDep(dep);
		list.add(emp);
		
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
	return list;
	
}
	public boolean add(Employee emp) {
		PreparedStatement pstat=null;
		Connection conn=null;
		
		boolean f = false;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "456789");
			 String sql="insert into employee(name,sex,age,d_id,pic) values(?,?,?,?,?)";
			 pstat = conn.prepareStatement(sql);
			 pstat.setString(1, emp.getName());
			 pstat.setString(2, emp.getSex());
			 pstat.setInt(3, emp.getAge());
			 pstat.setObject(4, emp.getDep().getId());
			 pstat.setString(5, emp.getPic());
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
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;

	}
	
	public boolean delete(int id) {
		boolean f = false;
		Statement stat = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"456789");

			String sql = "delete from employee where id ='"+id+"'";
			stat = conn.createStatement();

			int rs = stat.executeUpdate(sql);
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
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;

	}
	public boolean chag(Employee emp) {
		Statement stat=null;
		Connection conn=null;
		
		boolean f = false;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "456789");
			 stat = conn.createStatement();
			int rs = stat.executeUpdate("update employee set name='" + emp.getName() + "',sex='" + emp.getSex()
					+ "',age='" + emp.getAge() + "',d_id='" + emp.getDep().getId() + "'where id=" + emp.getId()+ "");
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
	public boolean chagByids(Employee emp,String ids) {
		Statement stat=null;
		Connection conn=null;
		
		boolean f = false;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "456789");
			 stat = conn.createStatement();
			int rs = stat.executeUpdate("update employee set sex='" + emp.getSex()
					+ "',age='" + emp.getAge() + "',d_id='" + emp.getDep().getId() + "'where id in (" + ids + ")");
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
	
	public boolean delete(String ids) {
		boolean f = false;
		Statement stat = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"456789");

			String sql = "delete from employee where id in (" + ids + ")";
			stat = conn.createStatement();

			int rs = stat.executeUpdate(sql);
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



public boolean changeAll2(List<Employee> list) {
	Statement stat=null;
	Connection conn=null;
	
	boolean f = false;
	for(int i=0;i<list.size();i++) {
	try {

		Class.forName("com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
				"root", "456789");
		 stat = conn.createStatement();
		int rs = stat.executeUpdate("update employee set name='" + list.get(i).getName() + "',sex='" + list.get(i).getSex()
				+ "',age='" + list.get(i).getAge() + "',d_id='" + list.get(i).getDep().getId() + "'where id=" + list.get(i).getId()+ "");
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
	
	
	}
	return f;
}


public List<Employee> searchBySize(int m,int size){
	
	List<Employee> list=new ArrayList<>();
	Connection conn=null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
	Statement stat = conn.createStatement();
	ResultSet rs=stat.executeQuery("select * from employee limit "+m+","+size);

	while(rs.next()) {
		Employee emp=new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setSex(rs.getString("sex"));
		emp.setAge(rs.getInt("age"));
		emp.setD_id(rs.getInt("d_id"));
		list.add(emp);
		
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
	return list;
	
}
public int searchCount(){
	
	int a=0;
	Connection conn=null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncording=utf-8", "root", "456789");
	Statement stat = conn.createStatement();
	ResultSet rs=stat.executeQuery("select count(*)as a from employee");
	while(rs.next()) {
		a=rs.getInt("a");
		
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
	return a;
	
}

public List<Employee> searchByCondition(Employee condition,int m,int size) {
	List<Employee> list = new ArrayList<>();
	Statement stat=null;
	Connection conn=null;
	ResultSet rs=null;
   
	try {
		Class.forName("com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "456789");
		 stat = conn.createStatement();
		String where=" where 1=1 ";
		if (condition.getName()!=null&&!condition.getName().equals("")) {
         where+="and e.name='"+condition.getName()+"'";
		}
		if (condition.getSex()!=null&&!condition.getSex().equals("")) {
		 where+="and sex='"+condition.getSex()+"'";
		}
		if (condition.getAge()!=-1) {
		where+="and age="+condition.getAge()+"";
		}
		if (condition.getDep().getId()!=-1) {
			where+=" and d_id="+condition.getDep().getId()+"";
			}
		
		String sql="select e.*,d.id as dId,d.name as dName,d.emp_count as dEmp_count from employee as e left join department as d on e.d_id=d.id "+where+" limit "+m+","+size;
	
		 rs = stat.executeQuery(sql);
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setName(rs.getString("name"));
			emp.setSex(rs.getString("sex"));
			emp.setAge(rs.getInt("age"));
			Department dep=new Department();
			dep.setId(rs.getInt("dId"));
			dep.setName(rs.getString("dName"));
			dep.setEmp_count(rs.getInt("dEmp_count"));
            emp.setDep(dep);
            emp.setPic(rs.getString("pic"));
			list.add(emp);
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
public int searchCountByCondition(Employee condition) {
	
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
		if (condition.getSex()!=null&&!condition.getSex().equals("")) {
		 where+="and sex='"+condition.getSex()+"'";
		}
		if (condition.getAge()!=-1) {
		where+="and age="+condition.getAge()+"";
		}
		if (condition.getDep().getId()!=-1) {
			where+=" and d_id="+condition.getDep().getId();
			}
		
		String sql="select count(*) as a from employee"+where;
	    
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
}