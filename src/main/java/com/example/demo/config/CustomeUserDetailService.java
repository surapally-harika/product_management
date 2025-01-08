package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.exceptions.Exception;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;


public class CustomeUserDetailService implements UserDetailsService{
	
	@Autowired
	public UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> new Exception("There is no user with this email "));
		
		
		
		return new CustomeUserDetails(user);
	}

}
