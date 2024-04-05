package com.library.crud.read;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.library.Util.Utility;

public class Read {

	private static String SQL_SELECT_BOOK_QUERY = "select bid, bname, quantity from book_self";
	private static String SQL_SELECT_BORROWER_QUERY = "select id, name, issued, due, fine from borrower";

	private static void printResult(ResultSet resultSet, String type) throws SQLException {
		if (type.equalsIgnoreCase("book")) {

			System.out.println();
			System.out.println("Book Id\t\tBook Name\t\t\t\tQuantity");
			System.out.println(
					"________________________________________________________________________________");

			while (resultSet.next()) {
				System.out.println(String.format("%s\t\t%s\t\t\t\t%s", resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3)));
			}
		} else {
			System.out.println();
			System.out.println("Borrower Id\t\tBorrower Name\t\tIssued Date\t\tDue Date\t\tFine");
			System.out.println(
					"___________________________________________________________________________________________________________________");

			while (resultSet.next()) {
				System.out.println(
						String.format("%s\t\t\t\t%s\t\t%s\t\t%s\t\t%s", resultSet.getString(1), resultSet.getString(2),
								resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
			}
		}

		System.out.println();
	}

	public static void readBooks(Connection connection, Scanner scanner) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_SELECT_BOOK_QUERY);
			resultSet = preparedStatement.executeQuery();
			printResult(resultSet, "Book");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatement, resultSet);
		}
	}

	public static void readBorrowers(Connection connection, Scanner scanner) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_SELECT_BORROWER_QUERY);
			resultSet = preparedStatement.executeQuery();
			printResult(resultSet, "Borrower");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatement, resultSet);
		}
	}
}
