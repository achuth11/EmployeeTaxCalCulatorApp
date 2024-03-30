package com.employee.taxcalculator.service.common;

public class EmployeeExceptionHandler extends Exception{


	private static final long serialVersionUID = 765537047422419036L;

	public EmployeeExceptionHandler() {
        super();
    }

    public EmployeeExceptionHandler(String message) {
        super(message);
    }

    public EmployeeExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeExceptionHandler(Throwable cause) {
        super(cause);
    }

    protected EmployeeExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
