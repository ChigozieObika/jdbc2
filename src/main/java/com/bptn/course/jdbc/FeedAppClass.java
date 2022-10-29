package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeedAppClass {
	
	// database credentials
	static String username = "postgres";
	static String password = "password";
	static String dbName = "feedApp";
	static String port = "5432";
	
	static String dbUrl = "localhost";
	
	// method to create a connection to the database
	public Connection CreateConnection() {
	    Connection conn = null;
	    try {
	      
	      String conStr = "jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName;
	      
	      conn = DriverManager.getConnection(conStr, username, password);
	      
	    } catch (Exception e) {
	    	// handle exception
	    	System.out.println(e.getClass().getName() + ": " + e.getMessage());
	    	System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	    return conn;
	  }
	
	// method to close a connection to the database
	public boolean CloseConnection(Connection conn) {
	    boolean flag = true;
	    if (conn != null) {
	    	try {
	    		conn.close();
	    	} catch (SQLException e) {
	    		flag = false;
	    		// handle exception
	    		System.out.println("Exception:" + e.getMessage());
	    	}
	    }
	    return flag;
	  }
	
	// method to add user to the UserID table of the feedApp database
	public void AddUser(Connection conn, String username, String name, String email, String phoneNumber, String userPassword) {
		    try {
		      PreparedStatement stmt = null;
		      
		      String sql = "INSERT INTO \"UserID\" (\"username\", \"name\", \"email\", \"phoneNumber\", \"userPassword\")" + 
		    		  	   "VALUES (?, ?, ?, ?, ?)"; // insert statement
		      
		      stmt = conn.prepareStatement(sql);
		      
		      stmt.setString(1, username);
		      stmt.setString(2, name);
		      stmt.setString(3, email);
		      stmt.setString(4, phoneNumber);
		      stmt.setString(5, userPassword);
		      
		      int numOfRows = stmt.executeUpdate();
		      
		      System.out.println(numOfRows + " record(s) inserted");
		      System.out.println("Insert Executed Successfully!!!");
		      
		      stmt.close();
		      
		    } catch (SQLException ex) {
		      //handle exception from closing stmt
		      System.out.println("Error while inserting new user");
		      System.out.println("Exception:" + ex.getMessage());
		    }
		  }
	  
	  public void GetAllUsers(Connection conn) {
		    try {
		      Statement stmt = null;
		      
		      stmt = conn.createStatement();
		      
		      String sql = "SELECT * FROM \"UserID\""; // query database
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while (rs.next()) {
		    	  
		        // retrieve by column name
		        System.out.print("Username: " + rs.getString("username"));
		        System.out.print(", Name: " + rs.getString("name"));
		        System.out.print(", Email: " + rs.getString("email"));
		        System.out.print(", Phone Number: " + rs.getString("phoneNumber"));
		        System.out.println(", Password: " + rs.getString("userPassword"));
	
		      }
		      
		      System.out.println("Query Executed Successfully!!!");
		      
		      if (stmt != null) {		    	  
		    	  stmt.close();
		      }
		      if (rs != null) {		    	  
		    	  rs.close();
		      }
		      
		    } catch (SQLException ex) {
		    	
		      //handle exception from closing stmt, rs
		    	System.out.println("Error while getting all users");
		        System.out.println("Exception:" + ex.getMessage());
		        
		    }
		  }

	public static void main(String[] args) {
		
		FeedAppClass jdbcFeedApp = new FeedAppClass(); // create an object of the FeedAppClass
		
        Connection conn = jdbcFeedApp.CreateConnection(); // create a connection object
        
        jdbcFeedApp.AddUser(conn, "indiagolf", "Pete Doe", "petedoe@bptn.com", "5466921", "pete!doe&"); // add user to table UserID
        
        jdbcFeedApp.GetAllUsers(conn); // display all users in the UserID table
        
        jdbcFeedApp.CloseConnection(conn); // close connection to the database
        
	}
}
