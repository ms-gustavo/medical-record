package com.myapp.eletronic_physio_record.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.eletronic_physio_record.entities.TreatmentSession;

@Repository
public interface TreatmentSessionRepository extends JpaRepository<TreatmentSession, Long> {
	List<TreatmentSession> findByMedicalRecordId(Long medicalRecordId);
}

