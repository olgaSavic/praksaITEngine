package com.ftn.projekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Blog extends Universal{
	
	private String blogTitle ;
	private String blogBody ;
	
	@ManyToOne(fetch=FetchType.EAGER) // odmah se ucitava i lista korisnika, za razliku od LAZY, kada se oni ucitavaju na zahtev
	@JoinColumn(name="userId", referencedColumnName="id")
	private User user ;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "Blog_Tag", 
	joinColumns = { @JoinColumn(name = "blog_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "tag_id") }
	  )
	private Set<Tag> tags = new HashSet<Tag>();
	
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



	public Set<Tag> getTags() {
		return tags;
	}



	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	

	
	
	
	
	
	

}
