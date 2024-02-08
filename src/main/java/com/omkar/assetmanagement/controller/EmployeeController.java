package com.omkar.assetmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.assetmanagement.dto.EmployeeDto;
import com.omkar.assetmanagement.dto.ResponseDto;
import com.omkar.assetmanagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/all")
	public ResponseDto getAllEmployees() {
		List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
		return new ResponseDto(200, "Success", employeeDtos);

	}

	@PostMapping({ "/add", "/update" })
	public ResponseDto addEmployee(@RequestBody EmployeeDto employeeDto) {
		employeeService.addEmployee(employeeDto);
		return new ResponseDto(200, "Success", "Add Success");
	}

//	@GetMapping("/get")
//	public ResponseEntity<?> getEmployeeById(@RequestParam Long empId){
//		EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
//		if (employeeDto==null) {
//			return ResponseEntity.ok("User Not Found");
//		}
//		return ResponseEntity.ok(employeeDto);
//	}
	@GetMapping("/get")
	public ResponseDto getEmployeeById(@RequestParam Long empId) {

		EmployeeDto employeeDto = employeeService.getEmployeeById(empId);

		return new ResponseDto(200, "Success", employeeDto);
	}

	@DeleteMapping("/delete")
	public ResponseDto deleteEmployeeById(@RequestParam Long empId) {

		employeeService.deleteEmployeeById(empId);
		return new ResponseDto(200, "Success", "Delete Success");
	}

	@PostMapping("/assign")
	public ResponseDto assignAsset(@RequestParam Long empId, @RequestParam Long assetId) {
		employeeService.assignAsset(empId, assetId);
		return new ResponseDto(200, "Success", "Assign Success");

	}
}
