package com.myapp.eletronic_physio_record.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.entities.dto.AuthenticationDTO;
import com.myapp.eletronic_physio_record.entities.dto.RegisterDTO;
import com.myapp.eletronic_physio_record.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var authentication = this.authenticationManager.authenticate(usernamePassword);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
		
		if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.email(), encryptedPassword, data.role());
		
		this.userRepository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
	
}
