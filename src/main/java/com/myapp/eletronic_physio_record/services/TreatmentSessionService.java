package com.myapp.eletronic_physio_record.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.eletronic_physio_record.entities.MedicalRecord;
import com.myapp.eletronic_physio_record.entities.Patient;
import com.myapp.eletronic_physio_record.entities.Physio;
import com.myapp.eletronic_physio_record.entities.TreatmentSession;
import com.myapp.eletronic_physio_record.entities.dto.TreatmentSessionDTO;
import com.myapp.eletronic_physio_record.repositories.MedicalRecordRepository;
import com.myapp.eletronic_physio_record.repositories.PatientRepository;
import com.myapp.eletronic_physio_record.repositories.PhysioRepository;
import com.myapp.eletronic_physio_record.repositories.TreatmentSessionRepository;
import com.myapp.eletronic_physio_record.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreatmentSessionService {

	
	@Autowired
	private TreatmentSessionRepository treatmentSessionRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PhysioRepository physioRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private TokenService tokenService;
	
	public TreatmentSession createTreatmentSession(TreatmentSessionDTO data, String token) {
		String email = tokenService.validateToken(token);
		Physio physio = (Physio) physioRepository.findByEmail(email);
		if (physio == null) throw new RuntimeException("Physio not Found");
		
		MedicalRecord medicalRecord = medicalRecordRepository.findById(data.medicalRecordId())
                .orElseThrow(() -> new RuntimeException("Medical Record not found"));
		
		Patient patient = medicalRecord.getPatient();
		
		if (!patient.getPhysios().contains(physio)) {
            throw new RuntimeException("You have no authorization to add sessions to this patient.");
        }
		
		 TreatmentSession treatmentSession = new TreatmentSession();
	        treatmentSession.setMedicalRecord(medicalRecord);
	        treatmentSession.setSessionDate(data.sessionDate());
	        treatmentSession.setObservations(data.observations());
	        treatmentSession.setProcedures(data.procedures());

	        return treatmentSessionRepository.save(treatmentSession);
	}
}
