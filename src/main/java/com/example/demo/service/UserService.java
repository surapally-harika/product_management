package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.User;

public interface UserService {
	
	public User add(UserDto userdto);
	
	public User loginUserService(UserLoginDto loginDto);

}
