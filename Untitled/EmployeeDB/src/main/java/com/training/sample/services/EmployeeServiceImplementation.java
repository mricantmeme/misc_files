package com.training.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sample.dto.BookmarkObject;
import com.training.sample.entity.Employee;
import com.training.sample.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmp()
	{
		List<Employee> AllDetails = employeeRepository.findByIsactive(true);
		return 	AllDetails;
	}
	@Override
	public Employee getEmp(long id) {

		Employee particular = employeeRepository.findByEid(id);
		return particular;
	}
	@Override
	public Employee getByName(String name) {
		//		Employee getname= employeeRepository.findByEname(name);
		return employeeRepository.findByEname(name);

	}

	@Override
	public String addEmpDetails(BookmarkObject request)
	{
		Employee addEmployee = new Employee();
		addEmployee.setAge(request.getAge());
		addEmployee.setDept(request.getDept());
		addEmployee.setEname(request.getEname());
		addEmployee.setSalary(request.getSalary());
		addEmployee.setIsactive(true);
		employeeRepository.save(addEmployee);

		return "</ bold Operation Successfull!!>";
	}
	@Override
	public String updateEmpDetails(BookmarkObject request) {
		if(request.getEid() != null) 
		{

			Employee update= employeeRepository.findByEid(request.getEid());
			if(update !=null) 
			{
				update.setEid(request.getEid());
				update.setEname(request.getEname());
				update.setAge(request.getAge());
				update.setDept(request.getDept());
				update.setSalary(request.getSalary());
				employeeRepository.save(update);
				return "Update Successfull";
			}
			else
				return "EId is not found in DB";	

		}

		else
			return "EId is not entered"; 
	}
	@Override
	public void deleteEmp(Long id) 
	{
		if(id != null)
		{
			employeeRepository.deleteByEid(id);
		}
	}
}
/*Employee addEmployee=new Employee();
		addEmployee.setAge(age);
		employeeRepository.save(addEmployee);*/