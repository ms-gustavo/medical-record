package com.myapp.eletronic_physio_record.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.eletronic_physio_record.entities.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	List<MedicalRecord> findByPatientId(Long patientId);
}