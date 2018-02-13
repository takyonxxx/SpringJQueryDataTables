package com.biliyor.service;

import java.util.List;

import com.biliyor.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(long id);
	
}
