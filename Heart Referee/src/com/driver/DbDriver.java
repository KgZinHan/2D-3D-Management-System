package com.driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbDriver {

	private static final String URL = "jdbc:mysql://localhost:3306/testing";
	private static final String USER = "root";
	private static final String PASSWORD = "jarvis1998";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}
	}

}
