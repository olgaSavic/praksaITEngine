package com.ftn.projekat.dto;

import com.ftn.projekat.validators.NameTitleConstraint;

public class BlogDTO {
	
	@NameTitleConstraint
	private String blogTitle ;
	
	private String blogBody ;
	
	public BlogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogDTO(String blogTitle, String blogBody) {
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
	
	
	
	

}
