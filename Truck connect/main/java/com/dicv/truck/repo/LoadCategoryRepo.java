package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.LoadCategory;

@Repository
public interface LoadCategoryRepo extends JpaRepository<LoadCategory, Integer> {

	@Query("Select lc from LoadCategory lc  where lc.isDeleted=0 and lc.dicvUserCreatedBy.userId=:userId and "
			+ "lc.dicvUserCreatedBy.recordStatus!='d'")
	public List<LoadCategory> getLoadCategoryList(@Param("userId") Integer userId);

}
