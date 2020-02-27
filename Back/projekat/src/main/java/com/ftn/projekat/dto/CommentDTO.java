package com.ftn.projekat.dto;

import com.ftn.projekat.model.Universal;

public class CommentDTO extends Universal{
	
	private String value ;

	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDTO(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	

}
