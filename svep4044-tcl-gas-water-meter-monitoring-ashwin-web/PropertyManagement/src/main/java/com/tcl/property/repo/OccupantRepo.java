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

import com.tcl.property.model.Occupant;

public interface OccupantRepo extends MongoRepository<Occupant, Long> {

	Optional<Occupant> findByOccupantId(String occupantId);

	List<Occupant> findByOccupantIdAndIsActive(String occupantId, boolean b);

	List<Occupant> findByIsActiveAndPropertyId(boolean b, String propertyId, Pageable createPageRequest);

	List<Occupant> findByIsActiveAndPropertyId(Boolean status, String propertyId);

	List<Occupant> findByFirstNameContainsIgnoreCaseOrMiddleNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrOccupantEmailIdContainsIgnoreCaseAndIsActiveAndPropertyId(
			String search, String search1, String search2, String search3, boolean b, String search4,
			Pageable createPageRequest);

	List<Occupant> findByFirstNameContainsIgnoreCaseOrMiddleNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrOccupantEmailIdContainsIgnoreCaseAndIsActiveAndPropertyId(
			String search, String search1, String search2, String search3, boolean b, String search4);

	List<Occupant> findByOccupantIdInAndIsActive(List<String> occupantId, boolean b);

	Optional<Occupant> findFirstByOrderByCreatedDateDesc();

	List<Occupant> findByOccupantIdAndIsActive(String occupantId, boolean b, Pageable createPageRequest);

}
