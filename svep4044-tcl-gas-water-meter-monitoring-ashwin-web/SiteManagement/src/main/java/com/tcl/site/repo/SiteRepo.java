/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tcl.site.model.Site;

@Repository
public interface SiteRepo extends MongoRepository<Site, Long> {

	Optional<Site> findBySiteId(String siteId);

	List<Site> findByCreatedByAndIsActive(String userId, Boolean status, Pageable createPageRequest);

	List<Site> findBySiteNameContainsIgnoreCaseOrSiteDescriptionContainsIgnoreCaseOrEmailIdContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndIsActiveAndCreatedBy(
			String search, String search2, String search3, String search4, Boolean status, String userId,
			Pageable createPageRequest);

	List<Site> findByCreatedByAndIsActive(String userId, Boolean status);

	List<Site> findBySiteNameContainsIgnoreCaseOrSiteDescriptionContainsIgnoreCaseOrEmailIdContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndIsActiveAndCreatedBy(
			String search, String search2, String search3, String search4, String search5, Boolean status,
			String userId);

	List<Site> findBySiteIdIn(List<String> siteId);

	Optional<Site> findFirstByOrderByCreatedDateDesc();

}
