package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.VeicleUploadFileDto;
import com.dicv.truck.model.VehicleFileUpload;

@Repository
public interface VehicleFileUploadRepo extends JpaRepository<VehicleFileUpload, Integer> {

	@Query("Select new com.dicv.truck.dto.VeicleUploadFileDto(v.fileId,v.fileSize,v.fileName,v.description,v.fileType) from VehicleFileUpload v where"
			+ " v.vehicle.vehicleId=:vehicleId and v.isDeleted=0")
	public List<VeicleUploadFileDto> getFiles(@Param("vehicleId") Integer vehicleId);

}
