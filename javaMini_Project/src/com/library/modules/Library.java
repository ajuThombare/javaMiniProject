package com.library.modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.library.util.LibraryConst;
import com.library.util.Student;

@SuppressWarnings("serial")
public class Library {

	static Connection con = null;

	static List<String> bookShelf = new ArrayList<>() {
		{
			add("Java8");
			add("HTML");
			add("JavaScript");
			add("Scalla");
			add("C#");
			add("C");
		}
	};
	static Map<String, Integer> issuedBooks = new HashMap<>();
	static Map<Integer, Integer> studentDiposit = new HashMap<>() {
		{
			put(1, 5000);
			put(2, 5000);
			put(3, 5000);
		}
	};

	static List<Student> students = new ArrayList<>() {
		{
			add(new Student(1, "Ajay", "FY"));
			add(new Student(2, "Nick", "FY"));
			add(new Student(3, "Harry", "SY"));
			add(new Student(4, "Bruce", "TY"));
			add(new Student(5, "Tony", "TY"));
		}
	};

	public void isBookAvailableOrIssued(String bookName) {
		// TODO Auto-generated method stub

		if (new Library().isBookAvail(bookName)) {
			System.out.println(bookName + " is available in library.");
		} else {
			if (issuedBooks.containsKey(bookName)) {
				System.out.println(bookName + " is available but issued to other student.");
			} else {
				System.out.println(bookName + " is available not in library.");
			}
		}
	}

	public void getMyIssuedBooks(int id) {
		// TODO Auto-generated method stub
		if (issuedBooks.containsValue(id)) {
			System.out.println("My issued Books is/are: ");
			for (Map.Entry<String, Integer> a : issuedBooks.entrySet()) {
				if (a.getValue() == id) {
					System.out.println(a.getKey());
				}
			}
		} else {
			System.out.println("There is not any Book issued to YOU.");
		}
	}

	public void getMyPaidDiposite(int id) {
		// TODO Auto-generated method stub
		if (studentDiposit.containsKey(id)) {
			System.out.println("You have paid " + studentDiposit.get(id) + " Diposite.");
		} else {
			System.out.println("You have not paid any Library Diposite.");
		}
	}

	public boolean isDipositPaid(int id) {
		// TODO Auto-generated method stub
		if (studentDiposit.containsKey(id)) {
			if (studentDiposit.get(id) >= 5000) {
				return true;
			}
		}

		return false;
	}

	public void updatePasswordInBD(String user, String newPassword, String type) {
		// TODO Auto-generated method stub
		try {
			try {
				con = new Library().getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!con.isClosed()) {
				Statement statement = con.createStatement();
				String qr = "UPDATE `library_db`.`userpassword` SET `password` = '" + newPassword
						+ "' WHERE (`username` = '" + user + "');";
				int execute = statement.executeUpdate(qr);
				if (execute == 0)
					System.out.println("Record is not Registered in BD.");
				else {
					System.out.println("New " + type + " is registered.");
				}
			} else
				System.out.println("Connection is closed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void resgisterUser(String user, String password, String type) throws Exception {
		// TODO Auto-generated method stub
		con = new Library().getConnection();
		if (!con.isClosed()) {
			Statement statement = con.createStatement();
			String qr = "INSERT INTO `library_db`.`userpassword` ( `username`, `password`, `type`) VALUES ( '" + user
					+ "', '" + password + "','" + type + "')";
			int execute = statement.executeUpdate(qr);
			if (execute == 0)
				System.out.println("Record is not Registered in BD.");
			else {
				System.out.println("New " + type + " is registered.");
			}

		} else
			System.out.println("Connection is closed!");
	}

	public boolean isUserPassRegistered(String user, String password, String type) throws Exception {
		// TODO Auto-generated method stub
		con = new Library().getConnection();
		if (!con.isClosed()) {
			Statement statement = con.createStatement();
			String st3 = "SELECT * FROM `library_db`.`userpassword` where username = '" + user + "' and type = '" + type
					+ "' and password= '" + password + "'";
			ResultSet rs = statement.executeQuery(st3);
			if (rs.next()) {
				return true;
			}
		}
		return false;
	}

	public boolean isUserRegistered(String user, String type) throws Exception {
		// TODO Auto-generated method stub
		con = new Library().getConnection();
		if (!con.isClosed()) {
			Statement statement = con.createStatement();
			String st3 = "SELECT * FROM `library_db`.`userpassword` where username = '" + user + "' and type = '" + type
					+ "'";
			ResultSet rs = statement.executeQuery(st3);
			if (rs.next()) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidPassword(String password) {

		// Regex to check valid password.
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the password is empty
		// return false
		if (password == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given password
		// and regular expression.
		Matcher m = p.matcher(password);

		// Return if the password
		// matched the ReGex
		return m.matches();
	}

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "root");
		}
		return con;
	}

	public void closeConnection() {
		// TODO Auto-generated method stub
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void recieveTheDeposit(int id, int ammount) {
		// TODO Auto-generated method stub
		if (studentDiposit.containsKey(id)) {
			studentDiposit.put(id, studentDiposit.get(id) + ammount);
		} else {
			studentDiposit.put(id, ammount);
		}
		System.out.println("Deposit Recieved.");
	}

	public void recieveTheBook(String book) {
		// TODO Auto-generated method stub
		if (issuedBooks.containsKey(book)) {
			issuedBooks.remove(book);
			bookShelf.add(book);
			System.out.println(book + " is Recieved.");
		} else {
			System.out.println(book + " is not yet issued.");
		}

	}

	public boolean issueTheBook(String book, int id) {
		// TODO Auto-generated method stub
		if (!issuedBooks.containsKey(book)) {
			bookShelf.remove(book);
			issuedBooks.put(book, id);
			System.out.println("The book is issued.");
			return true;
		} else {
			return false;
		}
	}

	public void getIssuedBooks() {
		int count = 1;
		if (issuedBooks.isEmpty()) {
			System.out.println("There is not books issued.");
		} else {
			for (Map.Entry<String, Integer> a : issuedBooks.entrySet()) {
				String name = getStudentWithID(a.getValue()).getName();
				System.out.println(count + ". Book : " + a.getKey() + " issued to " + name);
				count++;
			}
		}
	}

	public void getAvailBooks() {
		// TODO Auto-generated method stub
		bookShelf.forEach(a -> System.out.println(a));
	}

	public void addNewStudent(Student std, String user, String pass) {
		// TODO Auto-generated method stub

		if (!students.contains(std)) {
			Library obj = new Library();
			students.add(std);
			try {
				if (obj.isUserRegistered(user, LibraryConst.STUDENT.getValue())) {
					obj.updatePasswordInBD(user, pass, LibraryConst.STUDENT.getValue());
				} else {
					obj.resgisterUser(user, pass, LibraryConst.STUDENT.getValue());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

			System.out.println("New student is added with id :" + std.getId() + " and name: " + std.getName());

		} else {
			System.out.println(std.getName() + " is already present.");
		}

	}

	public void getAllPendinDiposit() {
		// TODO Auto-generated method stub
		int count;
		String name;
//		if (studentDiposit.isEmpty()) {
//			students.forEach(a -> System.out.println("Student name : " + a.getName() + ", Ammount Pending:" + 5000));
//		} else {
		for (Student a : students) {
			count = a.getId();
			if (!studentDiposit.containsKey(count)) {
				System.out.println("Student name : " + a.getName() + ", Ammount Pending:" + 5000);
			}
		}
		for (Map.Entry<Integer, Integer> a : studentDiposit.entrySet()) {

			name = getStudentWithID(a.getKey()).getName();
			int pending = a.getValue();
			if (pending < 5000)
				System.out.println("Student name : " + name + ", Ammount Pending:" + (5000 - pending));

		}

//		}
	}

	public void getAllRecievedDiposit() {
		// TODO Auto-generated method stub
		int count = 1;
		if (studentDiposit.isEmpty()) {
			System.out.println("There is no diposit Recieved.");
		} else {
			for (Map.Entry<Integer, Integer> a : studentDiposit.entrySet()) {
				String name = getStudentWithID(a.getKey()).getName();
				System.out.println(count + ". Student name : " + name + ", Ammount :" + a.getValue());
				count++;
			}
		}
	}

	public void getAllStudents() {
		// TODO Auto-generated method stub
		System.out.println("There are " + students.size() + " students currently registered.");
	}

	public Student getStudentWithID(int id) {
		// TODO Auto-generated method stub
		Student obj = null;
		for (Student a : students) {
			if (a.getId() == id) {
				obj = a;
				break;
			}
		}
		return obj;
	}

	public boolean isStudentAvail(int id) {
		// TODO Auto-generated method stub
		return students.stream().anyMatch(a -> a.getId() == id);
	}

	public boolean isBookAvail(String book) {
		// TODO Auto-generated method stub
		return bookShelf.stream().anyMatch(a -> a.equalsIgnoreCase(book));
	}

	public String addNewBooks(String book) {
		// TODO Auto-generated method stub
		boolean isPresent = bookShelf.stream().anyMatch(a -> a.equalsIgnoreCase(book));

		if (!isPresent) {
			if (!issuedBooks.containsKey(book)) {
				bookShelf.add(book);
				return "Added";
			} else {
				Student obj = getStudentWithID(issuedBooks.get(book));
				return "Book is available and issued to Student :" + obj.getName();
			}
		} else {
			return "Book is already Present.";
		}
	}

}
