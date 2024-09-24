package com.cg.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.employee.entity.Employee;
import com.cg.employee.exception.EmployeeNotFoundException;
import com.cg.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
		
	}
	
	
	public Employee addEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(String employeeId)
	{
		return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));		
	}
	
	
	public void deleteEmployee(String id)
	{
		getEmployeeById(id);
		employeeRepository.deleteById(id);
	}
	
	public Employee updateEmployee(String id,Employee employee)
	{
		Employee emp = getEmployeeById(id);
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setDepartment(employee.getDepartment());
		emp.setEmail(employee.getEmail());
		emp.setMobile(employee.getMobile());
		emp.setAge(employee.getAge());
		emp.setEmployeeSalary(employee.getEmployeeSalary());
		return employeeRepository.save(emp);
	}
}
