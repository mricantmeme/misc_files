package com.training.sample.services;

import java.util.List;

import com.training.sample.dto.BookmarkObject;
import com.training.sample.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmp();

	Employee getEmp(long id);

	Employee getByName(String name);

	String addEmpDetails(BookmarkObject request);

	String updateEmpDetails(BookmarkObject request);

	void deleteEmp(Long id);
}