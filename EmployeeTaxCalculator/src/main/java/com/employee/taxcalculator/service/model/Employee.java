package com.employee.taxcalculator.service.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Employee implements Serializable{

	private static final long serialVersionUID = 3447319485905556603L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int employeeId;
	@NotBlank(message = "Employee First Name is mandatory")
    private String firstName;
	@NotBlank(message = "Employee Last Name is mandatory")
    private String lastName;
	@Email(message = "Employee Email is mandatory")
    private String email;
	@NotEmpty(message = "Employee Phone Number is Mandatory")
    private List<String> phoneNumbers;
	@NotNull(message = "Employee Date of Joining is mandatory")
    private LocalDate dateOfJoining;
	@Positive(message = "Proper Monthly salary is mandatory")
    private double monthlySalary;
}
