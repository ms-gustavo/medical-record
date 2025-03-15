package com.myapp.eletronic_physio_record.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.Patient;
import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.entities.dto.PatientDTO;
import com.myapp.eletronic_physio_record.repositories.PatientRepository;
import com.myapp.eletronic_physio_record.repositories.PhysioRepository;
import com.myapp.eletronic_physio_record.repositories.UserRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PhysioRepository physioRepository;
	@Autowired
	private TokenService tokenService;

	public Patient createPatient(PatientDTO data, String token) {
		String email = tokenService.validateToken(token);
		User user = (User) userRepository.findByEmail(email);
		Physio physio = physioRepository.findById(user.getId())
				.orElseThrow(() -> new RuntimeException("Physio not found"));

		Patient patient = new Patient();
		patient.setName(data.name());
		patient.setBirthDate(data.birthDate());
		patient.setPhone(data.phone());
		patient.setAddress(data.address());

		patient.getPhysios().add(physio);

		return patientRepository.save(patient);

	}

	public List<Patient> getPatientsByPhysio(String token){
		String email = tokenService.validateToken(token);
		User user = (User) userRepository.findByEmail(email);
		Physio physio = physioRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Physio not found"));
		
		return patientRepository.findByPhysiosId(physio.getId());
		
	}
	
}
