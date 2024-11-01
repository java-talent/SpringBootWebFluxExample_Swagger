package com.javatalent.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.javatalent.entity.Employee;

public interface EmployeeRespository extends ReactiveCrudRepository<Employee, String>{

}
