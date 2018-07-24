package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.Dispatch;

@Repository
public interface DispatchRepo extends CrudRepository<Dispatch, Integer> {

	@Query("Select t from Dispatch t where t.trip.tripId=:tripId")
	public List<Dispatch> getDispatch(@Param("tripId") Long tripId);

}
