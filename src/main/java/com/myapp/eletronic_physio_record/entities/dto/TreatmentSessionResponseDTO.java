package com.myapp.eletronic_physio_record.entities.dto;

public record TreatmentSessionResponseDTO(
		Long id,
		String sessionDate,
		String observations,
		String procedures) {

}
