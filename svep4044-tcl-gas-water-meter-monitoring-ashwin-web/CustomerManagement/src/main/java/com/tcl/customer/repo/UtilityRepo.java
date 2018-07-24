/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.customer.model.Utility;

/*
 * This interface is used to connect with the mongo repository that is provided by spring to perform CRUD operations.
 */
public interface UtilityRepo extends MongoRepository<Utility, Long> {

	Optional<Utility> findByUtilityId(String customerUtilityId);

}
