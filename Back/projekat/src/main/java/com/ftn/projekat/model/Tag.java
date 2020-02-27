package com.ftn.projekat.model;

import javax.persistence.Entity;

@Entity
public class Tag extends Universal{
	
	private String tagName ;

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String tagName) {
		super();
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	

}
