package com.library.crud.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.library.Util.Utility;

public class Update {

	private static final String SQL_SELECT_BOOK_QUANTITY_QUERY = "select quantity from book_self where bname = ";
	private static final String SQL_UPDATE_BOOK_QUANTITY_QUERY = "update book_self set quantity = ? where bname = ?";

	public static void updateBookQuantity(Connection connection, Scanner scanner) {
		System.out.println();
		Statement statementForSelect = null;
		PreparedStatement preparedStatementForUpdate = null;
		String response = "yes";

		try {
			statementForSelect = connection.createStatement();
			preparedStatementForUpdate = connection.prepareStatement(SQL_UPDATE_BOOK_QUANTITY_QUERY);

			while (response.equalsIgnoreCase("yes")) {
				System.out.print("Enter the book name or back :: ");
				String bookName = scanner.next();

				if (bookName.equalsIgnoreCase("back")) {
					break;
				}

				ResultSet resultSet = statementForSelect
						.executeQuery(SQL_SELECT_BOOK_QUANTITY_QUERY + "'" + bookName + "'");

				if (!displayQuantity(resultSet)) {
					continue;
				}

				preparedStatementForUpdate.setString(2, bookName);
				System.out.print("Do you want to update it [yes/no] :: ");
				String responseForUpdate = scanner.next();

				if (responseForUpdate.equalsIgnoreCase("yes")) {
					System.out.println();
					System.out.print("Enter the new quantity :: ");
					preparedStatementForUpdate.setInt(1, scanner.nextInt());

					preparedStatementForUpdate.execute();
					System.out.println("Quantity is updated succesfully....");
				} else {
					System.out.println("No updation is done....");
				}

				System.out.print("\nUpdate More [yes/no] :: ");
				response = scanner.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utility.closeResources(null, null, preparedStatementForUpdate, null);
			Utility.closeResources(null, null, statementForSelect, null);
		}
	}

	private static final String SQL_SELECT_BORROWER_FINE_QUERY = "select fine from borrower where id = ";
	private static final String SQL_UPDATE_BORROWER_FINE_QUERY = "update borrower set fine = ? where id = ?";

	public static void updateBorrowerFine(Connection connection, Scanner scanner) {
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String response = "yes";

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_BORROWER_FINE_QUERY);
			statement = connection.createStatement();

			while (response.equalsIgnoreCase("yes")) {
				System.out.println();
				System.out.print("Enter the borrower id or back :: ");
				String borrowerId = scanner.next();

				if (borrowerId.equalsIgnoreCase("back")) {
					break;
				}

				resultSet = statement.executeQuery(SQL_SELECT_BORROWER_FINE_QUERY + borrowerId);
				if (!displayFine(resultSet)) {
					continue;
				}

				preparedStatement.setInt(2, Integer.valueOf(borrowerId));
				System.out.print("Do you want to update it [yes/no] :: ");
				String opinion = scanner.next();

				if (opinion.equalsIgnoreCase("yes")) {
					System.out.print("Enter new value of Fine :: ");
					int newFine = scanner.nextInt();
					preparedStatement.setInt(1, newFine);
					
					
					
					preparedStatement.execute();
					System.out.println("Fine is updated succesfully....");
				} else {
					System.out.println("No updation is done....");
				}

				System.out.print("\nUpdate More [yes/no] :: ");
				response = scanner.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static boolean displayFine(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			System.out.println("No id is matched...");
			return false;
		}

		System.out.print("previous fine :: ");
		System.out.println(resultSet.getInt(1));
		System.out.println();
		return true;
	}

	private static boolean displayQuantity(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			System.out.println("No book is matched...");
			System.out.println();
			return false;
		}

		System.out.print("previous quantity :: ");
		System.out.println(resultSet.getInt(1));
		System.out.println();
		return true;
	}
}
