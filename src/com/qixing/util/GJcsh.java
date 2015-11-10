package com.qixing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GJcsh {
	private static final String  JAR="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/qi";
	private static final String USERNAME="root";
	private static final String PASSWORD="12138";
	
	public static Connection getConnection(){
		Connection connection=null;
		try {
			Class.forName(JAR);
			connection=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;	
	}
	
	public static void close(Connection connection,PreparedStatement preparedStatement,
			ResultSet resultSet){
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement!=null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection .close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
