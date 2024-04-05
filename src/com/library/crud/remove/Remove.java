package com.library.crud.remove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.library.Util.Utility;

public class Remove {

	private final static String SQL_DELETE_BOOK_QUERY = "delete from book_self where bname = ?";
	private final static String SQL_DELETE_BORROWER_QUERY = "delete from borrower where id = ?";

	public static void removeBook(Connection connection, Scanner scanner) {
		PreparedStatement preparedStatement = null;
		String response = "yes";
		
		try {
			
			while(response.equalsIgnoreCase("yes")) {				
				preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK_QUERY);
				System.out.print("\nEnter the book name for deletion :: ");
				String bookName = scanner.next();
				preparedStatement.setString(1, bookName);
				
				System.out.print("Confirm [yes/no] :: ");
				String confirmation = scanner.next();
				
				System.out.println();
				if (confirmation.equalsIgnoreCase("yes")) {
					preparedStatement.execute();
					System.out.println("One book info is deleted succesfully....");
				} else {
					System.out.println("No book info is deleted....");
				}
				
				System.out.print("\nDelete More [yes/no] :: ");
				response = scanner.next();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatement, null);
		}
		
	}
	
	public static void removeBorrower(Connection connection, Scanner scanner) {
		PreparedStatement preparedStatement = null;
		String response = "yes";
		
		try {
			
			while(response.equalsIgnoreCase("yes")) {				
				preparedStatement = connection.prepareStatement(SQL_DELETE_BORROWER_QUERY);
				System.out.print("\nEnter the borrower id for deletion :: ");
				int borrowerId = scanner.nextInt();
				preparedStatement.setInt(1, borrowerId);
				
				System.out.print("Confirm [yes/no] :: ");
				String confirmation = scanner.next();
				
				System.out.println();
				if (confirmation.equalsIgnoreCase("yes")) {
					preparedStatement.execute();
					System.out.println("One borrower info is deleted succesfully....");
				} else {
					System.out.println("No borrower info is deleted....");
				}
				
				System.out.print("\nDelete More [yes/no] :: ");
				response = scanner.next();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatement, null);
		}
		
	}
}
