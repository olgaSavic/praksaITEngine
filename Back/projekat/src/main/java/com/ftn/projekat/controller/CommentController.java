package com.ftn.projekat.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.projekat.dto.CommentDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.Comment;
import com.ftn.projekat.model.Tag;
import com.ftn.projekat.service.CommentService;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")

@RestController
@RequestMapping(value = "comments")
public class CommentController {
	
	@Autowired
	CommentService commentService ;
	
	@PutMapping("/addCommentToBlog/{idBlog}")
	public ResponseEntity<Blog> addCommentToBlog(@PathVariable Long idBlog, @RequestBody @Valid CommentDTO dto, BindingResult result) throws Exception {

		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		Blog b = commentService.addCommentToBlog(idBlog, dto);
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Blog>(b, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/getBlogComments/{idBlog}")
	public ResponseEntity<List<Comment>> getBlogComments(@PathVariable Long idBlog) throws Exception 
	{
		List<Comment> comments = commentService.getBlogComments(idBlog);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

}
