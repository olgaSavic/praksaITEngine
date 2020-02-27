package com.ftn.projekat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.ftn.projekat.dto.CommentDTO;
import com.ftn.projekat.dto.TagDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.Tag;
import com.ftn.projekat.model.User;
import com.ftn.projekat.service.BlogService;
import com.ftn.projekat.service.UserService;
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")

@RestController
@RequestMapping(value = "blogs")
public class BlogController {

	
	@Autowired
	BlogService blogService ;
	
	@Autowired
	UserService userService ;
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@PostMapping("/addNewBlog")
	public ResponseEntity<Blog> addNewBlog(@RequestBody BlogDTO dto) 
	{
		Blog b = blogService.addNewBlog(dto);
		return new ResponseEntity<Blog>(b, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@PutMapping("/editBlog/{idBlog}")
	public ResponseEntity<Blog> editBlog(@PathVariable Long idBlog, @RequestBody @Valid BlogDTO dto, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		Blog b = blogService.editBlog(idBlog, dto);
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Blog>(b, HttpStatus.OK);
		}
		
	}
	
	// Tags
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@PutMapping("/addTagToBlog/{idBlog}")
	public ResponseEntity<Blog> addTagToBlog(@PathVariable Long idBlog, @RequestBody @Valid TagDTO dto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		Blog b = blogService.addTagToBlog(idBlog, dto);
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Blog>(b, HttpStatus.OK);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/getBlogsByUser/{idUser}")
	public ResponseEntity<List<Blog>> getBlogsByUser(@PathVariable Long idUser) throws Exception 
	{
		ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.getBlogsByUser(idUser);
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@PutMapping("/searchBlogsByTag")
	public ResponseEntity<List<Blog>> searchBlogsByTag(@RequestBody @Valid TagDTO dto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		List<Blog> b = blogService.searchBlogsByTag(dto);
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Blog>>(b, HttpStatus.OK);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@PutMapping("/searchMyBlogsByTag")
	public ResponseEntity<List<Blog>> searchMyBlogsByTag(@RequestBody @Valid TagDTO dto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		List<Blog> b = blogService.searchMyBlogsByTag(dto);
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Blog>>(b, HttpStatus.OK);
		}
		
	}
	
	// **************************************
	
	// Comment
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@PutMapping("/addCommentToBlog/{idBlog}")
	public ResponseEntity<Blog> addCommentToBlog(@PathVariable Long idBlog, @RequestBody CommentDTO dto) throws Exception {

		Blog b = blogService.addCommentToBlog(idBlog, dto);
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Blog>(b, HttpStatus.OK);
		}
		
	}

	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@DeleteMapping("/deleteBlog/{idBlog}")
	public boolean deleteBlog(@PathVariable Long idBlog) throws Exception 
	{
		boolean response = blogService.deleteBlog(idBlog);
		return response; // TRUE - uspesno obrisan, FALSE - nije obrisan (nije pronadjen)
	}
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs() throws Exception 
	{
		ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.getAllBlogs();
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/returnTagsOfBlog/{idBlog}")
	public ResponseEntity<Set<Tag>> returnTagsOfBlog(@PathVariable Long idBlog) throws Exception 
	{
		Set<Tag> tags = blogService.returnTagsOfBlog(idBlog);
		return new ResponseEntity<Set<Tag>>(tags, HttpStatus.OK);
	}
	
	// vraca listu blogova trenutno ulogovanog korisnika
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/getMyBlogs")
	public ResponseEntity<List<Blog>> getMyBlogs() throws Exception 
	{
		ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.getBlogsByUser(userService.getCurrentUser().getId());
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	// provera da li je vozilo rezervisano
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/canEditDeleteBlog/{id}")
	public boolean canEditDeleteBlog(@PathVariable Long id) 
	{
		boolean mine = blogService.canEditDeleteBlog(id);
		return mine; // mine -> TRUE ako je nasao medju blogovima moj, inace false
	}	
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/returnBlogById/{id}")
	public ResponseEntity<Blog> returnBlogById(@PathVariable Long id) throws Exception 
	{		
		Blog v = blogService.returnBlogById(id);
		if (v == null) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<>(v, HttpStatus.OK);
		}

	}

	

}
