package com.training.sample.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.training.sample.entity.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByIsactive(Boolean active);
	Employee findByEid(Long id);
	Employee findByEname(String name);
	void deleteByEid(Long id);
}