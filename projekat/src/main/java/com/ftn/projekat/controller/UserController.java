package com.ftn.projekat.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.projekat.dto.UserDTO;
import com.ftn.projekat.model.User;
import com.ftn.projekat.service.UserService;


@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired
	UserService userService ;
	
	@GetMapping("/getCurrentUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<User> getCurrentUser() {
		User korisnik = userService.getCurrentUser();
		return new ResponseEntity<User>(korisnik, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllAdmins", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getAllAdmins() {	
		List<User> administrators = userService.getAllAdmins();
		return new ResponseEntity<>(administrators, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllBlogers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getAllBlogers() {	
		List<User> blogers = userService.getAllBlogers();
		return new ResponseEntity<>(blogers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getAllUsers() {	
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PutMapping("/editUser/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<User> editUser( @PathVariable Long id, @RequestBody UserDTO dto) throws NoSuchAlgorithmException {
		User korisnik = userService.editUser(id, dto);
		return new ResponseEntity<User>(korisnik, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST, consumes="application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO korisnik) throws NoSuchAlgorithmException 
	{
		
		String povVrFunkcije = userService.register(korisnik);
		
		if(povVrFunkcije == "ok") // ne postoji korisnik sa tim email-om 
		{
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} 
		else // vraca  "greska", jer vec postoji korisnik sa tim email-om
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // mozda je bolje vratiti OK
		}
	}
	
	@PostMapping("/createNewUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<User> createNewUser(@RequestBody UserDTO dto) 
	{
		String povVrFunkcije = userService.createNewUser(dto);
		if(povVrFunkcije == "ok") // ne postoji korisnik sa tim email-om 
		{
			User korisnik = userService.findByEmail(dto.getEmail());
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} 
		else // vraca  "greska", jer vec postoji korisnik sa tim email-om
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // mozda je bolje vratiti OK
		}
		
	}
	
	@DeleteMapping("/deleteUser/{idUser}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteUser(@PathVariable Long idUser) throws Exception 
	{
		boolean response = userService.deleteUser(idUser);
		return response; // TRUE - uspesno obrisan, FALSE - nije obrisan (nije pronadjen)
	}

}
