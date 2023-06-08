package com.aurosoft.employeemanagement.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name= "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="fname", nullable= false)
	private String fname;
	
	@Column(name="lname", nullable= false)
	private String lname;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="password", nullable =  false)
	private String password;
	
	@Column(name="role", nullable =  false)
	private String role;
	
	
	@Column(name="phone", nullable =  false)
	private String phone;
	
	
	@Column(name="vcode", nullable =  false)
	private String vcode;
	
	@Column(name="reg_date",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date reg_date;
	

	@OneToMany(mappedBy = "assignBy", fetch = FetchType.LAZY)
	private Set<Task> tasklist1 = new HashSet<>();
	
	@OneToMany(mappedBy = "assignTo", fetch = FetchType.LAZY)
	private Set<Task> tasklist2 = new HashSet<>();
	
	
}
