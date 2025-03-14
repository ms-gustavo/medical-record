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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<User>> listUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		if (!userRepository.existsById(id)) return ResponseEntity.notFound().build();
		
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
