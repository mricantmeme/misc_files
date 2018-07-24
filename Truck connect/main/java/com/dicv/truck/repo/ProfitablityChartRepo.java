package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.ProfitabilityChart;

@Repository
public interface ProfitablityChartRepo extends JpaRepository<ProfitabilityChart, Integer> {

	@Query("Select p from ProfitabilityChart p "
			+ " where p.isDeleted=0 and  p.dicvUserCreatedBy.userId=:userId and p.dicvUserCreatedBy.recordStatus!='d'")
	public List<ProfitabilityChart> getProfitabilityChart(@Param("userId") Integer userId);

}
