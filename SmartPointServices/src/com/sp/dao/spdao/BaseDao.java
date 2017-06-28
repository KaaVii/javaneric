package com.sp.dao.spdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class BaseDao {
	/**
	 * @author dan
	 */
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	// private static final String DB_CONNECTION =
	// "jdbc:db2://extbasicpacman05.w3-969.ibm.com:50000/TIS_DEV:retrieveMessagesFromServerOnGetMessage=true";
	private static final String DB_CONNECTION = "jdbc:mysql://dbsp.czs3arioqj71.us-east-1.rds.amazonaws.com:3306/";
	private static final String DB_NAME = "SmartPointDB";
	private static final String DB_USER = "rootsp";
	private static final String DB_PASSWORD = "smartpoint";
		
		
			
			

		protected static Connection getDBConnection() {
			Connection dbConnection = null;
			try {
				Class.forName(DB_DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
			try {
				dbConnection = DriverManager.getConnection(DB_CONNECTION + DB_NAME, DB_USER,
						DB_PASSWORD);
				return dbConnection;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return dbConnection;
		}
		

		
		protected static void closeConnection(Connection conn, Statement statement,
				ResultSet rs) {
			try {
				if (rs != null) {
					rs.close();
				}
				if(statement != null){
					statement.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
		protected static void closeConnection(Connection conn, Statement pstmt) {
			try {
			closeConnection(conn, pstmt, null);	
				} catch (Exception e){
					e.printStackTrace();
				}
			}
}