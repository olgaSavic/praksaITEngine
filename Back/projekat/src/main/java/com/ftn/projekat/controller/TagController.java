package com.ftn.projekat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ftn.projekat.dto.TagDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.Tag;
import com.ftn.projekat.model.User;
import com.ftn.projekat.service.BlogService;
import com.ftn.projekat.service.TagService;
import com.ftn.projekat.service.UserService;
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")

@RestController
@RequestMapping(value = "tags")
public class TagController {
	
	@Autowired
	TagService tagService ;
	
	@PreAuthorize("hasRole('ROLE_BLOGER')")
	@GetMapping("/getAllTags")
	public ResponseEntity<List<Tag>> getAllTags() throws Exception 
	{
		ArrayList<Tag> tags = (ArrayList<Tag>) tagService.getAllTags();
		return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	}

}
