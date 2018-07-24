/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.devices.dto.DeviceResponseDto;
import com.tcl.devices.model.Device;

/*
 * This interface is used to connect with the mongo repository that is provided by spring.
 */
public interface DeviceRepositiry extends MongoRepository<Device, String> {

	Optional<Device> findBySerialNumber(String string);

	Optional<Device> findFirstByOrderByCreatedDateDesc();

	Optional<Device> findByDeviceId(String deviceId);

	List<Device> findByDeviceIdContainsIgnoreCaseOrSerialNumberContainsIgnoreCaseAndIsActive(String search,
			String search2, boolean b, Pageable createPageRequest);

	List<Device> findByIsActive(boolean b, Pageable pageable);

	List<Device> findByIsActive(Boolean b);

	List<DeviceResponseDto> findByDeviceIdContainsIgnoreCaseOrSerialNumberContainsIgnoreCaseAndIsActive(String search,
			String search2, boolean b);

	List<Device> findByDeviceIdIn(List<String> deviceId);

	List<Device> findByDeviceIdInAndIsActive(List<String> deviceId, boolean b);

	Optional<Device> findByDeviceIdAndIsActive(String deviceId, boolean b);

	Optional<Device> findByDeviceIdAndIsActive(String deviceId);

	List<Device> findByStatusNot(String status, Pageable pageable);

	List<Device> findByStatusNot(String string);

	List<Device> findByDeviceIdContainsIgnoreCaseOrSerialNumberContainsIgnoreCaseOrDeviceEuiContainsIgnoreCaseAndIsActive(
			String search, String search2, String search3, boolean b, Pageable createPageRequest);

}