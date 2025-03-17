package com.myapp.eletronic_physio_record.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.Patient;
import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.dto.PatientDTO;
import com.myapp.eletronic_physio_record.repositories.PatientRepository;
import com.myapp.eletronic_physio_record.repositories.PhysioRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PhysioRepository physioRepository;
	@Autowired
	private TokenService tokenService;

	
	public Patient findById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
	}
	
	@Transactional
	public Patient createPatient(PatientDTO data, String token) {
		String email = tokenService.validateToken(token);
		System.out.println("============ EMAIL =========");
		System.out.println("EMAIL: " + email);
		System.out.println("============ EMAIL =========");
		Physio physio = (Physio) physioRepository.findByEmail(email);
		if (physio == null) throw new EntityNotFoundException("Fisioterapeuta não encontrado");
		

		Patient patient = new Patient();
		patient.setName(data.name());
		patient.setBirthDate(data.birthDate());
		patient.setPhone(data.phone());
		patient.setAddress(data.address());

		patient.getPhysios().add(physio);
		physio.addPatient(patient);
		
		Patient savedPatient = patientRepository.save(patient);		
		return savedPatient;

	}

	public List<Patient> getPatientsByPhysio(String token){
		String email = tokenService.validateToken(token);
		Physio physio = (Physio) physioRepository.findByEmail(email);
		if (physio == null) throw new EntityNotFoundException("Fisioterapeuta não encontrado");
		
		return patientRepository.findByPhysiosId(physio.getId());
		
	}
	
}
