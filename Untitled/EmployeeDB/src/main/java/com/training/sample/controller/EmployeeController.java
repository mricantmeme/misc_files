package com.training.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.sample.dto.BookmarkObject;
import com.training.sample.entity.Employee;
import com.training.sample.services.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value= "/all",method=RequestMethod.GET)
	public List<Employee> getAllDetails()
	{
		List<Employee> temp= employeeService.getAllEmp();
		return  temp;
	}
	@RequestMapping("/temp")
	public String temp()
	{
		return "disp temp";
	}
	@RequestMapping(value="/getid/{id}", method=RequestMethod.GET)
	public Employee getEmp(@PathVariable Long id)
	{
//		Employee particular= employeeService.getEmp(id);
		return employeeService.getEmp(id);
	}
	@RequestMapping(value="/getname/{name}", method= RequestMethod.GET)
	public Employee getByName(@PathVariable String name)
	{
		return employeeService.getByName(name);
	}
	
	@RequestMapping(value="/add/detail", method= RequestMethod.POST)
	public String AddEmpDetails(@RequestBody BookmarkObject request)
	{
		return employeeService.addEmpDetails(request);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String UpdateDetails(@RequestBody BookmarkObject request)
	{
		
		return employeeService.updateEmpDetails(request);
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String removeEmp(@PathVariable Long id)
	{
		employeeService.deleteEmp(id);
		return " Delete Successfull!!";	
	}
}