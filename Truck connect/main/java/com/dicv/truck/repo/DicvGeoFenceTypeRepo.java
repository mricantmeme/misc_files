package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvGeoFenceType;

@Repository
public interface DicvGeoFenceTypeRepo extends JpaRepository<DicvGeoFenceType, Integer> {

	@Query("select t from DicvGeoFenceType t  where t.createdByUser.userId= :userId and t.isDeleted= 0")
	public List<DicvGeoFenceType> getGeoFenceTypeList(@Param("userId") Integer userId);
	
	@Query("select t.typeName from DicvGeoFenceType t where t.typeName=:typeName and t.isDeleted= 0")
	public List<String> checkGeoFenceTypeNameExist(@Param("typeName") String typeName);

}
