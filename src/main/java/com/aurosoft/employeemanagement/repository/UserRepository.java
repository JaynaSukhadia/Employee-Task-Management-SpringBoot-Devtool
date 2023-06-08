package com.aurosoft.employeemanagement.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.employeemanagement.entity.User;



public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);
	User  findByEmail(String email);
	boolean existsByEmail(String email);
    //boolean isPasswordMatch(String password,String confirmPassword);
	// boolean validatePasswordAndConfirmPassword(User user);
	 //boolean registerUser(User user);
	}

