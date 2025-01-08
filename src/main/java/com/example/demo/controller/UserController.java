package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtService;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

public class UserController {
	
	@Autowired
	public UserService uservice;
	
	@Autowired
	public JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<User> add(@RequestBody UserDto userDto){
		
		System.out.println(userDto);
		
		User user = uservice.add(userDto);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> addUser(@RequestBody UserLoginDto userloginDto){
		
		User user = uservice.loginUserService(userloginDto);
		
		String token = jwtService.generateToken(user.getEmail());
		
		LoginResponseDto resDto = new LoginResponseDto("Login Successfully!",token);
		
		return ResponseEntity.accepted()
		         .header("Authorization", "Bearer "+ token)
		         .body(resDto);
		
	}
	
	
	

}
