package com.numbergenerator.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numbergenerator.demo.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	

}
