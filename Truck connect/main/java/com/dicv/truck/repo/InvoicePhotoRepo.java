package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.InvoicePhoto;

@Repository
public interface InvoicePhotoRepo extends CrudRepository<InvoicePhoto, Integer> {

	@Query("Select d from InvoicePhoto d where d.dispatch.trip.tripId IN :tripId ")
	public List<InvoicePhoto> getInvoicePhoto(@Param("tripId") List<Long> tripId);

}
