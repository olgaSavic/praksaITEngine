package com.ftn.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.projekat.model.Comment;
import com.ftn.projekat.model.Tag;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	Comment findOneByValue(String value);
	
	@Query(value = "SELECT * FROM comment as c WHERE c.blog_id = :idBlog AND c.is_deleted = FALSE", nativeQuery = true)
	List<Comment> findBlogCommentsNotDeleted(Long idBlog);

}
