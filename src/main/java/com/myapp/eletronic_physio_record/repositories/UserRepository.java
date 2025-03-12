package com.myapp.eletronic_physio_record.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.myapp.eletronic_physio_record.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	UserDetails findByEmail(String email);
	
}
