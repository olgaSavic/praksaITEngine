package com.ftn.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
	
	Tag findOneByTagName(String name);
	
	@Query(value = "SELECT * FROM tag as t WHERE t.blog_id = :idBlog AND t.is_deleted = FALSE", nativeQuery = true)
	List<Tag> findBlogTagsNotDeleted(Long idBlog);
	
	@Query(value = "SELECT * FROM tag as t where t.is_deleted = FALSE", nativeQuery = true)
	List<Tag> findAllNotDeleted();

}
