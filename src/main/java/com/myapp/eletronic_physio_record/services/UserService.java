package com.myapp.eletronic_physio_record.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public void delete(Long id) {
		userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		
		try {
			userRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir o usuário. Existem registros associados.");
		}
		
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
	}
}
