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
public class BlogService {
	
	@Autowired
	BlogRepository blogRepository ;
	
	@Autowired
	TagRepository tagRepository ;
	
	@Autowired
	CommentRepository commentRepository ;
	
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
		
		b.setDate(LocalDate.now());
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
	
	public ArrayList<Blog> searchBlogsByTag(TagDTO dto)
	{
		List <Blog> blogs = blogRepository.findAllNotDeleted();
		ArrayList<Blog> good = new ArrayList<Blog>();
		
		for (Blog b: blogs) {
			for (Tag t: b.getTags())
			{
				if (t.getTagName().equals(dto.getTagName()))
				{
					good.add(b);
				}
			}
		}
		
		return good ;
	}
	
	public ArrayList<Blog> searchMyBlogsByTag(TagDTO dto)
	{
		Long idUser = userService.getCurrentUser().getId();
		List <Blog> blogs = blogRepository.findAllByMeNotDeleted(idUser);
		ArrayList<Blog> good = new ArrayList<Blog>();
		
		for (Blog b: blogs) {
			for (Tag t: b.getTags())
			{
				if (t.getTagName().equals(dto.getTagName()))
				{
					good.add(b);
				}
			}
		}
		
		return good ;
	}
	
	

}
