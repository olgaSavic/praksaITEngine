package com.ftn.projekat.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.projekat.dto.UserDTO;
import com.ftn.projekat.enums.UserType;
import com.ftn.projekat.model.User;
import com.ftn.projekat.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


	// registracija za admina (login ide preko spring security-ja)
	public String register(UserDTO korisnik) // zbog enkripcije
	{
		
		User k1 = userRepository.findOneByEmail(korisnik.getEmail());
		
		if(k1 != null) // ukoliko vec postoji korisnik sa tim mejlom
			return "greska";
		else 
		{
			String tempPassword = "";
		
			tempPassword = encoder.encode(korisnik.getPass());
			User k = new User(korisnik.getFirstName(), korisnik.getLastName(), korisnik.getEmail(), tempPassword);
			k.setRole(UserType.ADMIN); // moze se registrovati samo adnin
			k.setDeleted(false);
			
			// cuvanje u bazu
			userRepository.save(k);
			return "ok";
		}
	}
	
	// admin kreira novog blogera
	public String createNewUser(UserDTO korisnik)
	{
		User k1 = userRepository.findOneByEmail(korisnik.getEmail());
		
		if(k1 != null) // ukoliko vec postoji korisnik sa tim mejlom
			return "greska";
		else 
		{
			String tempPassword = "";
		
			tempPassword = encoder.encode(korisnik.getPass());
			User k = new User(korisnik.getFirstName(), korisnik.getLastName(), korisnik.getEmail(), tempPassword);
			k.setRole(UserType.BLOGER); // admin dodaje blogera
			k.setDeleted(false);
			
			// cuvanje u bazu
			userRepository.save(k);
			return "ok";
		}
	}
	
	// admin menja postojeceg blogera
	// id -. id korisnika kog menja, dto -> dto za koji menja
	public User editUser(Long id, UserDTO dto) throws NoSuchAlgorithmException {
		User korisnik = userRepository.getOne(id);
		
		// na osnovu id-ja je nasao korisnika koga menja
		korisnik.setFirstName(dto.getFirstName());
		korisnik.setLastName(dto.getLastName());
		korisnik.setEmail(dto.getEmail());
		String tempPass = encoder.encode(dto.getPass());
		korisnik.setPass(tempPass);
		
		userRepository.save(korisnik);
		return korisnik;
		
	}
	
	// admin/bloger menja svoj profil
	public User editCurrentUser(Long id, UserDTO dto) throws NoSuchAlgorithmException {
		User korisnik = userRepository.getOne(id);
		
		// na osnovu id-ja je nasao korisnika koga menja
		korisnik.setFirstName(dto.getFirstName());
		korisnik.setLastName(dto.getLastName());
		String tempPass = encoder.encode(dto.getPass());
		korisnik.setPass(tempPass);
		
		userRepository.save(korisnik);
		return korisnik;
		
	}
	
	// logicko brisanje blogera
	public boolean deleteUser3(Long idUser) // id korisnika koji se brise
	{
		User u = userRepository.findByIdAndNotDeleted(idUser);
		if (u == null)
		{ 
			return false ;
		}
		else 
		{
			u.setDeleted(true);
			userRepository.save(u);
			return true ;
		}
	}
	
	
	// vrati sve korisnike
	public List<User> getAllUsers()
	{
		return userRepository.findAllNotDeleted();
	}
	
	
	public List<User> getAllBlogers2()
	{
		return userRepository.findAllBlogersNotDeleted();
	}

	
	// vrati sve admine
	public List<User> getAllAdmins()
	{
		return userRepository.findAllAdminsNotDeleted() ;
	}
	
	// metoda koja vraca trenutno ulogovanog korisnika
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User k = userRepository.findOneByEmail(principal.toString());
		String email = k.getEmail();
		return k;
	}
	
	// pomocne metode
	public User findByEmail(String email)
	{
		User u = userRepository.findOneByEmail(email);
		return u ;
	}
	
	public User returnUserById(Long id)
	{
		return userRepository.getOne(id);
	}
}
