package com.library.util;

public class Student {
	private int id;
	private String name;
	private String clas;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String clas) {
		super();
		this.id = id;
		this.name = name;
		this.clas = clas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", clas=" + clas + "]";
	}

}
