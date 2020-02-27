package com.ftn.projekat.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.projekat.dto.BlogDTO;
import com.ftn.projekat.dto.TagDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.Tag;
import com.ftn.projekat.model.User;
import com.ftn.projekat.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepository ;
	
	public Tag addNewTag(TagDTO dto)  
	{
		
		Tag t = new Tag();
		t.setTagName(dto.getTagName());
		t.setDeleted(false);

		tagRepository.save(t);
		return t ;
		
	}
	
	public List<Tag> getAllTags()
	{
		return tagRepository.findAllNotDeleted();
	}
	
	
}
