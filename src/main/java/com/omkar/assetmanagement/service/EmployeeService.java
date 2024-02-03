package com.omkar.assetmanagement.service;

import java.util.List;

import com.omkar.assetmanagement.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployeeById(Long empId);

	boolean deleteEmployeeById(Long empId);

	boolean addEmployee(EmployeeDto employeeDto);

	boolean assignAsset(Long empId, Long assetId);
}
