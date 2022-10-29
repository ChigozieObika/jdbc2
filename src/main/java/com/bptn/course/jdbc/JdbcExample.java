package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {
	
	static String username = "postgres";
	static String password = "password";
	static String dbName = "bptn";
	static String port = "5432";
	
	static String dbUrl = "localhost";

	public static void main(String[] args) {
		// Connection conn = null;
		
		String conStr = "jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName;
		// String conStr = "jdbc:postgresql://localhost:5432/bptn";
		
		/*
		 * Connection is an interface that extends the Autocloseable interface, that means
		 * Java can close the connection for us by using try with resources (try takes the 
		 * connection object as a parameter).
		 */
		String id = "1";
		String sql = "SELECT * FROM students WHERE \"studentId\" = " + id;
		
		try ( Connection conn = DriverManager.getConnection(conStr, username, password);
			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(sql);){
			
			while (rs.next()) {
				System.out.print("Student Id" + rs.getInt("studentId"));
				System.out.print(", Course Id" + rs.getInt("courseId"));
				System.out.print(", Student Name: " + rs.getString("studentName"));
				System.out.print(", Student Email: " + rs.getString("studentEmail"));
				System.out.print(", Student Phone: " + rs.getInt("studentPhone"));
			}
			System.out.println("Query Executed Successfully!!");
		} catch (SQLException e) {
			System.out.println("Error while running query");
			e.printStackTrace();
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
