package com.tcl.devices.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.devices.model.ConsumptionDetail;


public interface ConsumptionDetailsRepo extends MongoRepository<ConsumptionDetail, Long> {

	Optional<ConsumptionDetail> findByPropertyIdAndIsActive(String propertyId, Boolean status);

}
