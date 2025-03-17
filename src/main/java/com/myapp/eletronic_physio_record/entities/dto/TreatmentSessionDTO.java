package com.myapp.eletronic_physio_record.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record TreatmentSessionDTO(
		@NotBlank(message = "O campo 'medicalRecordId' é obrigatório.")Long medicalRecordId,
		@NotBlank(message = "O campo 'sessionDate' é obrigatório.")String sessionDate,
		@NotBlank(message = "O campo 'observations' é obrigatório.")String observations,
		@NotBlank(message = "O campo 'procedures' é obrigatório.")String procedures) {

}
