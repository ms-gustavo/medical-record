package com.myapp.eletronic_physio_record.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientDTO(@NotBlank(message = "O campo 'name' é obrigaório.") String name,
		@NotBlank(message = "O campo 'birthDate' é obrigatório.") String birthDate,
		@NotBlank(message = "O campo 'phone' é obrigatório.") String phone,
		@NotBlank(message = "O campo 'address' é obrigatório.") String address) {
}