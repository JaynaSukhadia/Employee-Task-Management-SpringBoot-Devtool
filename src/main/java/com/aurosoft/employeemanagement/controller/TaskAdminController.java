package com.aurosoft.employeemanagement.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.aurosoft.employeemanagement.entity.Status;
import com.aurosoft.employeemanagement.entity.Task;
import com.aurosoft.employeemanagement.entity.User;
import com.aurosoft.employeemanagement.service.StatusService;
import com.aurosoft.employeemanagement.service.TaskService;
import com.aurosoft.employeemanagement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/task")
public class TaskAdminController {
@Autowired
	private TaskService taskService;
	private UserService userService;
	private StatusService statusService;

	
	 
	
	public TaskAdminController(TaskService taskService, UserService userService, StatusService statusService) {
		super();
		this.taskService = taskService;
		this.userService = userService;
		this.statusService = statusService;
	}
	
	@GetMapping("/list")
	public  String listTasks(Model m)
	{
		List<Task> list = taskService.listAllTasks();
		m.addAttribute("list",list);		
	        
		return "admin/task_list";
	}
	
	@GetMapping("/assignBy")
    public String assignedTask(Model m,HttpSession session) {
		
		int uid =(int)session.getAttribute("uid");
		User user=userService.getUserById(uid);
        
		List<Task> list= taskService.getTasksByAssignBy(user);
        m.addAttribute("list",list);
       
	    
        return "admin/assigned";
    }
	
	@GetMapping("/myTask")
    public String myTask(Model m,HttpSession session) {
		int uid =(int)session.getAttribute("uid");
		User user=userService.getUserById(uid);
		
        List<Task> list= taskService.getTasksByAssignTo(user);
        m.addAttribute("list",list);
        
        int taskCount = taskService.getTasksByAssignTo(user).size();
        m.addAttribute("taskCount",taskCount);
        
        return "admin/mytask";
    }
	
	
	
	@GetMapping("/new")
	public String showForm(Task task, Model m)
	{
		List<User>list = userService.listAllUsers();
		m.addAttribute("userlist",list );
		
		List<Status> list1= statusService.listAllStatus();
		m.addAttribute("statuslist",list1 );
		
		
		
		return "admin/task_add";
	}
	
	@PostMapping("/insert")
	

	public String insertTask(@ModelAttribute("task") Task task,HttpSession session, Model m)
	{
		int uid =(int)session.getAttribute("uid");
		User user = userService.getUserById(uid);
		task.setAssignBy(user);
		
		
		task.setAssign_date(new Date());
		taskService.insertTask(task);
		
		 
		
		return "redirect:/task/list";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editTask(@PathVariable int id,Model m)
	{
		
	Task task=taskService.getTaskById(id);
	m.addAttribute("task", task);
	
	List<Status> list1= statusService.listAllStatus();
	m.addAttribute("statuslist",list1 );
	
//	List<User> list=userService.listAllUsers();
//	m.addAttribute("userlist", list);
	
	return "admin/task_edit";
	}
	
	
	@PostMapping(value="/update")
	public String updateTask(@ModelAttribute("task") Task task,HttpSession session)
	{
		
//		int uid =(int)session.getAttribute("uid");
//		User user = userService.getUserById(uid);
//		task.setAssignBy(user);
		
		
		Task task1 =taskService.getTaskById(task.getId());
		task1.setTitle(task.getTitle());
		task1.setDetails(task.getDetails());
		
		//task.setAssign_date(new Date());
		taskService.updateTask(task1);
		
		return "redirect:/task/list";
		
		
	}
	@GetMapping(value="/editstatus/{id}")
	public String editStatus(@PathVariable int id,Model m)
	{
		
	Task task=taskService.getTaskById(id);
	m.addAttribute("task", task);
	
	
	return "/admin/statusupdate";
	}
	
	@PostMapping(value="/statusupdate")
	public String statusUpdate(@ModelAttribute("task") Task task,HttpSession session)
	{
		
		Task task1 =taskService.getTaskById(task.getId());
		task1.setStatus(task.getStatus());
		//task.setAssign_date(new Date());
		taskService.updateTask(task1);
		
		session.setAttribute("msg", "Your task edited...");
		return "redirect:/task/myTask";
	}
	@GetMapping(value="/assigntaskupdate/{id}")
	public String assigntaskupdate(@PathVariable int id,Model m)
	{
		
	Task task=taskService.getTaskById(id);
	m.addAttribute("task", task);
	
	List<User> list=userService.listAllUsers();
	m.addAttribute("userlist", list);
	
	return "admin/assigntaskupdate";
	}
	
	@PostMapping(value="/assigntaskupdate")
	public String assigntaskupdate(@ModelAttribute("task") Task task,HttpSession session)
	{
		
		Task task1 =taskService.getTaskById(task.getId());
		task1.setTitle(task.getTitle());
		task1.setDetails(task.getDetails());
		task1.setAssignTo(task.getAssignTo());
		
		//task1.setStatus(task.getStatus());
		//task.setAssign_date(new Date());
		taskService.updateTask(task1);
		
		session.setAttribute("msg", "Your task edited...");
		return "redirect:/task/assignBy";
	}
	
	
	 
	
	@GetMapping(value="/delete/{id}")
	public String deleteTask(@PathVariable int id, Model m)
	
	{
		taskService.deleteTask(id);
		return "redirect:/task/list";
	}
}
