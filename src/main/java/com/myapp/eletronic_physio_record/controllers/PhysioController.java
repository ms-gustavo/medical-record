package com.myapp.eletronic_physio_record.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.dto.PhysioDTO;
import com.myapp.eletronic_physio_record.services.PhysioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physio")
@RequiredArgsConstructor
@Validated
public class PhysioController {

	@Autowired
	private PhysioService physioService;

	
	@GetMapping("/{id}")
	public ResponseEntity<Physio> findById(@PathVariable Long id){
		return ResponseEntity.ok(physioService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Physio> create(@Valid @RequestBody PhysioDTO data, @RequestHeader("Authorization") String token) {
		token = token.replace("Bearer ", "");
		return ResponseEntity.status(HttpStatus.CREATED).body(physioService.create(data, token));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Physio> update(@PathVariable Long id, @RequestBody PhysioDTO data){
		return ResponseEntity.ok(physioService.update(id, data));

}

}
