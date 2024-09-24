package com.cg.employee.entity;



import com.cg.employee.util.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String employeeId;
	
	@Column(length = 30)
	@NotBlank(message = Constants.EMP_NAME_VALIDATION_MESSAGE)
	private String employeeName;
	
	@Column
	private String department;
	
	@Column
	@Max(60)
	private int age;
	
	@Column
	@Email(message = Constants.EMAIL_VALIDATION_MESSAGE)
	private String email;
	
	@Column
	@NotBlank(message = Constants.MOBILE_NUM_VALIDATION_MESSAGE)
	private String mobile;
	
	@Column
	private double employeeSalary;
		
}
