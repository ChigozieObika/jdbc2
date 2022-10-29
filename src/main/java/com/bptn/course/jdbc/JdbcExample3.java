package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExample3 {
	
	static String username = "postgres";
	static String password = "password";
	static String dbName = "bptn";
	static String port = "5432";
	
	static String dbUrl = "localhost";

	public static void main(String[] args) {
		// Connection conn = null;
		ResultSet rs = null;
		String conStr = "jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName;
		// String conStr = "jdbc:postgresql://localhost:5432/bptn";
		
		/*
		 * Connection is an interface that extends the Autocloseable interface, that means
		 * Java can close the connection for us by using try with resources (try takes the 
		 * connection object as a parameter).
		 */
		String sql = "INSERT INTO students (\"studentId\", \"courseId\", \"studentName\"," +
					 "\"studentEmail\", \"studentPhone\") VALUES (?, ?, ?, ?, ?)";
		
		try ( Connection conn = DriverManager.getConnection(conStr, username, password);
			  PreparedStatement stmt = conn.prepareStatement(sql);
			  ){
			stmt.setInt(1, 3);
			stmt.setInt(2, 1);
			stmt.setString(3, "Yuvraj");
			stmt.setString(4, "yuvraj@bptn.com");
			stmt.setInt(5, 25490022);
			
			int num = stmt.executeUpdate();
			
			System.out.println("Rows inserted: " + num);
			
			System.out.println("Query Executed Successfully!!");
		} catch (SQLException e) {
			System.out.println("Error while running query");
			e.printStackTrace();
		} 
		finally {
			if (rs!= null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	// Use this method to close the connection by calling it in a finally block in the main method
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
