package com.employee.taxcalculator.service.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.taxcalculator.service.common.AppConstants;
import com.employee.taxcalculator.service.dao.EmployeeDao;
import com.employee.taxcalculator.service.model.DataAndError;
import com.employee.taxcalculator.service.model.Employee;
import com.employee.taxcalculator.service.model.ErrorMessage;
import com.employee.taxcalculator.service.model.TaxInformation;
import com.employee.taxcalculator.service.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public DataAndError<String> saveEmployee(Employee employee) {
		logger.info("In EmployeeServiceImpl ::  saveEmployee" + employee);
		DataAndError<String> response = new DataAndError<String>();
		ErrorMessage errors = new ErrorMessage();
		try {
			Employee data = employeeDao.save(employee);
			if (data != null) {
				response.setData(AppConstants.EMPLOYEE_SAVED_SUCCESSFULLY);
			} else {
				errors.setMessage(AppConstants.EMPLOYEE_SAVE_FAILURE);
				response.setError(errors);
			}
		}
		catch(Exception e) {
			errors.setMessage(e.getMessage());
			response.setError(errors);
		}
		return response;
	}

	@Override
	public TaxInformation getTaxInformationForEmployee(Integer employeeId) {
		logger.info("In EmployeeServiceImpl ::  getTaxInformationForEmployee" + employeeId);
		try {
		Employee employee = employeeDao.findById(employeeId).get();
		if (employee != null) {
			TaxInformation taxDetails = calculateTaxDetailsForTheEmployee(employee);
			return taxDetails;
			}
		}
		catch(Exception e) {
			logger.info("Employee Not Found");
		}
		return null;
	}

	private TaxInformation calculateTaxDetailsForTheEmployee(Employee employee) {
		logger.info("In EmployeeServiceImpl ::  calculateTaxDetailsForTheEmployee" + employee);
		TaxInformation taxDetails = new TaxInformation();
		taxDetails.setEmployeeId(employee.getEmployeeId());
		taxDetails.setFirstName(employee.getFirstName());
		taxDetails.setLastName(employee.getLastName());
		taxDetails.setYearlySalary(calculateYearlySalary(employee));
		taxDetails.setTaxAmount(calculateTaxAmount(taxDetails.getYearlySalary()));
		taxDetails.setCessAmount(calculateCess(taxDetails.getYearlySalary()));
		return taxDetails;
	}

	private BigDecimal calculateYearlySalary(Employee employee) {
		logger.info("In EmployeeServiceImpl ::  calculateTaxDetailsForTheEmployee" + employee);
		LocalDate financialYearStart = LocalDate.of(employee.getDateOfJoining().getYear(), 4, 1); 
		LocalDate financialYearEnd = LocalDate.of(employee.getDateOfJoining().getYear() + 1, 3, 31);
		// Calculate the tenure within the financial year
		LocalDate startDate = employee.getDateOfJoining().isAfter(financialYearStart) ? employee.getDateOfJoining()
				: financialYearStart;
		LocalDate endDate = LocalDate.now().isBefore(financialYearEnd) ? LocalDate.now() : financialYearEnd;
		long monthsWorked = ChronoUnit.MONTHS.between(startDate, endDate) + 1;

		BigDecimal totalSalary = BigDecimal.valueOf(employee.getMonthlySalary() * monthsWorked);
		return totalSalary;
	}

	private double calculateTaxAmount(BigDecimal yearlySalary) {
		logger.info("In EmployeeServiceImpl ::  calculateTaxAmount" + yearlySalary);
		double salary = yearlySalary.doubleValue();
		double taxAmount = 0.0;
		if (salary > 1000000) {
			taxAmount = (salary - 1000000) * 0.2 + 500000 * 0.1 + 250000 * 0.05;
		} else if (salary > 500000) {
			taxAmount = (salary - 500000) * 0.1 + 250000 * 0.05;
		} else if (salary > 250000) {
			taxAmount = (salary - 250000) * 0.05;
		}
		return taxAmount;
	}

	private double calculateCess(BigDecimal yearlySalary) {
		double salary = yearlySalary.doubleValue();
		logger.info("In EmployeeServiceImpl ::  calculateTaxAmount" + yearlySalary);
		double cessAmount = 0.0;
		if (salary > 2500000) {
			cessAmount = (salary - 2500000) * 0.02;
		}
		return cessAmount;
	}

}
