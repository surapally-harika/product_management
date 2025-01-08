package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.exceptions.CustomeException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

import io.jsonwebtoken.io.Encoder;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	public UserRepo urepo;
	
	
	@Autowired
	public PasswordEncoder encoder;

	@Override
	public User add(UserDto userdto) {
		
		User user = new User();
		
		user.setEmail(userdto.getEmail());
		user.setPassword(encoder.encode(userdto.getPassword()));
		user.setRoles("ROLE_USER");
		
		return urepo.save(user);
	}

	@Override
	public User loginUserService(UserLoginDto loginDto) {
		
		User user = urepo.findByEmail(loginDto.getEmail()).orElseThrow(() -> new RuntimeException("This email in bot registerd"));
		
		if(encoder.matches(loginDto.getPassword(),user.getPassword())) {
			user.setPassword(null);
			return user;
		}
		throw new RuntimeException("password is incorrect");
	}

}
