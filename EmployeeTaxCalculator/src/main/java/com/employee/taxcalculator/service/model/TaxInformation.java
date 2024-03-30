package com.employee.taxcalculator.service.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TaxInformation {
	
	private int employeeId;
	private String firstName;
    private String lastName;
    private BigDecimal  yearlySalary;
    private double taxAmount;
    private double cessAmount;
}
