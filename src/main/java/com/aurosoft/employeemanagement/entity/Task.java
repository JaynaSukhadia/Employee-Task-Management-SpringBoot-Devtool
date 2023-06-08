package com.aurosoft.employeemanagement.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="title", nullable= false)
	private String title;
	
	@Column(name="details", nullable= false)
	private String details;
	
//	@Column(name="assign_date",nullable = false)
//	private Date assign_date;
	
	@Column(name="assign_date",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date assign_date;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="status_id",referencedColumnName = "id", nullable = false)
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="assignBy", referencedColumnName = "id", nullable = false)
	private User assignBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="assignTo", referencedColumnName = "id", nullable = false)
	private User assignTo;
	
	 
	
	
}
