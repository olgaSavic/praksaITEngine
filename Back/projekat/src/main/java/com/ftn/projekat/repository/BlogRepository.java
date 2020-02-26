package com.ftn.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.projekat.model.Blog;
import com.ftn.projekat.model.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
	
	Blog findOneByBlogTitle(String title);
	
	@Query(value = "SELECT * FROM blog as b where b.id = :id AND b.is_deleted = false", nativeQuery = true)
	Blog findByIdAndNotDeleted(Long id);
	
	@Query(value = "SELECT * FROM blog as b where b.is_deleted = FALSE", nativeQuery = true)
	List<Blog> findAllNotDeleted();
	
	@Query(value = "SELECT * FROM blog as b where b.user_id = :idUser and b.is_deleted = FALSE", nativeQuery = true)
	List<Blog> findAllByMeNotDeleted(Long idUser);
	
	@Query(value = "SELECT * FROM blog as b where b.id = :idBlog and b.user_id = :idUser and b.is_deleted = FALSE", nativeQuery = true)
	Blog findByIdByMe(Long idBlog, Long idUser);

}
