package com.myapp.eletronic_physio_record.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.entities.dto.PhysioDTO;
import com.myapp.eletronic_physio_record.repositories.PhysioRepository;
import com.myapp.eletronic_physio_record.repositories.UserRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhysioService {

	@Autowired
	private PhysioRepository physioRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	
	public List<Physio> findAll(){
		return physioRepository.findAll();
	}
	
	public Physio findById(Long id) {
		return physioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Physiotherapist not found"));
	}
	
	public Physio create(PhysioDTO data, String token) {
		String email = tokenService.validateToken(token);
	    User user = (User) userRepository.findByEmail(email);
	    if (user == null) {
	        throw new RuntimeException("Usuário não encontrado");
	    }
		Physio physio = new Physio();
		physio.setName(data.name());
		physio.setLicenseNumber(data.licenseNumber());
		physio.setSpeciality(data.speciality());
		physio.setUser(user);
		
		return physioRepository.save(physio);
	}
	
	   public Physio update(Long id, PhysioDTO data) {
		   Physio physio = findById(id);	        
	        physio.setLicenseNumber(data.licenseNumber());
	        physio.setSpeciality(data.speciality());
	        return physioRepository.save(physio);
	    }
}
