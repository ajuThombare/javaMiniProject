package com.library.main;

import java.util.Scanner;

import com.library.modules.Library;
import com.library.util.Student;

public class Main {
	public static void main(String[] args) {
		boolean mainFlag = true;
		Scanner sc = new Scanner(System.in);

		do {

			System.out.println("Welcome to Library Choose your Role: ");
			System.out.println("1. Librarian");
			System.out.println("2. Teacher");
			System.out.println("3. Student.");
			System.out.println("4. Exit.");

			int mainOption = sc.nextInt();
			Library objLibrary = new Library();
			switch (mainOption) {

			// Role Librarian
			case 1:
				boolean librerianFlag = true;
				System.out.println("Enter Librarian password:");
				if (sc.next().equals("123")) {

					do {
						System.out.println("1. Add New Book.");
						System.out.println("2. See the availble Books.");
						System.out.println("3. Issue book.");
						System.out.println("4. View All Issued books.");
						System.out.println("5. Receive Book.");
						System.out.println("6. Receive Library Diposit.");
						System.out.println("7. View All Received Diposit.");

						int option1 = sc.nextInt();
						int ids = 0, dep = 0;
						String book = null, names = null, clas = null;
						if (option1 == 1) {
							System.out.println("Enter Book name to Add: ");
							book = sc.next();
						} else if (option1 == 3) {
							System.out.println("Enter the Book You want to issue: ");
							book = sc.next();
							System.out.println("Enter student id for u want to issue the Book: ");
							ids = sc.nextInt();
						} else if (option1 == 5) {
							System.out.println("Enter the Book that is Received: ");
							book = sc.next();
						} else if (option1 == 6) {
							System.out.println("Enter the Ammount of Diposit: ");
							dep = sc.nextInt();
							System.out.println("Enter student id : ");
							ids = sc.nextInt();
						}

						switch (option1) {

						case 1:
							System.out.println(objLibrary.addNewBooks(book));
							break;
						case 2:
							objLibrary.getAvailBooks();
							break;
						case 3:
							if (objLibrary.isBookAvail(book)) {
								if (objLibrary.isStudentAvail(ids)) {
									if (!objLibrary.issueTheBook(book, ids)) {
										System.out.println("The book is already issued.");
									}
								} else {
									System.out.println("Student with given ID is not present.");
								}
							} else {
								System.out.println("The Books is not Available in Library.");
							}
							break;
						case 4:
							objLibrary.getIssuedBooks();
							break;
						case 5:
							objLibrary.recieveTheBook(book);
							break;

						case 6:
							if (objLibrary.isStudentAvail(ids)) {
								objLibrary.recieveTheDeposit(ids, dep);
							} else {
								System.out.println("Student with given ID is not present.");
							}
							break;
						case 7:
							objLibrary.getAllRecievedDiposit();
							break;
						default:
							System.out.println("Please Select valied Option.");
							break;
						}
						System.out.println("You want to Continue? y/n");
						char state = sc.next().charAt(0);
						if (state == 'n' || state == 'N') {
							librerianFlag = false;
						}
					} while (librerianFlag);
				}
				break;

			// Role Teacher
			case 2:
				boolean teacherFlag = true;
				do {
					System.out.println("1. Add new Student.");
					System.out.println("2. Add New Book.");
					System.out.println("3. View the availble Books.");
					System.out.println("4. View Enrolled Students.");
					System.out.println("5. View All Received Diposit.");

					int option2 = sc.nextInt();
					int ids = 0;
					String names = null, clas = null, books = null;
					if (option2 == 2) {

						if (option2 == 2) {
							System.out.println("Enter Book name to Add: ");
							books = sc.next();
						}
					}

					try {
						switch (option2) {
						case 1:
							System.out.println("Enter student id: ");
							ids = sc.nextInt();
							if (objLibrary.isStudentAvail(ids)) {
								System.out.println("Student is already present with id: " + ids);
								break;
							}
							System.out.println("Enter student Name: ");
							names = sc.next();
							System.out.println("Enter student Class: ");
							clas = sc.next();
							objLibrary.addNewStudent(new Student(ids, names, clas));
							break;

						case 2:
							System.out.println(objLibrary.addNewBooks(books));
							break;
						case 3:
							objLibrary.getAvailBooks();
							break;
						case 4:
							objLibrary.getAllStudents();
							break;
						case 5:
							objLibrary.getAllRecievedDiposit();
							break;
						default:
							System.out.println("Please Select valied Option.");
							break;
						}
					} catch (Exception e) {
						System.out.println("Please Select valied Option.");
					}
					System.out.println("You want to Continue? y/n");
					char state = sc.next().charAt(0);
					if (state == 'n' || state == 'N') {
						teacherFlag = false;
					}
				} while (teacherFlag);
				break;

			// Role Student
			case 3:
				boolean studentFlag = true;
				do {

					System.out.println("1. View All Available Books.");
					System.out.println("2. View All Students Pending Deposit.");
					System.out.println("3. View All Enrolled Students.");

					int option3 = sc.nextInt();
					try {
						switch (option3) {
						case 1:
							objLibrary.getAvailBooks();
							break;
						case 2:
							objLibrary.getAllPendinDiposit();
							break;
						case 3:
							objLibrary.getAllStudents();
							break;
						default:
							System.out.println("Please Select valied Option.");
							break;
						}
					} catch (Exception e) {
						System.out.println("Please Select valied Option.");
					}
					System.out.println("You want to Continue? y/n");
					char state = sc.next().charAt(0);
					if (state == 'n' || state == 'N') {
						studentFlag = false;
					}
				} while (studentFlag);
				break;

			case 4:
				System.out.println("Bye");
				mainFlag = false;
				break;
			default:
				System.out.println("Please Select valied Option.");
				break;

			}

		} while (mainFlag);
	}
}
