package com.tcl.devices.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcl.devices.model.DeviceHistory;

public interface DeviceHistoryRepository extends MongoRepository<DeviceHistory, String> {

	List<DeviceHistory> findByDeviceId(String deviceId, Pageable createSortPageRequest);

	List<DeviceHistory> findByDeviceId(String deviceId);

}
