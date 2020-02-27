package com.ftn.projekat.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.ftn.projekat.validators.NameTitleConstraint;


@Entity
public class Comment extends Universal {
	
	private String value ;
	private String name ;
	private LocalDate date ;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="blogId", referencedColumnName="id")
	private Blog blog ;

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


	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	

}
