package com.tcl.devices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.devices.model.Payload;


public interface PayloadRepo  extends MongoRepository<Payload, Long>{

}
