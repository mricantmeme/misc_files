/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.customer.model.Customer;

/*
 * This interface is used to connect with the mongo repository that is provided by spring to perform CRUD operations.
 */
public interface CustomerRepo extends MongoRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	List<Customer> findByIsActive(boolean b, Pageable createPageRequest);

	List<Customer> findByCustomerNameContainsIgnoreCaseOrCustomerDescriptionContainsIgnoreCaseOrCustomerEmailIdContainsIgnoreCaseOrCustomerGroupEmailIdContainsIgnoreCaseOrCustomerMobileNumberContainsIgnoreCaseOrUtilityIdContainsIgnoreCaseAndIsActive(
			String search, String search2, String search3, String search4, String search5, String search6, boolean b,
			Pageable createPageRequest);

	List<Customer> findByIsActive(Boolean status);

	List<Customer> findByCustomerNameContainsIgnoreCaseOrCustomerDescriptionContainsIgnoreCaseOrCustomerEmailIdContainsIgnoreCaseOrCustomerGroupEmailIdContainsIgnoreCaseOrCustomerMobileNumberContainsIgnoreCaseOrUtilityIdContainsIgnoreCaseAndIsActive(
			String search, String search2, String search3, String search4, String search5, String search6,
			Boolean status);

	List<Customer> findByCustomerIdIn(List<String> customerId);

	Optional<Customer> findFirstByOrderByModifiedDateDesc();


}
