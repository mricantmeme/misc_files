package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DicvUser;

@Repository
public interface LoginRepo extends JpaRepository<DicvUser, Integer> {

	@Query("Select d from DicvUser d where d.userName=:userName and d.recordStatus!=:recordStatus")
	public List<DicvUser> getUserDetails(@Param("userName") String userName,
			@Param("recordStatus") String recordStatus);

	@Query("Select d from DicvUser d where LOWER(d.userName) = LOWER(:userName) and d.recordStatus!=:recordStatus")
	public List<DicvUser> getUserDetailsByUserName(@Param("userName") String userName,
			@Param("recordStatus") String recordStatus);

}
