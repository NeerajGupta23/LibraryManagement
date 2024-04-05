package com.library.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Utility {

	private static FileInputStream fileInputStream = null;
	private final static String fileLocation = ".\\src\\com\\library\\properties\\database.properties";
	private static Properties properties = null;

	static {
		try {
			File file = new File(fileLocation);
			fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
				properties.getProperty("password"));
	}

	public static void closeResources(Scanner scanner, Connection connection, Statement statement, ResultSet resultSet) {
		if (scanner != null) {
			scanner.close();
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
