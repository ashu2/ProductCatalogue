package sb.videocon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sb.videocon.model.Employee;

public class ConnectionTest {
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		   "jdbc:postgresql://localhost:5432/videocon","postgres", "ilfs");
		return connection;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DbOperations obj = new DbOperations();
//		obj.DbOperations
	}
	
	}
