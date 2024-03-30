package com.hospital.management.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.management.service.common.AppConstants;
import com.hospital.management.service.dao.PatientDao;
import com.hospital.management.service.model.Patient;
import com.hospital.management.service.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService{
	
	private final Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);
	
	@Autowired
	private PatientDao patientDao;

	@Override
	public String addPatients(Patient patient) {
		logger.info("In HospitalServiceImpl :: addPatients"+patient);
		Patient response = patientDao.save(patient);
		if(response != null) {
			return AppConstants.PATIENT_SAVED_SUCCESSFULLY;
		}
		return AppConstants.PATIENT_SAVED_FAILURE;
	}
	

}
