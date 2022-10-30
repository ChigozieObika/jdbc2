package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcExample4 {
	
	static String username = "postgres";
	static String password = "password";
	static String dbName = "bptn";
	static String port = "5432";
	
	static String dbUrl = "localhost";

	public static void main(String[] args) {
		
		String conStr = "jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName;
		
		String sql = "INSERT INTO courses (\"courseName\") VALUES (?)"; // insert statement for courses table
		
		String sql1 = "INSERT INTO students (\"studentId\", \"courseId\", \"studentName\"," +
					 "\"studentEmail\", \"studentPhone\") VALUES (?, ?, ?, ?, ?)"; // insert statement for students table
		
//		Connection conn = null;
		try (Connection conn = DriverManager.getConnection(conStr, username, password);){ // used a try-catch to handle a checked exception for creating a connection object
			 
			conn.setAutoCommit(false); // set auto-commit to false
			
			try ( PreparedStatement stmt = conn.prepareStatement(sql); // Autocloseable for PreparedStatement objects
					PreparedStatement stmt1 = conn.prepareStatement(sql1);
				  ){
				
				stmt.setString(1, "Java");
				
				stmt1.setInt(1, 6);
				stmt1.setInt(2, 3);
				stmt1.setString(3, "Chigozie");
				stmt1.setString(4, "Chigozies@bptn.com");
				stmt1.setInt(5, 2546029);
				
				int num = stmt.executeUpdate();
				int num1 = stmt1.executeUpdate();
				
				conn.commit();
				
				System.out.println("Number of rows inserted into courses table: " + num);
				
				System.out.println("Number of rows inserted into students table: " + num1);
				
				System.out.println("Query Executed Successfully!!");
			} catch (SQLException e) { // catch of autocloseable try-catch
				System.out.println("Error while running query");
				conn.rollback();
				System.out.println("Transaction rolled back");
				System.out.println("Exception:" + e.getMessage());
			} 
			
		} catch (SQLException e1) {
			System.out.println("Exception:" + e1.getMessage());
		}
	}
}
