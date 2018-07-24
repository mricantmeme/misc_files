package com.dicv.truck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.UserType;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType, Integer> {
	
	@Query("Select r from UserType r where r.userType=:userType")
	public UserType getUserType(@Param(value = "userType") String userType);

}
