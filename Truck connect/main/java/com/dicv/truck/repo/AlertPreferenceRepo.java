package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.AlertPreference;

@Repository
public interface AlertPreferenceRepo extends JpaRepository<AlertPreference, Integer> {

	@Query("Select a from AlertPreference a where a.isDeleted=0 and a.dicvUser.userId=:userId")
	public List<AlertPreference> getAlertPreferenceList(@Param("userId") Integer userId);

}
