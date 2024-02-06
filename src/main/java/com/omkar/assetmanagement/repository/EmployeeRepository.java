package com.omkar.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.omkar.assetmanagement.model.Employee;

@Component
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
