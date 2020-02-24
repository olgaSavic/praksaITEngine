package com.ftn.projekat.model;

import javax.persistence.*;

import com.ftn.projekat.enums.UserType;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName ;
	private String lastName ; 
	private String email ;
	private String pass ;
	private boolean isDeleted = false;
	
	@Enumerated(EnumType.STRING)
	private UserType role;

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



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}
	
	// private String imagePath ;
	
	
	
	
	
	

}
