package com.aurosoft.employeemanagement.controller;



import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aurosoft.employeemanagement.entity.User;
import com.aurosoft.employeemanagement.service.TaskService;
import com.aurosoft.employeemanagement.service.UserService;
import com.aurosoft.employeemanagement.util.Helper;

import jakarta.servlet.http.HttpSession;



//import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/login")
public class ViewController {
	

	UserService userService;
	TaskService taskService;
	


public ViewController(UserService userService, TaskService taskService) {
		super();
		this.userService = userService;
		this.taskService = taskService;
	}

@GetMapping("/login")
	public String login() {
		
		return "login";
	}

@GetMapping("/dashboard")
public String dashboard() {
	
	return "user/dashboard";
}
@GetMapping("/template")
public String adminTemp() {
	
	return "admin/template";
}


@PostMapping("/login")
public String logincheck(@RequestParam("email") String email,
		@RequestParam("password")String password, HttpSession session) 
{
	User user = userService.findByEmailAndPassword(email, password);
	if(user!=null)
	{
		session.setAttribute("uname", user.getFname()+ "    "+user.getLname());
		session.setAttribute("fname", user.getFname());
		session.setAttribute("uid", user.getId());
		session.setAttribute("urole", user.getRole());
		session.setAttribute("email", user.getEmail());
//		System.out.println("XXXXXXXXXXXXXXXX");
//		System.out.println(user);
//		System.out.println("XXXXXXXXXXXXXXXX");

		
		if(Helper.checkUserRole())
		{
			session.setAttribute("msg", "You are succesfully login..");
			return "user/dashboard";
		}
		else
	{
		session.setAttribute("msg","You are successfully login");
		return "admin/dashboard";
	}
	}
		else
		{
			session.setAttribute("msg", "Wrong Username or password");
			return "redirect:/login";
		}
	}	

	@GetMapping("/profile")
	public String profile(Model m,HttpSession session)
	{
		
		if(!Helper.checkUserRole() && !Helper.checkAdminRole())
		{
			return "redirect:/login";
		}
//		ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null)
		{
			uid=(int)session.getAttribute("uid");
			
		}
	
		User user=userService.getUserById(uid);
		m.addAttribute("user", user);
		
		//session.removeAttribute("msg");
		
		int taskCount = taskService.getTasksByAssignTo(user).size();
        m.addAttribute("taskCount",taskCount);
		
       
        
		
		if(Helper.checkUserRole())
		{
			return "user/profile";
		}
		else
		{
			return "admin/profile";
		}
		
	}
	
	@GetMapping(value="/editprofile")
	public String editprofile(Model m)
	{
		
		if(!Helper.checkUserRole() && !Helper.checkAdminRole())
		{
			return "redirect:/login";
		}
		ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null)
		{
			uid=(int)session.getAttribute("uid");
			
		}

		User user=userService.getUserById(uid);
		m.addAttribute("user", user);

		if(Helper.checkUserRole())
		{
			return "/user/edit_profile";
		}
		else
		{
			return "/admin/edit_profile";		
		}
	
	}

	@PostMapping(value="/updateprofile")
	public String updateProfile(@ModelAttribute("user") User user,HttpSession session)
	{
		//user.setReg_date(new Date());
		
		User user2 =userService.getUserById(user.getId());
		user2.setFname(user.getFname());
		user2.setLname(user.getLname());
		user2.setEmail(user.getEmail());
		user2.setPhone(user.getPhone());
		
		userService.updateUser(user2);
		session.setAttribute("msg", "Your profile edited...");
		return "redirect:/profile";
	}

	
@GetMapping("/index")
public String index() {
	
	return "index";
}
@GetMapping("/signup")
public String signup(User user) {
	
	return "signup";
	}

@PostMapping("/newsignup")


public String newsignup(@ModelAttribute("user") User user,HttpSession session)
{
	user.setReg_date(new Date());
	userService.insertUser(user);
	
	session.setAttribute("msg", "Signup Successfully, you can login.....");
	return "redirect:/login";
}

@GetMapping("/forgot_pass")
public String forgot_pass(User user) {
	
	return "forgot_pass";
	}

@PostMapping("/update_forgot_pass")
public String forgotpass(@RequestParam("email") String email,
		 HttpSession session, Model m) 
{
	User user = userService.findByEmail(email);
if(user == null)
{
	session.setAttribute("msg", "email not found");
	return "redirect:/forgot_pass";
}else
{
	String newVcode = Helper.generateRandomNumber();
	user.setVcode(newVcode);
	userService.updateUser(user);
	m.addAttribute("email", email);
	m.addAttribute("vcode", newVcode);
	
	return "verification";
	}
}

@PostMapping("/verificationCheck")
public String verificationCheck(@RequestParam("email") String email,
		@RequestParam("vcode") String vcode,
		@RequestParam("cvcode") String cvcode, 
		Model m, HttpSession session)
{
if(vcode.equals(cvcode))
{
	m.addAttribute("email", email);
	session.setAttribute("msg", "verification code match");
	return "reset_pass";
}
else
{
	session.setAttribute("msg", "verification code not match");
	return "redirect:/forgot_pass";
}
}

@PostMapping("/reset_pass1")
public String resetpass(@RequestParam("password") String password,
		@RequestParam("cpassword") String cpassword, 
		@RequestParam("email") String email,
		HttpSession session)
{
	if(password.equals(cpassword))
	{
		
		User user = userService.findByEmail(email);
		user.setPassword(password);
		user = userService.updateUser(user);
		if(user!=null)
		{
			session.setAttribute("msg","Password Reset");
			return "redirect:/login";
		}
		else
		{
			session.setAttribute("msg", "Something went wrong!!");
			return "redirect:/reset_pass";
		}
	}
		else
		{
			session.setAttribute("msg", "Password and confirm password not match");
			return "redirect:/reset_pass";
		}
	}



//@GetMapping("/profile")
//public String profile() {
//	
//	return "/user/profile";
//	}
@GetMapping("/change_pass")
public String change() {
	if(!Helper.checkUserRole() && !Helper.checkAdminRole())
	{
		return "redirect:/login";
	}
	if(Helper.checkUserRole())
	{
		return "/user/change_pass";
	}
	else
	{
		return "/admin/change_pass";		
		}
	}


@PostMapping("/update_change_pass")
public String updatechangepass(@RequestParam("oldpassword")String oldpassword,@RequestParam("password") String password,
		@RequestParam("cpassword") String cpassword,HttpSession session)
{
	if(!Helper.checkUserRole() && !Helper.checkAdminRole())
{
	return "redirect:/login";
}
	
	
	String email = session.getAttribute("email").toString();
	User user = userService.findByEmail(email);
	
	if(!user.getPassword().equals(oldpassword))
	{
		session.setAttribute("msg","old pass not match");
		return "redirect:/change_pass";
	}
	if(password.equals(cpassword))
	{
		
		user.setPassword(password);
		user = userService.updateUser(user);
		if(user!=null)
		{
			session.setAttribute("msg","Password Reset");
			return "redirect:/profile";
		}
		else
		{
			session.setAttribute("msg", "Something went wrong!!");
			return "redirect:/reset_pass";
		}
	}
		else
		{
			session.setAttribute("msg", "Password and confirm password not match");
			return "redirect:/reset_pass";
		}
	}





@GetMapping("/logout")
public String logout() {
	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
	
	if(session.getAttribute("uname") != null)
	 session.removeAttribute("uname");

	if(session.getAttribute("uid") != null)
		 session.removeAttribute("uid");
		
	if(session.getAttribute("urole") != null)
		 session.removeAttribute("urole");
	
	
	session.setAttribute("msg", "You are successfully logout from system");
			return "redirect:/login";
	}
}
