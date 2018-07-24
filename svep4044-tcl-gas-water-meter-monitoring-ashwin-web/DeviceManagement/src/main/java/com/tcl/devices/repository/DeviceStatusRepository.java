/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.devices.model.DeviceStatus;

/*
 * This interface is used to connect with the mongo repository that is provided by spring.
 */
public interface DeviceStatusRepository extends MongoRepository<DeviceStatus, Long> {


	List<DeviceStatus> findByIsActive(Boolean b);

	Optional<DeviceStatus> findByStatusIdAndIsActive(String status, boolean b);

	DeviceStatus findByStatusAndIsActive(String string, boolean b);
}

