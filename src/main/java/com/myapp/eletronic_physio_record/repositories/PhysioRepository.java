package com.myapp.eletronic_physio_record.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.User;

@Repository
public interface PhysioRepository extends JpaRepository<Physio, Long> {
	Optional<Physio> findByUser(User user);
}