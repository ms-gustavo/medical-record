package com.myapp.eletronic_physio_record.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.repositories.UserRepository;

@Service

public class AuthService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
	}

}
