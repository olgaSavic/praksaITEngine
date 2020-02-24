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
public class UserSevice {
	
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
		
		// KOMENTAR -> dodati izmenu jos necega, za sad moze da menja samo ime i prezime
		userRepository.save(korisnik);
		return korisnik;
		
	}
	
	
	// admin brise postojeceg blogera - LOGICKI
	public boolean deleteUser(Long idUser) // id korisnika koji se brise
	{
	
		for (User u: userRepository.findAll())
		{
			if (u.isDeleted() == false) // ukoliko vec nije obrisan
			{
				if (u.getId() == idUser) // ukoliko je pronasao korisnika kog zeli da brise
				{
					u.setDeleted(true);
					userRepository.save(u);
					return true ;
				}
			}
		}
			
		return false; // ne postoji user sa prosledjenim ID-jem, pa se ne moze ni obrisati
	}
	
	
	// vrati sve korisnike
	public List<User> getAllUsers()
	{
		List<User> allUsers = userRepository.findAll();
		List<User> users = new ArrayList<User>();
		
		if (allUsers == null)
		{
			return null ;
		}
		for (User u: allUsers)
		{
			if(u.isDeleted() == false)
			{
				users.add(u);
			}
		}
		return users ;
	}
	
	// vrati sve blogere
	public List<User> getAllBlogers()
	{
		List<User> allUsers = userRepository.findAll();
		List<User> allBlogers = new ArrayList<User>();
		
		if (allUsers == null)
		{
			return null ;
		}
		for (User u: allUsers)
		{
			// vrati samo blogere koji nisu obrisani
			if (u.getRole().toString().equals("BLOGER") && u.isDeleted() == false)
			{
				allBlogers.add(u);
			}
		}
		return allBlogers ;
	}
	
	// vrati sve admine
	public List<User> getAllAdmins()
	{
		List<User> allUsers = userRepository.findAll();
		List<User> allAdmins = new ArrayList<User>();
		
		if (allUsers == null)
		{
			return null ;
		}
		for (User u: allUsers)
		{
			// vrati samo admine koji nisu obrisani
			if (u.getRole().toString().equals("ADMIN") && u.isDeleted() == false)
			{
				allAdmins.add(u);
			}
		}
		return allAdmins ;
	}
}
