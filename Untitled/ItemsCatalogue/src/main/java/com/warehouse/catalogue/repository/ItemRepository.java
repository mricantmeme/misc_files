package com.warehouse.catalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.catalogue.entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
	Items findById(Long id);
	void deleteById(Long id);
	List<Items> findByIstrue(Boolean status);

}