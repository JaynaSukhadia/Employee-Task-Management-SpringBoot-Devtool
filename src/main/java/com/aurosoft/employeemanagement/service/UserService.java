package com.aurosoft.employeemanagement.service;

import java.util.List;

import com.aurosoft.employeemanagement.entity.User;


public interface UserService {

	
	List<User> listAllUsers();
	User  getUserById(int id);
	User insertUser(User user);
	User updateUser(User user);
	int deleteUser(int id);
	
	
	
	User findByEmailAndPassword(String email, String password);
	User  findByEmail(String email);
	boolean existsByEmail(String email);
	//boolean registerUser(User user);
	// boolean validatePasswordAndConfirmPassword(User user);
	//boolean isPasswordMatch(String password,String confirmPassword);
	//boolean registerUser(User user);
}
