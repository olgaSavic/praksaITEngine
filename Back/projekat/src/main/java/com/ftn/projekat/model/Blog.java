package com.ftn.projekat.model;

import javax.persistence.*;


@Entity
public class Blog extends Universal{
	
	private String blogTitle ;
	private String blogBody ;
	
	@ManyToOne(fetch=FetchType.EAGER) // odmah se ucitava i lista korisnika, za razliku od LAZY, kada se oni ucitavaju na zahtev
	@JoinColumn(name="userId", referencedColumnName="id")
	private User user ;
	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Blog(String blogTitle, String blogBody) {
		super();
		this.blogTitle = blogTitle;
		this.blogBody = blogBody;
	}



	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogBody() {
		return blogBody;
	}

	public void setBlogBody(String blogBody) {
		this.blogBody = blogBody;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	

}
