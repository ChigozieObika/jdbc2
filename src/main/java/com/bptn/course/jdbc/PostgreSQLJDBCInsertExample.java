package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Create a class PostgresSQLJDBCInsert
public class PostgreSQLJDBCInsertExample {
	
	static String username = "pguser";
	static String password  = "AVNS_ZAhTNCU4QYnMvAUrk7b";
	static String dbName = "weatherDB";
	static String port = "25060";
	
	// Create a String variable and store the insert query
	static String dbUrl = "cohort-1-section-2-team-4-do-user-12484302-0.b.db.ondigitalocean.com";

	public static void main(String[] args) {
		
		String conStr = "jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName;
		
		String sql = "INSERT INTO \"User\" (\"id\", \"firstName\", \"lastName\", \"emailID\", \"username\"," +
					 "\"address\", \"phone\", \"isAdmin\", \"cityID\", \"createdOn\" )" +
					 "VALUES (?, ?, ?, ?, ?, ?, ?, (CAST(? AS bit)), ?, (CAST(? AS timestamp)))";
		
		// Create a Connection interface object to establish a connection between Java and PostgreSQL
		// Create a PreparedStatement object to run SQL insert query.
		// Close the connection object and prepare the statement object after completing the transaction.
		try ( Connection conn = DriverManager.getConnection(conStr, username, password);
				PreparedStatement stmt = conn.prepareStatement(sql);
			  ){
			stmt.setInt(1, 100000);
			stmt.setString(2, "John");
			stmt.setString(3, "Wick");
			stmt.setString(4, "babayaro@gmail.com");
			stmt.setString(5, "johnwickmustdie");
			stmt.setString(6, "1 John Wick Dr.");
			stmt.setString(7, "9999999999");
			stmt.setInt(8, 1);
			stmt.setInt(9, 104);
			stmt.setString(10, "2020-06-22 19:10:25");
			
			System.out.println("Opened database successfully");
			
			int num = stmt.executeUpdate();
			
			System.out.println(num + " record(s) inserted");
			
			System.out.println("Query Executed Successfully!!");
		} catch (SQLException e) {
			System.out.println("Error while running query");
			System.out.println("Exception:" + e.getMessage());
		} 
	}
}
