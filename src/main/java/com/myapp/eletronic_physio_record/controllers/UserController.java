package com.myapp.eletronic_physio_record.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.repositories.UserRepository;
import com.myapp.eletronic_physio_record.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> listUsers(){
		return ResponseEntity.ok(userService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
