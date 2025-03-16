package com.myapp.eletronic_physio_record.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.MedicalRecord;
import com.myapp.eletronic_physio_record.entities.Patient;
import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.dto.MedicalRecordDTO;
import com.myapp.eletronic_physio_record.repositories.MedicalRecordRepository;
import com.myapp.eletronic_physio_record.repositories.PatientRepository;
import com.myapp.eletronic_physio_record.repositories.PhysioRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PhysioRepository physioRepository;
	@Autowired
	private TokenService tokenService;
	
	public MedicalRecord createMedicalRecord(MedicalRecordDTO data, String token) {
		String email = tokenService.validateToken(token);
		Physio physio = (Physio) physioRepository.findByEmail(email);
		if (physio == null) throw new RuntimeException("Physio not found");
		
		Patient patient = (Patient) patientRepository.findById(data.patientId()).orElseThrow(() -> new RuntimeException("Patient not found"));
	
		if (!patient.getPhysios().contains(physio)) throw new RuntimeException("You have no permission to create a medical record for this patient");
		
		MedicalRecord newMedicalRecord = new MedicalRecord();
		newMedicalRecord.setPatient(patient);
		newMedicalRecord.setStartDate(data.startDate());
		newMedicalRecord.setDiagnostic(data.diagnostic());
		
		return medicalRecordRepository.save(newMedicalRecord);
		
	
	}
	
}
