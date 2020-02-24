package com.ftn.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.projekat.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
	
	Blog findOneByBlogTitle(String title);

}
