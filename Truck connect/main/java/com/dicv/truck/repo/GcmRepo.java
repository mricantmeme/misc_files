package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.Gcm;

@Repository
public interface GcmRepo extends CrudRepository<Gcm, Integer> {

	@Query("Select g.gcm from Vehicle g where g.vehicleId=:vehicleId and g.isDeleted=0")
	public List<Gcm> getGcmDetails(@Param("vehicleId") Integer vehicleId);

}
