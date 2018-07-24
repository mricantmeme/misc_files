package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.AlertType;

@Repository
public interface AlertTypeRepo extends JpaRepository<AlertType, Integer> {

	@Query("Select s from AlertType s where s.isDeleted=0")
	public List<AlertType> getAlertTypeList();

	@Query("Select a from AlertType a where a.alertType=:type and a.isDeleted=0")
	public List<AlertType> getAlertTypeByType(@Param("type") String type);

}
