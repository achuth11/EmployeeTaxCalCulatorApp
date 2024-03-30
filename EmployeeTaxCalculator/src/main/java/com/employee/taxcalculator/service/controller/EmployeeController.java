package com.employee.taxcalculator.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.taxcalculator.service.common.AppConstants;
import com.employee.taxcalculator.service.model.DataAndError;
import com.employee.taxcalculator.service.model.Employee;
import com.employee.taxcalculator.service.model.ErrorMessage;
import com.employee.taxcalculator.service.model.TaxInformation;
import com.employee.taxcalculator.service.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("save")
	public DataAndError<String> saveEmployee(@Valid @RequestBody Employee employee) {
		logger.info("In EmployeeController :: saveEmployee"+employee);
		DataAndError<String> response = employeeService.saveEmployee(employee);
		return response;
	}
	
	@GetMapping("tax-details/{employeeId}")
	public DataAndError<TaxInformation> getTaxInformationForEmployee(@PathVariable int employeeId) {
		DataAndError<TaxInformation> response = new DataAndError<TaxInformation>();
		logger.info("In EmployeeController :: getTaxInformationForEmployee :"+employeeId);
		TaxInformation data = employeeService.getTaxInformationForEmployee(employeeId);
		if(data != null) {
			response.setData(data);
		} else {
			ErrorMessage error = new ErrorMessage();
			error.setMessage(AppConstants.USER_NOT_FOUND);
			response.setError(error);
		}
		return response;
	}

}
