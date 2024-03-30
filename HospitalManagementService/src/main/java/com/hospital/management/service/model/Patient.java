package com.hospital.management.service.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Patient implements Serializable{
	
	private static final long serialVersionUID = -2630819850662438539L;
	
	@Id
	private String patientId;
	private String patientName;
	private int patientAge;
	private String patientAddress;

}
