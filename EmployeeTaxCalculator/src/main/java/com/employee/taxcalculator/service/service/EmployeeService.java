package com.employee.taxcalculator.service.service;

import com.employee.taxcalculator.service.model.Employee;
import com.employee.taxcalculator.service.model.TaxInformation;

public interface EmployeeService {
	
	public String saveEmployee(Employee employee);

	public TaxInformation getTaxInformationForEmployee(Integer employeeId);
}
