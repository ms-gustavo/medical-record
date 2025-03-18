package com.myapp.eletronic_physio_record.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record PhysioDTO(
		Long userId,
		@NotBlank(message = "O campo 'name' é obrigatório.")String name,
		@NotBlank(message = "O campo 'licenseNumber' é obrigatório.")String licenseNumber,
		@NotBlank(message = "O campo 'speciality' é obrigatório.")String speciality) {}
