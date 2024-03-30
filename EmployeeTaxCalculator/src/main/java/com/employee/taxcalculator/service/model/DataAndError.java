package com.employee.taxcalculator.service.model;

import lombok.Data;

@Data
public class DataAndError<T> {

	private T data;
	private ErrorMessage error;
	
}
