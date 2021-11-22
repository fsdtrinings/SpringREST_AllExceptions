package com.mkj.rapipay.execptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationsExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exp,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> map = new HashMap<>();
		   // fieldName, errorMsg
		
		List<ObjectError> list = exp.getBindingResult().getAllErrors();
		// all validations exceptions with fieldname and error render msg
		list.stream().forEach((e)->{
			String fieldName = ((FieldError)e).getField();
		    String errorMsg = e.getDefaultMessage();
			
		   map.put(fieldName, errorMsg);
		});
		
		
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}

	
}
