package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.GeoFenceShape;

@Repository
public interface GeoFenceShapeRepo extends JpaRepository<GeoFenceShape, Integer> {

	@Query("SELECT g FROM GeoFenceShape g WHERE g.geoFenceShapeName=:shapeName AND g.isDeleted=0")
	public List<GeoFenceShape> checkGeoFenceShape(@Param("shapeName") String shapeName);

}
