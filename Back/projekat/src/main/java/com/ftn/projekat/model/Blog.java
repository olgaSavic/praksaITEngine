package com.ftn.projekat.model;

import javax.persistence.*;


@Entity
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String blogTitle ;
	private String blogBody ;
	private boolean isDeleted = false ;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId", referencedColumnName="id")
	private User user ;
	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Blog(Long id, String blogTitle, String blogBody, boolean isDeleted) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.blogBody = blogBody;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	

}
