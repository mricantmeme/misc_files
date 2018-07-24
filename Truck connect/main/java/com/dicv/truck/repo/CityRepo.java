package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvCity;

@Repository
public interface CityRepo extends JpaRepository<DicvCity, Integer> {

	@Query("select d from DicvCity d where d.isDeleted=0 and d.dicvState.stateId =:stateId")
	public List<DicvCity> getCityByState(@Param("stateId") Integer stateId);

}
