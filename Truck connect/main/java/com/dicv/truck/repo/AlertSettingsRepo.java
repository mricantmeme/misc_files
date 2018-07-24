package com.dicv.truck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.AlertSettings;

@Repository
public interface AlertSettingsRepo extends JpaRepository<AlertSettings, Integer> {

	@Query("Select v from AlertSettings v where  v.dicvUser.userId=:userId ")
	public AlertSettings getAlertSettings(@Param(value = "userId") Integer userId);

}
