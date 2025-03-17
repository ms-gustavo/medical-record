package com.myapp.eletronic_physio_record.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO (
		@NotBlank(message = "O campo 'email' é obrigatório.") String email, 
		@NotBlank(message = "O campo 'password' é obrigatório.")String password) {

	
	
}
