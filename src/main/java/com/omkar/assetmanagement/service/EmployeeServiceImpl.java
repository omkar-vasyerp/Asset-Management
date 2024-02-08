package com.omkar.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.omkar.assetmanagement.dto.EmployeeDto;
import com.omkar.assetmanagement.exception.AssetNotFoundException;
import com.omkar.assetmanagement.exception.EmployeeNotFoundException;
import com.omkar.assetmanagement.model.Asset;
import com.omkar.assetmanagement.model.Employee;
import com.omkar.assetmanagement.repository.AssetRepository;
import com.omkar.assetmanagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private AssetRepository assetRepository;
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(AssetRepository assetRepo, EmployeeRepository employeeRepo) {
		this.assetRepository = assetRepo;
		this.employeeRepository = employeeRepo;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		List<Employee> newEmployees = new ArrayList<Employee>();
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for (Employee employee : employees) {
			if (employee.isDeleted() != true) {
				newEmployees.add(employee);
			}
		}

		for (Employee employee : newEmployees) {
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

		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id" + empId));
		if (employee.isDeleted()) {
			return null;
		}
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setEmpName(employee.getEmpName());
		employeeDto.setEmpPost(employee.getEmpPost());
		employeeDto.setAsset(employee.getAsset());
		return employeeDto;

	}

	@Override
	public boolean deleteEmployeeById(Long empId) {
//		employeeRepository.deleteById(empId);
		if (employeeRepository.existsById(empId)) {
			Employee employee = employeeRepository.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found With Id: "+empId));
			employee.setDeleted(true);
			employeeRepository.save(employee);
		}
		return false;
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
		Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found With Id: "+empId));
		Asset asset = assetRepository.findById(assetId).orElseThrow(()->new AssetNotFoundException("Asset Not Found With Id: "+assetId));

		if (existingEmployee != null) {
			if (existingEmployee.isDeleted() != true) {
				existingEmployee.setAsset(asset);
				employeeRepository.save(existingEmployee);
				return true;
			}
		}
		return false;
	}

}
