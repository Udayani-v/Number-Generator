package com.numbergenerator.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
//@Table(name="Employee")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@Column(name="id")
	private Long id;
	
	//@Column(name="Employee_Name")
	private String employeeName;
	
	//@Column(name="Age")
	private String age;

}
