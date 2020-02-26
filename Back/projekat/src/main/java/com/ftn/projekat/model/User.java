package com.ftn.projekat.model;

import javax.persistence.*;

import com.ftn.projekat.enums.UserType;


@Entity
public class User extends Universal{
	
	private String firstName ;
	private String lastName ; 
	private String email ;
	private String pass ;
	
	@Enumerated(EnumType.STRING)
	private UserType role;
	
	private String imagePath ;


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public User(String firstName, String lastName, String email, String pass) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
	}
	
	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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


	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	
	
	
	
	
	

}
