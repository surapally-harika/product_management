package com.example.demo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class CustomeUserDetails  implements UserDetails{
	
	private String email;
	
	private String password;
	
	private List<GrantedAuthority> authorities;
	
	
	public CustomeUserDetails() {
		
	}
	
	

	public CustomeUserDetails(User user) {
		super();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.authorities = Arrays.stream(user.getRoles().split(","))
	            .map(SimpleGrantedAuthority::new)
	            .collect(Collectors.toList());
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

}
