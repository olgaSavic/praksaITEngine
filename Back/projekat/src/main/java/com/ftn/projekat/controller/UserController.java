package com.ftn.projekat.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")

@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired
	UserService userService ;
	
	@GetMapping("/getCurrentUser")
	public ResponseEntity<User> getCurrentUser() {
		User korisnik = userService.getCurrentUser();
		return new ResponseEntity<User>(korisnik, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getAllAdmins", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllAdmins() {	
		List<User> administrators = userService.getAllAdmins();
		return new ResponseEntity<>(administrators, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getAllBlogers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllBlogers() {	
		List<User> blogers = userService.getAllBlogers2();
		return new ResponseEntity<>(blogers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {	
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editUser/{id}")
	public ResponseEntity<User> editUser( @PathVariable Long id, @RequestBody @Valid UserDTO dto, BindingResult result) throws NoSuchAlgorithmException {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		User korisnik = userService.editUser(id, dto);
		
		if (korisnik == null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<User>(korisnik, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editUserPassword/{email}")
	public ResponseEntity<User> editUserPassword( @PathVariable String email, @RequestBody @Valid UserDTO dto, BindingResult result) throws NoSuchAlgorithmException {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		User korisnik = userService.editUserPassword(email, dto);
		if (korisnik == null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<User>(korisnik, HttpStatus.OK);
		}
	}
	
	
	@PutMapping("/editCurrentUser")
	public ResponseEntity<User> editCurrentUser(@RequestBody @Valid UserDTO dto, BindingResult result) throws NoSuchAlgorithmException {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		User k = userService.getCurrentUser();
		if (k == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		User korisnik = userService.editCurrentUser(k.getId(), dto);
		if (korisnik == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<User>(korisnik, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO korisnik, BindingResult result) throws NoSuchAlgorithmException 
	{
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		String povVrFunkcije = userService.register(korisnik);
		
		if(povVrFunkcije == "ok") // ne postoji korisnik sa tim email-om 
		{
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} 
		else // vraca  "greska", jer vec postoji korisnik sa tim email-om
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/createNewUser")
	public ResponseEntity<User> createNewUser(@RequestBody @Valid UserDTO dto, BindingResult result) throws MailException, InterruptedException, MessagingException 
	{
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		String povVrFunkcije = userService.createNewUser(dto);
		if(povVrFunkcije == "ok") // ne postoji korisnik sa tim email-om 
		{
			User korisnik = userService.findByEmail(dto.getEmail());
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} 
		else // vraca  "greska", jer vec postoji korisnik sa tim email-om
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteUser/{idUser}")
	public boolean deleteUser(@PathVariable Long idUser) throws Exception 
	{
		boolean response = userService.deleteUser3(idUser);
		return response; // TRUE - uspesno obrisan, FALSE - nije obrisan (nije pronadjen)
	}
	
	@GetMapping("/returnUserById/{idUser}")
	public ResponseEntity<User> returnUserById(@PathVariable Long idUser) throws Exception 
	{		
		User v = userService.returnUserById(idUser);
		if (v == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(v, HttpStatus.OK);
		}

	}

}
