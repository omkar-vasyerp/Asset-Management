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


	private EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
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
	public ResponseEntity<?> getEmployeeById(@RequestParam Long empId){
		EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
		if (employeeDto==null) {
			return ResponseEntity.ok("User Not Found");
		}
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEmployeeById(@RequestParam Long empId){
		EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
		if (employeeDto!=null) {
		employeeService.deleteEmployeeById(empId);
		return ResponseEntity.ok("Delete Success");
		}
		return ResponseEntity.ok("User Not Found");
	}
	
	@PostMapping("/assign")
	public ResponseEntity<String> assignAsset(@RequestParam Long empId,@RequestParam Long assetId){
		EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
		if (employeeDto!=null) {
		employeeService.assignAsset(empId, assetId);
		return ResponseEntity.ok("Assign Success");
		}else {
		return ResponseEntity.ok("User Not Found");}
	}
}
