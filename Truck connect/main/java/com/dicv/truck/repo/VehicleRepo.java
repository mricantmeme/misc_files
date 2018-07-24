package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.VehicleInfoDto;
import com.dicv.truck.dto.VehicleListDto;
import com.dicv.truck.model.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

	@Query("Select count(v.vehicleId) FROM Vehicle v where v.vehicleId!=:vehicleId and v.isDeleted=0 AND "
			+ " v.registrationId=:registrationId")
	public Long findByRegId(@Param("registrationId") String registrationId, @Param("vehicleId") Integer vehicleId);

	@Query("Select count(v.vehicleId) FROM Vehicle v where v.vehicleId!=:vehicleId and v.isDeleted=0 AND "
			+ " v.vin=:vin")
	public Long findByVin(@Param("vin") String vin, @Param("vehicleId") Integer vehicleId);

	@Query("Select d from Vehicle d where d.vehicleId=:vehicleId and d.isDeleted=0")
	public List<Vehicle> getVehicleDetails(@Param("vehicleId") Integer vehicleId);

	@Query("Select count(v.vehicleId) FROM Vehicle v where v.isDeleted=0 ")
	public Long vehicleCount();

	@Query("Select count(v.vehicleId) FROM Vehicle v where v.dicvUser.userId=:userId and v.isDeleted=0 ")
	public Long vehicleCount(@Param("userId") Integer userId);

	@Query("Select v FROM Vehicle v where  v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getVehicleList(Pageable pageable);

	@Query("Select v FROM Vehicle v where  v.dealerUser.userId=:userId and v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getDealerVehicleList(@Param("userId") Integer userId);

	@Query("Select v FROM Vehicle v where  v.dicvUser.userId=:userId and v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getVehicleList(@Param("userId") Integer userId);

	@Query("Select v FROM Vehicle v where  v.dicvUser.userId=:userId and v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getVehicleList(@Param("userId") Integer userId, Pageable pageable);

	@Query("Select v FROM Vehicle v where  v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getVehicleList();

	@Query("Select v FROM Vehicle v where  v.isDeleted=0 and v.companyId=232")
	public List<Vehicle> getVehicleFedexList();

	@Query("Select v.vehicleId FROM Vehicle v where  v.dicvUser.userId=:userId and v.isDeleted=0 ")
	public List<Integer> getVehicleIds(@Param("userId") Integer userId);

	@Query("Select v.vehicleId FROM Vehicle v where  v.isDeleted=0 ")
	public List<Integer> getVehicleIds();

	@Query("Select v.dicvCountry.countryCode,v.dicvCountry.countryName,count(v.vehicleId),v.runningStatus FROM Vehicle v "
			+ " where v.isDeleted=0 group by v.dicvCountry.countryCode,v.dicvCountry.countryName" + ",v.runningStatus")
	public List<Object[]> getVehicleCountryAndStatus();

	@Query("Select v.dicvCountry.countryCode,v.dicvCountry.countryName,count(v.vehicleId),v.runningStatus FROM Vehicle v "
			+ "where v.dicvUser.userId=:userId and v.isDeleted=0 group by v.dicvCountry.countryCode,v.dicvCountry.countryName"
			+ ",v.runningStatus")
	public List<Object[]> getVehicleCountryAndStatus(@Param("userId") Integer userId);

	@Query("Select v FROM Vehicle v where v.vehicleId IN :listOfVehicles and v.isDeleted=0")
	public List<Vehicle> getByVehicleIds(@Param("listOfVehicles") List<Integer> listOfVehicles);

	@Query("Select count(v.vehicleId) from Vehicle v where v.isDeleted=0 AND v.registrationId LIKE %:keyword%")
	public Long vehicleCount(@Param("keyword") String keyword);

	@Query("Select count(v.vehicleId) from Vehicle v where v.isDeleted=0 AND v.dicvUser.userId =:userId AND v.registrationId LIKE %:keyword%")
	public Long vehicleCount(@Param("userId") Integer userId, @Param("keyword") String keyword);

	@Query("Select v FROM Vehicle v where  v.isDeleted=0 AND v.registrationId LIKE %:keyword% order by modifiedDateTime desc")
	public List<Vehicle> getVehicleList(@Param("keyword") String keyword, Pageable pageable);

	@Query("Select v FROM Vehicle v where  v.dicvUser.userId=:userId and v.isDeleted=0 AND v.registrationId LIKE %:keyword% order by modifiedDateTime desc")
	public List<Vehicle> getVehicleList(@Param("userId") Integer userId, @Param("keyword") String keyword,
			Pageable pageable);

	@Query("Select new com.dicv.truck.dto.VehicleInfoDto(v.vehicleId,v.registrationId,v.description) FROM Vehicle v where  v.isDeleted=0 order by modifiedDateTime desc")
	public List<VehicleInfoDto> getActiveVehicles();

	@Query("Select new com.dicv.truck.dto.VehicleInfoDto(v.vehicleId,v.registrationId,v.description) FROM Vehicle v where  v.isDeleted=0 AND v.dicvUser.userId =:userId order by modifiedDateTime desc")
	public List<VehicleInfoDto> getActiveVehicles(@Param("userId") Integer userId);

	@Query("Select new com.dicv.truck.dto.VehicleListDto(v.vehicleId,v.registrationId) FROM Vehicle v where  v.vehicleId IN :vehicleIds and v.isDeleted=0")
	public List<VehicleListDto> loadVehicleWithNameAndId(@Param("vehicleIds") List<Integer> vehicleIds);

	@Query("Select count(v.vehicleId) from Vehicle v where v.isDeleted=0 and v.dicvCompany.companyId=:companyId")
	public Long vehicleCountBycompanyId(@Param("companyId") Integer companyId);

	@Query("Select count(v.vehicleId) from Vehicle v where v.isDeleted=0 and v.defaultDriver.userId=:userId")
	public Long getDefaultDriver(@Param("userId") Integer userId);

	@Query("Select v FROM Vehicle v where  v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getAllVehicleList();

	@Query("Select v FROM Vehicle v where  v.dicvUser.userId=:userId and v.isDeleted=0 order by modifiedDateTime desc")
	public List<Vehicle> getAllVehicleList(@Param("userId") Integer userId);

	@Query("Select count(v.vehicleId) FROM Vehicle v where  v.dicvGroup.groupId=:groupId and v.isDeleted=0")
	public Long getVehicleCountByGroupId(@Param("groupId") Integer groupId);

	@Query("Select count(v.vehicleId) FROM Vehicle v where  v.rootAdminGroup.groupId=:groupId and v.isDeleted=0")
	public Long getVehicleCountByRooTAdminGroupId(@Param("groupId") Integer groupId);

	@Query("Select count(v.vehicleId) FROM Vehicle v where  v.dicvCategory.categoryId=:categoryId and v.isDeleted=0")
	public Long getVehicleCountByCategory(@Param("categoryId") Integer categoryId);

	@Query("Select count(v.vehicleId) FROM Vehicle v where  v.dicvType.typeId=:typeId and v.isDeleted=0")
	public Long getVehicleCountByDicvType(@Param("typeId") Integer typeId);

}
