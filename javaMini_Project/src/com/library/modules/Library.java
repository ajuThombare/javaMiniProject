package com.library.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.util.Student;

@SuppressWarnings("serial")
public class Library {

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
	static Map<Integer, Integer> studentDiposit = new HashMap<>();

	static List<Student> students = new ArrayList<>() {
		{
			add(new Student(1, "Ajay", "FY"));
			add(new Student(2, "Nick", "FY"));
			add(new Student(3, "Harry", "SY"));
			add(new Student(4, "Bruce", "TY"));
			add(new Student(10, "Tony", "TY"));
			add(new Student(11, "Peter", "SY"));
			add(new Student(12, "Steve", "FY"));
		}
	};

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

	public void addNewStudent(Student std) {
		// TODO Auto-generated method stub
		students.add(std);
		System.out.println("New student is added with id :" + std.getId() + " and name: " + std.getName());
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
		students.forEach(a -> System.out.println(a));
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
