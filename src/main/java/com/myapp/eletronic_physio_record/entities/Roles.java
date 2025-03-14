package com.myapp.eletronic_physio_record.entities;

public enum Roles {

	ADMIN("ADMIN"),
	PHYSIO("PHYSIO");
	
	
	private String role;
	
	Roles(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
