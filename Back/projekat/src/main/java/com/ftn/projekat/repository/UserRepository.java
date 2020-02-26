package com.ftn.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.projekat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneByEmail(String email);
	
	@Query(value = "SELECT * FROM user as u where u.id = :id AND u.is_deleted = false", nativeQuery = true)
	User findByIdAndNotDeleted(Long id);
	
	@Query(value = "SELECT * FROM user as u where u.role = 'BLOGER' and u.is_deleted = FALSE", nativeQuery = true)
	List<User> findAllBlogersNotDeleted();
	
	@Query(value = "SELECT * FROM user as u where u.is_deleted = FALSE", nativeQuery = true)
	List<User> findAllNotDeleted();
	
	@Query(value = "SELECT * FROM user as u where u.role = 'ADMIN' and u.is_deleted = FALSE", nativeQuery = true)
	List<User> findAllAdminsNotDeleted();
	
	

}
