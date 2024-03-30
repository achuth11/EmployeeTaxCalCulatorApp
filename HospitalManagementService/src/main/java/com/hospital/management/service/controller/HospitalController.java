package com.hospital.management.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.service.model.Patient;
import com.hospital.management.service.service.HospitalService;

@RequestMapping("hospital")
@RestController
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	private final Logger logger = LoggerFactory.getLogger(HospitalController.class);
	
	@GetMapping("/")
	public String helloWorld() {
	        return "Welcomes";
	}
	
	@PostMapping("add-patients")
	public String addPatients(@RequestBody Patient patient) {
		logger.info("In HospitalController :: addPatients"+patient);
		String response = hospitalService.addPatients(patient);
		return response;
	}

}
