package com.ftn.projekat.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.projekat.dto.BlogDTO;
import com.ftn.projekat.dto.CommentDTO;
import com.ftn.projekat.dto.TagDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.Comment;
import com.ftn.projekat.model.Tag;
import com.ftn.projekat.model.User;
import com.ftn.projekat.repository.BlogRepository;
import com.ftn.projekat.repository.CommentRepository;
import com.ftn.projekat.repository.TagRepository;
import com.ftn.projekat.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository ;
	
	@Autowired
	BlogRepository blogRepository ;
	
	public Blog addCommentToBlog(Long idBlog, CommentDTO dto) throws NotFoundException
	{
		Blog b = blogRepository.getOne(idBlog);
		
		if (b == null) {
			return b ;
		}	
		Comment c = new Comment();
		c.setValue(dto.getValue());
		c.setBlog(b);
		c.setDate(LocalDate.now());
		c.setName(dto.getName());
		
		commentRepository.save(c);
		return b ;
	}
	
	public List<Comment> getBlogComments(Long idBlog)
	{
		return commentRepository.findBlogCommentsNotDeleted(idBlog);
	}
	
	
	
	

}
