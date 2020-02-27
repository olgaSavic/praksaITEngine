package com.ftn.projekat.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.ftn.projekat.validators.NameTitleConstraint;


@Entity
public class Comment extends Universal {
	
	private String value ;
	
	private Date date ;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="blogId", referencedColumnName="id")
	private Blog blog ;
	
	@ManyToOne(fetch=FetchType.EAGER) // odmah se ucitava i lista korisnika, za razliku od LAZY, kada se oni ucitavaju na zahtev
	@JoinColumn(name="userId", referencedColumnName="id")
	private User user ;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	

}
