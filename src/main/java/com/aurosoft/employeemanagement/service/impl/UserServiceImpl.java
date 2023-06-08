package com.aurosoft.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.employeemanagement.entity.User;
import com.aurosoft.employeemanagement.repository.UserRepository;
import com.aurosoft.employeemanagement.service.UserService;



@Service
public class UserServiceImpl implements UserService{


	@Autowired
	UserRepository repo;

	@Override
	public List<User> listAllUsers() {
		return repo.findAll();
	}

	@Override
	public User getUserById(int id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public User insertUser(User user) {
		return repo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public int deleteUser(int id) {
		repo.deleteById(id);
		return id;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		
		return repo.findByEmailAndPassword(email, password);
	}

	@Override
	public User findByEmail(String email) {
		
		return repo.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return repo.existsByEmail(email);
	}

//	@Override
//	public boolean validatePasswordAndConfirmPassword(User user) {
//		
//		return repo.validatePasswordAndConfirmPassword(user);
//	}
//
//	public boolean registerUser(User user) {
//		
//		return repo.registerUser(user);
//	}

//	@Override
//	public boolean isPasswordMatch(String password, String confirmPassword) {
//		return repo.isPasswordMatch(password, confirmPassword);
//	}
}
