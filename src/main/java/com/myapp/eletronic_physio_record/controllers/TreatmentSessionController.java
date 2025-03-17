package com.myapp.eletronic_physio_record.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.eletronic_physio_record.entities.TreatmentSession;
import com.myapp.eletronic_physio_record.entities.dto.TreatmentSessionDTO;
import com.myapp.eletronic_physio_record.services.TreatmentSessionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physio/patients/medical-records/treatment-session")
@RequiredArgsConstructor
@Validated
public class TreatmentSessionController {

	
	@Autowired
	private TreatmentSessionService treatmentSessionService;
	
	@PostMapping
	public ResponseEntity<TreatmentSession> createTreatmentSession(@Valid @RequestBody TreatmentSessionDTO data, @RequestHeader("Authorization") String token){
		token = token.replace("Bearer ", "");
		return ResponseEntity.ok(treatmentSessionService.createTreatmentSession(data, token));
	}
}
