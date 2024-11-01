package com.javatalent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javatalent.dto.EmployeeDto;
import com.javatalent.service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
	private EmployeeService service;
	
	@PostMapping("/save")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		return service.saveEmployee(employeeDto);
	}
	
	@GetMapping("{id}")
	public Mono<EmployeeDto> getEmployee(@PathVariable("id") String employeeId){
		return service.getEmployee(employeeId);
	}
	
	@GetMapping
	public Flux<EmployeeDto> getAllEmployees(){
		return service.getAllEmployees();
	}
	
	@PutMapping("{id}")
	public Mono<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, 
												@PathVariable("id") String employeeId){
		return service.updateEmployee(employeeDto, employeeId);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Mono<Void> deleteEmployee(@PathVariable("id") String employeeId){
		return service.deleteEmployee(employeeId);
	}
}
