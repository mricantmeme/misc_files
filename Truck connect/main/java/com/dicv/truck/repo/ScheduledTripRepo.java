package com.dicv.truck.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.ScheduledTrip;

@Repository
public interface ScheduledTripRepo extends CrudRepository<ScheduledTrip, Integer> {

}
