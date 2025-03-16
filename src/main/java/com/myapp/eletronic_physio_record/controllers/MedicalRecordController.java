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

import com.myapp.eletronic_physio_record.entities.MedicalRecord;
import com.myapp.eletronic_physio_record.entities.dto.MedicalRecordDTO;
import com.myapp.eletronic_physio_record.services.MedicalRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physio/patients/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@PostMapping
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecordDTO data, @RequestHeader("Authorization") String token) {
		token = token.replace("Bearer ", "");
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.createMedicalRecord(data, token));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<MedicalRecord>> findById(@PathVariable Long id,@RequestHeader("Authorization") String token){
		token = token.replace("Bearer ", "");
		return ResponseEntity.ok(medicalRecordService.findByPatient(id, token));
	}
}
