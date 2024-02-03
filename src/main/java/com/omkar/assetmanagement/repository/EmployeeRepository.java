package com.omkar.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.assetmanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
