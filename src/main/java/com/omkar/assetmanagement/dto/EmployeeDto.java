package com.omkar.assetmanagement.dto;


import com.omkar.assetmanagement.model.Asset;

import lombok.Data;


@Data
public class EmployeeDto {

	private long empId;
	
	private String empName;

	private String empPost;
	
	private Asset asset;
	

}