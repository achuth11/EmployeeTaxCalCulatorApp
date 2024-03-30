package com.hospital.management.service.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Doctor implements Serializable{
	
	private static final long serialVersionUID = -3122871963487530423L;
	
	@Id
	private String doctorId;
	private String doctorName;
	private String doctorSpecialization;
	private String doctorLicenseNumber;
}
