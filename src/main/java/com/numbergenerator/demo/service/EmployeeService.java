package com.numbergenerator.demo.service;

import java.util.List;
import java.util.Optional;

import com.numbergenerator.demo.entity.Employee;

public interface EmployeeService {

	String uploadEmployeeData(String path);

	List<Employee> getAllEmployees();

	void updateEmployee(Employee employee);

	Optional<Employee> getEmployeeById(Long id);

	void deleteEmployee(Long id);

	String getStatus();

	List<Employee> getEmployeesByPagiation(Integer pageNo, Integer pageSize, String sortBy);

}
