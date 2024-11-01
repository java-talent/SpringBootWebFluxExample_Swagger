package com.javatalent.service;

import org.springframework.stereotype.Service;

import com.javatalent.dto.EmployeeDto;
import com.javatalent.entity.Employee;
import com.javatalent.mapper.EmployeeMapper;
import com.javatalent.repository.EmployeeRespository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRespository employeeRespository;

	@Override
	public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Mono<Employee> savedEmployee = employeeRespository.save(employee);
		return savedEmployee.map(employeeEntity -> EmployeeMapper.mapToEmployeeDto(employee));
	}

	@Override
	public Mono<EmployeeDto> getEmployee(String employeeId) {
		Mono<Employee> employeeMono = employeeRespository.findById(employeeId);
		return employeeMono.map(employee -> EmployeeMapper.mapToEmployeeDto(employee));
	}

	@Override
	public Flux<EmployeeDto> getAllEmployees() {
		
		Flux<Employee> employeeFlux = employeeRespository.findAll();
		return employeeFlux
				.map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
				.switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId) {
		
		Mono<Employee> employeeMono = employeeRespository.findById(employeeId);
		
		return employeeMono.flatMap((existingEmployee)-> {
			existingEmployee.setFirstName(employeeDto.getFirstName());
			existingEmployee.setLastName(employeeDto.getLastName());
			existingEmployee.setEmail(employeeDto.getEmail());
			return employeeRespository.save(existingEmployee);
		}).map((employee)-> EmployeeMapper.mapToEmployeeDto(employee));
	}

	@Override
	public Mono<Void> deleteEmployee(String employeeId) {
		return employeeRespository.deleteById(employeeId);
	}

}
