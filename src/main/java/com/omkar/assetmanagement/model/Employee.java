package com.omkar.assetmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private long empId;
	
	@Column(name = "emp_name")
	private String empName;
	
	@Column(name="emp_post")
	private String empPost;
	
	
	@OneToOne
	private Asset asset;
	
}
