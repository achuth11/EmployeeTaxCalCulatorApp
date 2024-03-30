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
import com.employee.taxcalculator.service.model.Employee;
import com.employee.taxcalculator.service.model.TaxInformation;
import com.employee.taxcalculator.service.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public String saveEmployee(Employee employee) {
		logger.info("In EmployeeServiceImpl ::  saveEmployee" + employee);
		Employee response = employeeDao.save(employee);
		if(response != null ) {
			return AppConstants.EMPLOYEE_SAVED_SUCCESSFULLY;
		}else 
			return AppConstants.EMPLOYEE_SAVE_FAILURE;
	}

	@Override
	public TaxInformation getTaxInformationForEmployee(Integer employeeId) {
		logger.info("In EmployeeServiceImpl ::  getTaxInformationForEmployee" + employeeId);
		Employee employee = employeeDao.findById(employeeId).get();
		if(employee != null ) {
			TaxInformation taxDetails = calculateTaxDetailsForTheEmployee(employee);
			return taxDetails;
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
		 LocalDate financialYearStart = LocalDate.of(employee.getDateOfJoining().getYear(), 4, 1); // April 1st
	        LocalDate financialYearEnd = LocalDate.of(employee.getDateOfJoining().getYear() + 1, 3, 31); // March 31st next year
	        
	        // Calculate the tenure within the financial year
	        LocalDate startDate = employee.getDateOfJoining().isAfter(financialYearStart) ? employee.getDateOfJoining() : financialYearStart;
	        LocalDate endDate = LocalDate.now().isBefore(financialYearEnd) ? LocalDate.now() : financialYearEnd;
	        long monthsWorked = ChronoUnit.MONTHS.between(startDate, endDate) + 1; // Adding 1 as both start and end months are counted
	        
	     BigDecimal  totalSalary = BigDecimal.valueOf(employee.getMonthlySalary() * monthsWorked);
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
