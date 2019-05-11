package ch.bfh.red.backend.models;

import java.io.Serializable;

public class User extends Person {
	private final String username;
	private final String password;

	public User(String username, String password, String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
		this.username = username;
		this.password = password;
	}

	public String getusername() {
		return username;
	}

	public String getpassword() {
		return password;
	}

}