package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvGroup;

@Repository
public interface GroupRepo extends JpaRepository<DicvGroup, Integer> {

	@Query("SELECT d FROM DicvGroup d where d.createdByUser.userId=:userId and d.isDeleted=0")
	public List<DicvGroup> getDicvGroup(@Param("userId") Integer userId);

}
