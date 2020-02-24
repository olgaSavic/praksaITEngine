package com.ftn.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.projekat.dto.BlogDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.service.BlogService;
import com.ftn.projekat.service.UserService;

@RestController
@RequestMapping(value = "/api/blogs")
public class BlogController {
	
	@Autowired
	BlogService blogService ;
	
	@Autowired
	UserService userService ;
	
	@PostMapping("/addNewBlog")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Blog> addNewBlog(@RequestBody BlogDTO dto) 
	{
		Blog b = blogService.addNewBlog(dto);
		return new ResponseEntity<Blog>(b, HttpStatus.OK);
		
	}
	
	@PutMapping("/editBlog/{idBlog}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Blog> editBlog(@PathVariable Long idBlog, @RequestBody BlogDTO dto) throws Exception {

		Blog b = blogService.editBlog(idBlog, dto);
		
		if (b == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		else
		{
			return new ResponseEntity<Blog>(b, HttpStatus.OK);
		}
		
	}

	@DeleteMapping("/deleteBlog/{idBlog}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteBlog(@PathVariable Long idBlog) throws Exception 
	{
		boolean response = blogService.deleteBlog(idBlog);
		return response; // TRUE - uspesno obrisan, FALSE - nije obrisan (nije pronadjen)
	}
	
	@GetMapping("/getAllBlogs")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Blog>> getAllBlogs() throws Exception 
	{
		ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.getAllBlogs();
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@GetMapping("/getBlogsByUser/{idUser}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Blog>> getBlogsByUser(@PathVariable Long idUser) throws Exception 
	{
		ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.getBlogsByUser(idUser);
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	// vraca listu blogova trenutno ulogovanog korisnika
	@GetMapping("/getMyBlogs")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Blog>> getMyBlogs() throws Exception 
	{
		ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.getBlogsByUser(userService.getCurrentUser().getId());
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	

}
