package com.hospital.management.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.service.model.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, String>{

}
