package com.ftn.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.projekat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneByEmail(String email);

}
