package com.myapp.eletronic_physio_record.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.eletronic_physio_record.entities.Patient;
import com.myapp.eletronic_physio_record.entities.dto.PatientDTO;
import com.myapp.eletronic_physio_record.services.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physio/patients")
@RequiredArgsConstructor
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> findById(@PathVariable Long id){
		return ResponseEntity.ok(patientService.findById(id));
	}
	
	@PostMapping
    public ResponseEntity<Patient> create(@RequestBody PatientDTO data, @RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(data, token));
    }
	
	@GetMapping
    public ResponseEntity<List<Patient>> getAll(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok(patientService.getPatientsByPhysio(token));
    }
	
}

