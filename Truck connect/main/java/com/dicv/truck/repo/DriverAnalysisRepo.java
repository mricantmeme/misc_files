package com.dicv.truck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.DriverAnalysisList;

@Repository
public interface DriverAnalysisRepo extends JpaRepository<DriverAnalysisList, Long> {

}
