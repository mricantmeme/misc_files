package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvRegion;

@Repository
public interface DicvRegionRepo extends JpaRepository<DicvRegion, Integer> {

	@Query("Select d from DicvRegion d where d.createdByUser=:createdByUser and d.isDeleted=0")
	public List<DicvRegion> findAllRegions(@Param("createdByUser") Integer createdByUser);

}
