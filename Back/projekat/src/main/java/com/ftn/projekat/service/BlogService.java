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
	
	@Autowired
	UserService userService ;
	
	// dodavanje novog bloga
	public Blog addNewBlog(BlogDTO dto)  
	{
		
		Blog b = new Blog();
		b.setBlogTitle(dto.getBlogTitle());
		b.setBlogBody(dto.getBlogBody());
		b.setDeleted(false);
		
		User korisnik = userService.getCurrentUser();
		b.setUser(korisnik);
		
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
		Blog u = blogRepository.findByIdAndNotDeleted(id);
		if (u == null)
		{ 
			return false ;
		}
		else 
		{
			u.setDeleted(true);
			blogRepository.save(u);
			return true ;
		}
	}
	
	// vrati sve blogove
	public List<Blog> getAllBlogs()
	{
	
		return blogRepository.findAllNotDeleted() ;
	}	
	
	// vrati sve blogove nekog korisnika
	public List<Blog> getBlogsByUser(Long idUser)
	{
		return blogRepository.findAllByMeNotDeleted(idUser) ;
	}	
	
	// korisnik ima dozvolu da brise/menja blog samo ako je njegov
	public boolean canEditDeleteBlog(Long idBlog) 
	{	
		Long idUser = userService.getCurrentUser().getId();
		Blog blog = blogRepository.findByIdByMe(idBlog, idUser);
		
		if (blog == null)
		{
			return false ;
		}
		else
		{
			return true ;
		}
	}
	
	public Blog returnBlogById(Long id)
	{
		return blogRepository.getOne(id);
	}
	
	

}
