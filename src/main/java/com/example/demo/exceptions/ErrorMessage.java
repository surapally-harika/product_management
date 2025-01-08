package com.example.demo.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

	public LocalDateTime timestamp;
	 
	 
	 public String message;
	 
	 
	 public Integer status;
	 
	 
	 
	 public String  path;
}
