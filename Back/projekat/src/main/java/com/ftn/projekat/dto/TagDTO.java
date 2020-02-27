package com.ftn.projekat.dto;

import com.ftn.projekat.validators.NameTitleConstraint;

public class TagDTO {
	@NameTitleConstraint
	private String tagName ;

	public TagDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TagDTO(String tagName) {
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
