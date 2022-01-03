package com.thinkingcode.hiringtest.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thinkingcode.hiringtest.exception.ResourceNotFoundException;
import com.thinkingcode.hiringtest.model.Employee;
import com.thinkingcode.hiringtest.repository.EmployeeRepository;


@RestController
@RequestMapping("/api/v1")
public class Employeecontroller {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		//return this.employeeRepository.findAll();
	 	
	  //List<Employee> employeeList = new employeeList;
	  
	 List<Employee> employeeList=employeeRepository.findAll();
	 return employeeList;
	  
	}
	
	//get employee by id
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId)
		throws ResourceNotFoundException {
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id::"+employeeId));
		return ResponseEntity.ok().body(employee);		
	}
	
	
	//save employee
	@PostMapping(value= "/createEmployee",consumes="application/json")
	
	public Employee createEmployee(@RequestBody Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	//update employee
	@PutMapping("updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id")long employeeId,
			 @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id::"+employeeId));
		
		employee.setEname(employeeDetails.getEname());
		employee.setProfession(employeeDetails.getProfession());
		employee.setAge(employeeDetails.getAge());
		
		return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
	
		
	
	//delete employee
	
	@DeleteMapping("deleteEmployee/{id}")
	public Map<String,Boolean>deleteEmployee(@PathVariable(value="id")long employeeId) throws ResourceNotFoundException{
		
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id::"+employeeId));

		this.employeeRepository.delete(employee);
		
		Map<String,Boolean> response= new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
		
	}
	
	

}
