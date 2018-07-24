package com.dicv.truck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.FuelDropAlert;

@Repository
public interface FuelDropAlertRepo extends JpaRepository<FuelDropAlert, Long> {

}
