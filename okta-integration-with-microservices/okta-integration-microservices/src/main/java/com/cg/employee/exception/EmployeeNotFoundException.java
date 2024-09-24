package com.cg.employee.exception;

import com.cg.employee.util.Constants;

public class EmployeeNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException(String id) {
		super(Constants.EMP_NOT_FOUND + id);
	}

}
