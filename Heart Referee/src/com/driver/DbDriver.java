package com.driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbDriver {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "12345678";
	private static final String DRIVER = "org.postgresql.Driver";
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
	}

}
