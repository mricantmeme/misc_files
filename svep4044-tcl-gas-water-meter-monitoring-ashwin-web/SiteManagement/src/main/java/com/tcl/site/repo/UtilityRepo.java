/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tcl.site.model.Utility;

@Repository
public interface UtilityRepo extends MongoRepository<Utility, Long> {

	Optional<Utility> findByUtilityId(String siteUtilityId);

}
