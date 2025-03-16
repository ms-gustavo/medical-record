package com.myapp.eletronic_physio_record.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.entities.dto.AuthenticationDTO;
import com.myapp.eletronic_physio_record.entities.dto.LoginResponseDTO;
import com.myapp.eletronic_physio_record.entities.dto.RegisterDTO;
import com.myapp.eletronic_physio_record.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
		
		String token = authService.login(data);
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
		
		User user = authService.register(data);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(user);
	}
	
}