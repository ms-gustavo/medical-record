package com.myapp.eletronic_physio_record.entities.dto;

import java.util.List;

public record MedicalRecordResponseDTO(
		Long id,
		String startDate,
		String diagnostic,
		PatientBasicResponseDTO patient,
		List<TreatmentSessionDTO> treatmentSession) {

}
