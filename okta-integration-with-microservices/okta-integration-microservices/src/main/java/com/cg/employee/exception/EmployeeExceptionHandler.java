package com.cg.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException e)
	{
			String message=e.getMessage();
			return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<String> employeeNotFoundException(ConstraintViolationException e)
	{
			String message=e.getConstraintViolations().iterator().next().getMessage();
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> employeeNotFoundException(MethodArgumentNotValidException e)
	{
			String message=e.getFieldErrors().iterator().next().getDefaultMessage();
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
		
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception e)
	{
			String message=e.getMessage();
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
