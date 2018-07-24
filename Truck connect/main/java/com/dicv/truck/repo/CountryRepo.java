package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvCountry;

@Repository
public interface CountryRepo extends JpaRepository<DicvCountry, Integer> {

	@Query("select d from DicvCountry d where d.isDeleted=0")
	public List<DicvCountry> getAllCountry();
	
	@Query("select d from DicvCountry d where d.isDeleted=0 and d.countryId=:countryId")
	public List<DicvCountry> getCountryDetails(@Param("countryId")Integer countryId);

}
