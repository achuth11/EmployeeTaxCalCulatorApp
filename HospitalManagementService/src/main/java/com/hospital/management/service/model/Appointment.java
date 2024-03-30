package com.hospital.management.service.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Appointment implements Serializable {

	private static final long serialVersionUID = -5104343415829068102L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int appointmentId;
	private String appointmentStatus;
	private Patient patient;
	private Doctor doctor;
	private Date appointmentDate;
	private Timestamp appointmentTime;
	private String primaryComplaint;
	private String secondaryComplaint;
	private String otherComplaints;
	private String diagnosis;
}
