package com.aurosoft.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aurosoft.employeemanagement.entity.Status;
import com.aurosoft.employeemanagement.service.StatusService;

@Controller
public class StatusController {
@Autowired
	StatusService statusService;

	public StatusController(StatusService statusService) {
		super();
		this.statusService = statusService;
	}
	
	
	@GetMapping("/list")
    public String getAllStatuses(Model model) {
        List<Status> list = statusService.listAllStatus();
        model.addAttribute("list", list);
        return "/admin/statuses";
    }

	
	
}
