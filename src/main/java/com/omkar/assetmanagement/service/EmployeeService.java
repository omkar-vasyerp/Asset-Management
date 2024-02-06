package com.omkar.assetmanagement.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omkar.assetmanagement.dto.EmployeeDto;

@Component
public interface EmployeeService {

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployeeById(Long empId);

	boolean deleteEmployeeById(Long empId);

	boolean addEmployee(EmployeeDto employeeDto);

	boolean assignAsset(Long empId, Long assetId);
}
