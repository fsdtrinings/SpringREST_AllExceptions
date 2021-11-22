package com.mkj.rapipay.execptions;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyGlobalExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<ExceptionTemplate> abc(MinBalanceException exp)
	{
		ExceptionTemplate template = new ExceptionTemplate();
		
		template.setCurrentTime(LocalTime.now());
		template.setExceptionMsg(exp.exceptionMsg());
		
		return new ResponseEntity<ExceptionTemplate>(template,HttpStatus.BAD_REQUEST);
		
	}
	
	// 
	@ExceptionHandler
	public ResponseEntity<ExceptionTemplate> abc2(javax.persistence.NoResultException exp)
	{
		ExceptionTemplate template = new ExceptionTemplate();
		
		template.setCurrentTime(LocalTime.now());
		template.setExceptionMsg(exp.toString());
		
		return new ResponseEntity<ExceptionTemplate>(template,HttpStatus.NOT_FOUND);
		
	}
	
}
