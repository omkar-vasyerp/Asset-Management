package com.omkar.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkar.assetmanagement.dto.EmployeeDto;
import com.omkar.assetmanagement.model.Asset;
import com.omkar.assetmanagement.model.Employee;
import com.omkar.assetmanagement.repository.AssetRepository;
import com.omkar.assetmanagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees= employeeRepository.findAll();
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for (Employee employee : employees) {
			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setEmpId(employee.getEmpId());
			employeeDto.setEmpName(employee.getEmpName());
			employeeDto.setEmpPost(employee.getEmpPost());
			employeeDto.setAsset(employee.getAsset());
			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployeeById(Long empId) {
		Employee employee=employeeRepository.findById(empId).get();
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setEmpName(employee.getEmpName());
		employeeDto.setEmpPost(employee.getEmpPost());
		employeeDto.setAsset(employee.getAsset());
		return employeeDto;
	}

	@Override
	public boolean deleteEmployeeById(Long empId) {
		employeeRepository.deleteById(empId);
		return true;
	}
	@Override
	public boolean addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setEmpId(employeeDto.getEmpId());
		employee.setEmpName(employeeDto.getEmpName());
		employee.setEmpPost(employeeDto.getEmpPost());
		employeeRepository.save(employee);
		return true;
	}

	@Override
	public boolean assignAsset(Long empId, Long assetId) {
		Employee existingEmployee = employeeRepository.findById(empId).get();
		Asset asset = assetRepository.findById(assetId).get();
		if(existingEmployee!= null) {
			existingEmployee.setAsset(asset);
			employeeRepository.save(existingEmployee);
			return true;	
		}
		return false;
	}

}
