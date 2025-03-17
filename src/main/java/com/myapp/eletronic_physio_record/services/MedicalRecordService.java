package com.myapp.eletronic_physio_record.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.MedicalRecord;
import com.myapp.eletronic_physio_record.entities.Patient;
import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.dto.MedicalRecordDTO;
import com.myapp.eletronic_physio_record.repositories.MedicalRecordRepository;
import com.myapp.eletronic_physio_record.repositories.PatientRepository;
import com.myapp.eletronic_physio_record.repositories.PhysioRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

import jakarta.persistence.EntityNotFoundException;
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
		if (physio == null) throw new EntityNotFoundException("Fisioterapeuta não encontrado");
		
		Patient patient = (Patient) patientRepository.findById(data.patientId()).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
	
		if (!patient.getPhysios().contains(physio)) throw new AccessDeniedException("Você não tem permissão para acessar este paciente.");
		
		MedicalRecord newMedicalRecord = new MedicalRecord();
		newMedicalRecord.setPatient(patient);
		newMedicalRecord.setStartDate(data.startDate());
		newMedicalRecord.setDiagnostic(data.diagnostic());
		
		return medicalRecordRepository.save(newMedicalRecord);
		
	}
	
	public List<MedicalRecord> findByPatient(Long patientId, String token) {
		String email = tokenService.validateToken(token);
		Physio physio = physioRepository.findByEmail(email);
		if (physio == null) throw new EntityNotFoundException("Fisioterapeuta não encontrado");
		
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
		
		if(!patient.getPhysios().contains(physio)) throw new AccessDeniedException("Você não tem permissão para acessar este paciente.");
		
		
		return medicalRecordRepository.findByPatientId(patientId);
	}
	
}
