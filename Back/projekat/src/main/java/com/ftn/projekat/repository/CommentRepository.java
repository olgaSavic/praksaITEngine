package com.ftn.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.projekat.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	Comment findOneByValue(String value);

}
