package com.aurosoft.employeemanagement.service;

import java.util.List;

import com.aurosoft.employeemanagement.entity.Status;



public interface StatusService {

	 
	List<Status> listAllStatus();
	Status getStatusById(int id);
	void insertStatus(Status status);
	void updateStatus(Status status);
	int deleteStatus(int id);
}
