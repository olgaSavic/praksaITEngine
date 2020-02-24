package com.ftn.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.projekat.dto.BlogDTO;
import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.User;
import com.ftn.projekat.repository.BlogRepository;

@Service
public class BlogService {
	
	@Autowired
	BlogRepository blogRepository ;
	
	// dodavanje novog bloga
	public Blog addNewBlog(BlogDTO dto)  
	{
		
		Blog b = new Blog();
		b.setBlogTitle(dto.getBlogTitle());
		b.setBlogBody(dto.getBlogBody());
		b.setDeleted(false);
		
		// KOMENTAR  -> postaviti userId kod bloga na trenutno ulogovanog korisnika
		
		blogRepository.save(b);
		return b ;
		
	}
	
	// izmena postojeceg bloga
	public Blog editBlog(Long idBlog, BlogDTO dto)
	{
		Blog b = blogRepository.getOne(idBlog);
		
		if (b == null)
		{
			return b ;
		}
		
		b.setBlogTitle(dto.getBlogTitle());
		b.setBlogBody(dto.getBlogBody());
		
		blogRepository.save(b);
		return b ;
		
	}
	
	// brisanje postojeceg bloga
	public boolean deleteBlog(Long id) {
		for (Blog b: blogRepository.findAll())
		{
			if (b.isDeleted() == false) // ukoliko vec nije obrisan
			{
				if (b.getId() == id) // ukoliko je pronasao blog koji zeli da brise
				{
					b.setDeleted(true);
					blogRepository.save(b);
					return true ;
				}
			}
		}
			
		return false; // ne postoji blog sa prosledjenim ID-jem, pa se ne moze ni obrisati
	}
	
	// vrati sve blogove
	public List<Blog> getAllBlogs()
	{
		List<Blog> allBlogs = blogRepository.findAll();
		List<Blog> blogs = new ArrayList<Blog>();
		
		if (allBlogs == null)
		{
			return null ;
		}
		for (Blog b: allBlogs)
		{
			if(b.isDeleted() == false)
			{
				blogs.add(b);
			}
		}
		return blogs ;
	}	
	
	// vrati sve blogove nekog korisnika
	public List<Blog> getBlogsByUser(Long idUser)
	{
		List<Blog> allBlogs = blogRepository.findAll();
		List<Blog> blogs = new ArrayList<Blog>();
		
		if (allBlogs == null)
		{
			return null ;
		}
		for (Blog b: allBlogs)
		{
			if(b.isDeleted() == false && b.getUser().getId().equals(idUser))
			{
				blogs.add(b);
			}
		}
		return blogs ;
	}	
	
	

}
