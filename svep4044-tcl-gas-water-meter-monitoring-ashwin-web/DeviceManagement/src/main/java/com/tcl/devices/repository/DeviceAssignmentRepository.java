/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.devices.model.AssignDevice;

/*
 * This interface is used to connect with the mongo repository that is provided by spring 
 * to perform CRUD operations.
 */
public interface DeviceAssignmentRepository extends MongoRepository<AssignDevice, Long> {

}
