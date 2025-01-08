package com.example.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class CustomeException {
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ErrorMessage> productException(Exception pe, HttpServletRequest req) {
		
		   ErrorMessage erm = new ErrorMessage(LocalDateTime.now(), pe.getMessage(),  404, req.getRequestURL().toString());
		
		return new ResponseEntity<ErrorMessage>(erm, HttpStatus.NOT_FOUND);
	}

}
