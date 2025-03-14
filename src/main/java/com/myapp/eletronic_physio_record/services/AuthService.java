package com.myapp.eletronic_physio_record.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.User;
import com.myapp.eletronic_physio_record.entities.dto.AuthenticationDTO;
import com.myapp.eletronic_physio_record.entities.dto.RegisterDTO;
import com.myapp.eletronic_physio_record.repositories.UserRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

    public String login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((User) authentication.getPrincipal());
    }
    
    public void register(RegisterDTO data) {
    	UserDetails user = userRepository.findByEmail(data.email());
    	if (user != null) {
            throw new IllegalArgumentException("User already exists");
        }
    	String encryptedPassword = passwordEncoder.encode(data.password());
    	User newUser = new User(data.email(), encryptedPassword, data.role());
        userRepository.save(newUser);
    }
    
}

