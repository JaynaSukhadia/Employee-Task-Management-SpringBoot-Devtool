package com.aurosoft.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.employeemanagement.entity.Task;
import com.aurosoft.employeemanagement.entity.User;
import com.aurosoft.employeemanagement.repository.TaskRepository;
import com.aurosoft.employeemanagement.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository repo;
	
	@Override
	public List<Task> listAllTasks() {
		
		return repo.findAll();
	}

	@Override
	public Task getTaskById(int id) {
		
		return repo.findById(id).orElseThrow();
	}

	@Override
	public Task insertTask(Task task) {
		
		return repo.save(task);
	}

	@Override
	public Task updateTask(Task task) {
		return repo.save(task);
	}

	@Override
	public int deleteTask(int id) {
		repo.deleteById(id);
		return id;
	}


	@Override
	public List<Task> getTasksByAssignTo(User assignTo) {
		return repo.findByAssignTo(assignTo);

		
	}

	@Override
	public List<Task> getTasksByAssignBy(User assignBy) {
		return repo.findByAssignBy(assignBy);
	}

	@Override
	public int getTaskCountForUser(User user) {
		repo.findByAssignTo(user);
		return 0;
	}

	
	

	

	 

	 
	 

}
