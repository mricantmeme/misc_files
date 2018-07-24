package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DeviceHealth;

@Repository
public interface DeviceHealthRepo extends JpaRepository<DeviceHealth, Integer> {

	@Query("select d from DeviceHealth d where d.vehicle.isDeleted=0")
	public List<DeviceHealth> getDeviceHealthReport();

}
