package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByEmail(String email);

}
