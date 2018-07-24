package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvCompany;

@Repository
public interface CompanyRepo extends JpaRepository<DicvCompany, Integer> {

	@Query("SELECT d FROM DicvCompany d where d.companyName=:companyName")
	public List<DicvCompany> getCompany(@Param("companyName") String companyName);
	
}
