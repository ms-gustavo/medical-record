package com.myapp.eletronic_physio_record.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.eletronic_physio_record.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {}

