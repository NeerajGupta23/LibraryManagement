package com.library.crud.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.library.Util.Utility;

public class Insert {

	private final static String SQL_INSERT_BOOK_QUERY = "insert into Book_Self(`bname`, `quantity`) values(?, ?)";
	private final static String SQL_INSERT_BORROWER_QUERY = "insert into Borrower(`name`, `issued`, `due`, `fine`) values(?, ?, ?, ?)";

	public static void insertBook(Connection connection, Scanner scanner) {
		PreparedStatement preparedStatement = null;
		String response = "yes";
		int count = 0;

		try {
			preparedStatement = connection.prepareCall(SQL_INSERT_BOOK_QUERY);

			while (response.equalsIgnoreCase("yes")) {
				count++;

				System.out.print("\nEnter the book name :: ");
				preparedStatement.setString(1, scanner.next());

				System.out.print("Enter the total quantity :: ");
				preparedStatement.setInt(2, scanner.nextInt());

				preparedStatement.execute();

				System.out.print("\nInsert More [yes/no] :: ");
				response = scanner.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatement, null);
		}

		printInsertionDetails(count, "Book");
	}
	
	public static void insertBorrower(Connection connection, Scanner scanner) {
		PreparedStatement preparedStatement = null;
		String response = "yes";
		int count = 0;

		try {
			preparedStatement = connection.prepareCall(SQL_INSERT_BORROWER_QUERY);

			while (response.equalsIgnoreCase("yes")) {
				count++;

				System.out.print("\nEnter the borrower name :: ");
				preparedStatement.setString(1, scanner.next());

				System.out.print("Enter the issued date [dd/MM/yyyy] :: ");
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		        Date issuedDate = formatter.parse(scanner.next());
				preparedStatement.setDate(2, new java.sql.Date(issuedDate.getTime()));
				
				System.out.print("Enter the due date [dd/MM/yyyy] :: ");
				Date dueDate = formatter.parse(scanner.next());
				preparedStatement.setDate(3, new java.sql.Date(dueDate.getTime()));
				
				System.out.print("Enter the total fine :: ");
				preparedStatement.setFloat(4, scanner.nextFloat());
				
				preparedStatement.execute();

				System.out.print("\nInsert More [yes/no] :: ");
				response = scanner.next();
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatement, null);
		}
		
		printInsertionDetails(count, "Borrower");

	}
	
	private static void printInsertionDetails(int countBooks, String thing) {
		System.out.println();

		switch (countBooks) {
		case 0:
			System.out.println(String.format("No %ss are Inserted...", thing));
			break;
		case 1:
			System.out.println(String.format("Total %d %s is inserted....", countBooks, thing));
			break;

		default:
			System.out.println(String.format("Total %d %ss are inserted....", countBooks, thing));
		}
	}
}