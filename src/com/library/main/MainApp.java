package com.library.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.library.Util.Utility;
import com.library.crud.insert.Insert;
import com.library.crud.read.Read;
import com.library.crud.remove.Remove;
import com.library.crud.update.Update;

class InputChoice {
	public String task = "";
	public String thing = "";
}

public class MainApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		try {
			connection = Utility.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int countBooks = 0;
		int countBorrower = 0;
		InputChoice choice = new InputChoice();
		inputOperationLowerCase(scanner, choice);

		while (!choice.task.equalsIgnoreCase("quit")) {

			switch (choice.task) {

			case "read":
				if (choice.thing.equalsIgnoreCase("book")) {
					Read.readBooks(connection, scanner);
				} else {
					Read.readBorrowers(connection, scanner);
				}
				break;

			case "insert":
				if (choice.thing.equalsIgnoreCase("book")) {
					Insert.insertBook(connection, scanner);
					
				} else {
					Insert.insertBorrower(connection, scanner);
				}
				break;

			case "delete":
				if (choice.thing.equalsIgnoreCase("book")) {
					Remove.removeBook(connection, scanner);
				} else {
					Remove.removeBorrower(connection, scanner);
				}
				break;

			case "update":
				if (choice.thing.equalsIgnoreCase("book")) {
					Update.updateBookQuantity(connection, scanner);
				} else {
					
				}
				break;
				
			case "clear":
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"
						+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
						+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				break;

			default:
				System.out.println("Invalid Input....");
			}

			inputOperationLowerCase(scanner, choice);
		}

		System.out.println("\nGood Bye..............................  :)");

		Utility.closeResources(scanner, connection, null, null);

	}


	private static void inputOperationLowerCase(Scanner scanner, InputChoice choice) {
		System.out.print("\nWhat you want to do [read/insert/delete/update/quit/clear]:: ");
		choice.task = scanner.next();

		if (choice.task.equalsIgnoreCase("quit")) {
			return;
		}		
		if (choice.task.equalsIgnoreCase("clear")) {
			return;
		}
		

		System.out.print("[book/borrower] :: ");
		choice.thing = scanner.next();

		choice.task = choice.task.toLowerCase();
		choice.thing = choice.thing.toLowerCase();
	}
}
