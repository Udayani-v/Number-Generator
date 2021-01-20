package com.numbergenerator.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.numbergenerator.demo.entity.Employee;
import com.numbergenerator.demo.service.EmployeeService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<String> savemployees(@RequestParam("action") String action,
			@RequestParam("file") MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			throw new RuntimeException("Uploaded file is empty");
		} else {
			File destination = File.createTempFile("employeeData", ".tmp");
			file.transferTo(destination);
			employeeService.uploadEmployeeData(destination.getPath());
			String res = employeeService.uploadEmployeeData(destination.getPath());
			destination.delete();
			return ResponseEntity.ok().body(res);
		}
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if (!employee.isPresent())
			return null;

		return employee.get();
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return employee;
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "Employee deleted";
	}

	@GetMapping("/employees/status")
	public String getStatus() {
		return employeeService.getStatus();
	}

}
