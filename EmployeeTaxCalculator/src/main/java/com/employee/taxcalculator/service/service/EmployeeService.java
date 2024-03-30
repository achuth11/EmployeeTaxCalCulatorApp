package com.employee.taxcalculator.service.service;

import com.employee.taxcalculator.service.model.DataAndError;
import com.employee.taxcalculator.service.model.Employee;
import com.employee.taxcalculator.service.model.TaxInformation;

public interface EmployeeService {
	
	public DataAndError<String> saveEmployee(Employee employee);

	public TaxInformation getTaxInformationForEmployee(Integer employeeId);
}
