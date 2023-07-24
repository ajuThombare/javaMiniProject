package com.library.util;

public enum LibraryConst {
	LIBRARIAN("librarian"), TEACHER("teacher"), STUDENT("student");

	private String val;

	LibraryConst(String string) {
		// TODO Auto-generated constructor stub
		this.val = string;
	}

	public String getValue() {
		return val;
	}

}
