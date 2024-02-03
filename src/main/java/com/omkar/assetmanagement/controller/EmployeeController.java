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
import com.omkar.assetmanagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
		
	}
	
	@PostMapping({"/add","/update"})
	public  ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto){
		employeeService.addEmployee(employeeDto);
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("/get")
	public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam Long empId){
		return ResponseEntity.ok(employeeService.getEmployeeById(empId));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEmployeeById(@RequestParam Long empId){
		employeeService.deleteEmployeeById(empId);
		return ResponseEntity.ok("Delete Success");
	}
	
	@PostMapping("/assign")
	public ResponseEntity<String> assignAsset(@RequestParam Long empId,@RequestParam Long assetId){
		employeeService.assignAsset(empId, assetId);
		return ResponseEntity.ok("Assign Success");
	}
}
