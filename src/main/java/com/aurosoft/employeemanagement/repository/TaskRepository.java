package com.aurosoft.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.employeemanagement.entity.Task;
import com.aurosoft.employeemanagement.entity.User;

public interface TaskRepository extends JpaRepository<Task, Integer>{

List<Task> findByAssignTo(User assignTo);
    
    List<Task> findByAssignBy(User assignBy);
    
   


	
}
