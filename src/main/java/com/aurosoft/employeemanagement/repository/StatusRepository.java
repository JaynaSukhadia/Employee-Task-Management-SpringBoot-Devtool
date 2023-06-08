package com.aurosoft.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.employeemanagement.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>
{

}
