package com.myapp.eletronic_physio_record.entities.dto;

import com.myapp.eletronic_physio_record.entities.Roles;

public record RegisterDTO(String email, String password, Roles role) {

}
