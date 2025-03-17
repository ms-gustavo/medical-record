package com.myapp.eletronic_physio_record.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record MedicalRecordDTO(
		@NotBlank(message = "O campo 'startDate' é obrigatório.") String startDate,
		@NotBlank(message = "O campo 'diagnostic' é obrigatório.") String diagnostic,
		@NotBlank(message = "O campo 'patientId' é obrigatório.") Long patientId) {

}
