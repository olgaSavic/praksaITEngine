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
import com.ftn.projekat.repository.BlogRepository;
import com.ftn.projekat.repository.TagRepository;

import javassist.NotFoundException;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepository ;
	
	@Autowired
	BlogRepository blogRepository ;
	
	public List<Tag> getAllTags()
	{
		return tagRepository.findAllNotDeleted();
	}
	
	public Blog addTagToBlog(Long idBlog, TagDTO dto) throws NotFoundException
	{
		Blog b = blogRepository.getOne(idBlog);
		
		if (b == null)
		{
			return b ;
		}
		
		Tag t = new Tag();
		t.setTagName(dto.getTagName());
		Set<Tag> blogTags = b.getTags();
		blogTags.add(t);

		blogRepository.save(b);
		return b ;
		
	}
	
	public Set<Tag> returnTagsOfBlog(Long blogId)
	{
		Blog b = blogRepository.getOne(blogId);
		
		return b.getTags() ;
	}
	
	
}
