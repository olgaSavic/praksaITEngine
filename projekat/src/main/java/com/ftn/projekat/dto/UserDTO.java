package com.ftn.projekat.dto;

public class UserDTO 
{
	
	private String firstName ;
	private String lastName ; 
	private String email ;
	private String pass ;
	
	private String role ;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String firstName, String lastName, String email, String pass, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	
}
