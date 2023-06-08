package com.aurosoft.employeemanagement.service;

import java.util.List;

import com.aurosoft.employeemanagement.entity.Task;
import com.aurosoft.employeemanagement.entity.User;



public interface TaskService {
	//Object findAssignTo = null;
	List<Task> listAllTasks();
	Task  getTaskById(int id);
	Task insertTask(Task task);
	Task updateTask(Task task);
	int deleteTask(int id);
	
	List<Task> getTasksByAssignTo(User assignTo);
	//List<Task> findByAssignTo(User assignTo);
    
    List<Task> getTasksByAssignBy(User assignBy);
	//List<Task> findByAssignTo(int id);
	
    
   
    int getTaskCountForUser(User user);
}
