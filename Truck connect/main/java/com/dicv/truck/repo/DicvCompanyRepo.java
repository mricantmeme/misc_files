package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvCompany;

@Repository
public interface DicvCompanyRepo extends JpaRepository<DicvCompany, Integer> {

	@Query("Select c from DicvCompany c where c.isDeleted=0 order by companyName asc ")
	public List<DicvCompany> getCompanyList();

}
