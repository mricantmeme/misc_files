package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.KPIScalingFactor;

@Repository
public interface KPIScalingFactorRepo extends JpaRepository<KPIScalingFactor, Integer> {

	@Query("Select k from KPIScalingFactor k")
	public List<KPIScalingFactor> getKPIScalingFactor();

}
