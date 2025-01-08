package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Autowired
	public JwtFilter jwtFilter;
	
	
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		
		// logic of filter
		   http.csrf(csrf -> csrf.disable())
		       .authorizeHttpRequests(auth ->{
		    	   
		    	    auth.requestMatchers("/api/user/register", "/api/user/login", "/api/products").permitAll()
		    	    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		    	    .anyRequest().authenticated();
		    	    
		       })
		       .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		       .authenticationProvider(authenticationProvider())
		          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	
	@Bean
	public UserDetailsService getuserdetailsService() {
		   return new CustomeUserDetailService();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authDao = new DaoAuthenticationProvider();
		           
		   authDao.setUserDetailsService(getuserdetailsService());
		   authDao.setPasswordEncoder(encode());
		
		return authDao;
		
	}
	
	@Bean
	public PasswordEncoder encode() {
		 return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationManager   managerConfiguration(AuthenticationConfiguration config) throws Exception { 
		         
		return  config.getAuthenticationManager();
	}
	
}
