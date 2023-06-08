package com.aurosoft.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.employeemanagement.entity.Status;
import com.aurosoft.employeemanagement.repository.StatusRepository;
import com.aurosoft.employeemanagement.service.StatusService;
@Service
public class StatusServiceImpl implements StatusService {
@Autowired
	StatusRepository statusRepository;
	
	@Override
	public List<Status> listAllStatus() {
		return statusRepository.findAll();
		
	}

	@Override
	public Status getStatusById(int id) {
		return statusRepository.findById(id).orElseThrow();
	}

	@Override
	public void insertStatus(Status status) {
		statusRepository.save(status);
	}

	@Override
	public void updateStatus(Status status) {
		statusRepository.save(status);
	}
	

	@Override
	public int deleteStatus(int id) {
		statusRepository.deleteById(id);
		return id;
	}

	
}
