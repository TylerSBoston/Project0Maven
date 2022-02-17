package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String ConnectionString = "jdbc:postgresql://localhost:5432/Project0";
	private static String userName = "postgres";
	private static String password = "1234";
	private static Connection conn = null;
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(ConnectionString,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		try {
			if(conn == null || conn.isClosed() == true)
			{
				conn = DriverManager.getConnection(ConnectionString,userName,password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
