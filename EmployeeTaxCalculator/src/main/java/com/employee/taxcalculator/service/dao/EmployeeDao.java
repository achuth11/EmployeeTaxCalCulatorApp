package com.employee.taxcalculator.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.taxcalculator.service.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee , Integer>{

}
