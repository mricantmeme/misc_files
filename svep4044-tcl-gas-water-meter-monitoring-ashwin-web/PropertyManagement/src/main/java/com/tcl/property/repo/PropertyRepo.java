/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tcl.property.model.Property;

@Repository
public interface PropertyRepo extends MongoRepository<Property, Long> {

	Optional<Property> findByPropertyId(String propertyId);

	List<Property> findByPropertyStatus(boolean b, Pageable createPageRequest);

	List<Property> findByPropertyStatus(Boolean status);

	List<Property> findByOwnerNameContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndPropertyStatus(String search,
			String search2, Boolean status);

	Optional<Property> findByOwnerEmailId(String ownerEmailId);

	Property findByOwnerName(String ownerName);

	List<Property> findByPropertyIdIn(List<String> propertyId);

	Optional<Property> findFirstByOrderByCreatedDateDesc();

	List<Property> findByOwnerNameContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndPropertyStatus(String search,
			String search2, boolean b, Pageable createPageRequest);

}
