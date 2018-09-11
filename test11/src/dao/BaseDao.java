package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
public void closeAll(Statement stat,Connection conn,ResultSet rs) {
	try {
		if(rs!=null) {
		rs.close();
		}
		if(stat!=null) {
		stat.close();
		}
		if(conn!=null) {
		conn.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
