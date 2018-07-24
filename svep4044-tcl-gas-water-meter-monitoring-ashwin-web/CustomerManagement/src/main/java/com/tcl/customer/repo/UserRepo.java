/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tcl.customer.model.Customer;
import com.tcl.customer.model.User;

/*
 * This interface is used to connect with the mongo repository that is provided by spring to perform CRUD operations.
 */
@Repository
public interface UserRepo extends MongoRepository<User, Long> {

	public User findByUserNameContainsIgnoreCase(String userName);

	public Optional<User> findByUserId(String userId);

	public Optional<User> findByEmailId(String customerEmailId);

	public Optional<User> findFirstByOrderByCreatedDateDesc();

	public Optional<User> findByCustomer(Customer updateStatus);

	public List<User> findByCustomerIn(List<Customer> customer);

}
