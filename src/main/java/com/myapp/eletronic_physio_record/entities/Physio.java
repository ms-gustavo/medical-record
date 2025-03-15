package com.myapp.eletronic_physio_record.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "physio")
@NoArgsConstructor @AllArgsConstructor
public class Physio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
    private String name;
	@Column(nullable = false)
	private String speciality;
	@Column(nullable = false, unique = true)
	private String licenseNumber;
	@Column(nullable = false, unique = true)
    private String email;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToMany
    @JoinTable(
        name = "physio_patients",
        joinColumns = @JoinColumn(name = "physio_id"),
        inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients;
	
	public User getUser() {
		return user;
	}
	
	public Long getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLicenseNumber() {
	    return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
	    this.licenseNumber = licenseNumber;
	}
	
	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}
	
	public String getSpeciality() {
	    return speciality;
	}

	public void setSpeciality(String speciality) {
	    this.speciality = speciality;
	}
	
	
}
