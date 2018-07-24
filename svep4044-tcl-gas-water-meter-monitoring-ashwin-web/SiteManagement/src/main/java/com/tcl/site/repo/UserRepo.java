/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tcl.site.model.Site;
import com.tcl.site.model.User;

@Repository
public interface UserRepo extends MongoRepository<User, Long> {

	public User findByUserName(String userName);

	public Optional<User> findByUserId(String userId);

	public Optional<User> findByEmailId(String customerEmailId);

	public Optional<User> findFirstByOrderByCreatedDateDesc();

	public User findByUserNameContainsIgnoreCase(String lowerCase);

	public Optional<User> findBySite(Site site);

	public List<User> findBySiteIn(List<Site> site);

}
