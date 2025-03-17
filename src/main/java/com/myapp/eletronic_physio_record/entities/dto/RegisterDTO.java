package com.myapp.eletronic_physio_record.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.myapp.eletronic_physio_record.entities.Roles;

public record RegisterDTO(
        @NotBlank(message = "O campo 'email' é obrigatório.") @Email(message = "Formato de email inválido.") String email,
        @NotBlank(message = "O campo 'password' é obrigatório.") String password,
        @NotNull(message = "O campo 'role' é obrigatório.") Roles role
) {}
