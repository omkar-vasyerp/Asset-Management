package com.omkar.assetmanagement.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.omkar.assetmanagement.dto.ResponseDto;
import com.omkar.assetmanagement.exception.AssetNotFoundException;
import com.omkar.assetmanagement.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public ResponseDto EmployeeNotFoundExceptionHandling(EmployeeNotFoundException ex){
		return new ResponseDto(404,"Id Not Found",ex.getMessage());
	}
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public ResponseDto AssetNotFoundExceptionHandling(AssetNotFoundException ex){
		return new ResponseDto(404,"Id Not Found",ex.getMessage());
	}
}
