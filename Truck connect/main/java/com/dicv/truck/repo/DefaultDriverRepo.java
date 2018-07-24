package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DefaultDriver;


@Repository
public interface DefaultDriverRepo extends JpaRepository<DefaultDriver, Integer> {

	@Query("Select d from DefaultDriver d where d.vehicleId IS NOT NULL and d.isDeleted=0")
	public List<DefaultDriver> getVehicleListDriver();


}
