package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.GeoFenceInfo;

@Repository
public interface GeoFenceRepo extends JpaRepository<GeoFenceInfo, Integer> {

	@Query("Select g from GeoFenceInfo g where g.dicvUser.userId=:userId and g.isDeleted = 0")
	public List<GeoFenceInfo> getGeoFenceList(@Param("userId") Integer userId);

	@Query("SELECT count(g.geoFenceId) FROM GeoFenceInfo g where g.geoFenceName=:geoFenceName and g.isDeleted = 0 and g.dicvUser.userId=:userId")
	public Long checkGeoFenceNameExist(@Param("userId") Integer userId, @Param("geoFenceName") String geoFenceName);

	@Query("SELECT count(g.geoFenceId) FROM GeoFenceInfo g where g.geoFenceName=:geoFenceName and g.geoFenceId!=:geoFenceId and g.isDeleted = 0 and g.dicvUser.userId=:userId")
	public Long checkGeoFenceNameExist(@Param("userId") Integer userId, @Param("geoFenceId") Integer geoFenceId,
			@Param("geoFenceName") String geoFenceName);

}
