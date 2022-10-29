package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresSQLJDBCInsert {

	static String username = "pguser";
	static String password  = "AVNS_ZAhTNCU4QYnMvAUrk7b";
	static String dbName = "weatherDB";
	static String port = "25060";

	static String dbUrl = "cohort-1-section-2-team-4-do-user-12484302-0.b.db.ondigitalocean.com";

	public Connection CreateConnection() {
		Connection conn = null;
		try {conn = DriverManager.getConnection("jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName,
				username, password);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return conn;
	}

	public boolean CloseConnection(Connection conn) {
		boolean flag = true;
		try {
			conn.close();
		} catch (SQLException ex) {
			flag = false;
			// Handle exception, in case of any errors:
			System.out.println("Exception:" + ex.getMessage());
		}
		return flag;
	}
	
	public void AddUser(Connection conn, int id, String firstName, String lastName, String emailID, String username, String address, String phone, int isAdmin, 
			int cityID, String createdOn) {
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO \"User\" (\"id\", \"firstName\", \"lastName\", \"emailID\", \"username\", \"address\", \"phone\", \"isAdmin\", \"cityID\", \"createdOn\" ) VALUES (?, ?, ?, ?, ?, ?, ?, (CAST(? AS bit)), ?, (CAST(? AS timestamp)))";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, emailID);
			stmt.setString(5, username);
			stmt.setString(6, address);
			stmt.setString(7, phone);
			stmt.setInt(8, isAdmin);
			stmt.setInt(9, cityID);
			stmt.setString(10, createdOn);
			int resultCount = stmt.executeUpdate();
			System.out.println(resultCount + " record(s) inserted");
			stmt.close();
		} catch (SQLException ex) {
			//handle exception
			System.out.println("Exception:" + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		//PreparedStatement Object
		PostgresSQLJDBCInsert jdbc = new PostgresSQLJDBCInsert();
		Connection conn = jdbc.CreateConnection();
		jdbc.AddUser(conn, 100000, "John", "Wick", "babayaro@gmail.com","johnwickmustdie","1 John Wick Dr.", "9999999999", 1, 104, "2020-06-22 19:10:25" );
		jdbc.CloseConnection(conn);


	}

}

