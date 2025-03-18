package com.myapp.eletronic_physio_record.entities.dto;

public record PatientBasicResponseDTO(
		Long id,
		String name,
		String birthDate,
		String phone,
		String address) {

}
