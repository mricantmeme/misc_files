package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.UserGroupDto;
import com.dicv.truck.dto.UserSelectedDto;
import com.dicv.truck.model.DicvUser;

@Repository
public interface UserRepo extends JpaRepository<DicvUser, Integer> {

	@Query("Select d from DicvUser d where  d.recordStatus!='d'")
	public List<DicvUser> getAllUser();

	@Query("Select d from DicvUser d where d.userName=:userName and d.recordStatus!=:recordStatus")
	public List<DicvUser> getUserDetails(@Param("userName") String userName,
			@Param("recordStatus") String recordStatus);

	@Query("Select d from DicvUser d where d.userId=:userId and d.recordStatus!=:recordStatus")
	public List<DicvUser> getUserDetails(@Param("userId") Integer userId, @Param("recordStatus") String recordStatus);

	@Query("Select count(d.userId) from DicvUser d where d.drivingLicenseNo=:drivingLicenseNo and d.userId!=:userId and d.recordStatus!=:recordStatus")
	public Long checkLicenseAvailable(@Param("drivingLicenseNo") String drivingLicenseNo,
			@Param("userId") Integer userId, @Param("recordStatus") String recordStatus);

	@Query("Select count(d.userId) from DicvUser d where d.userName=:userName and d.userId!=:userId and d.recordStatus!=:recordStatus")
	public Long checkUserNameExist(@Param("userName") String userName, @Param("userId") Integer userId,
			@Param("recordStatus") String recordStatus);

	@Query("Select count(d.userId) from DicvUser d where d.managerId=:userId and d.recordStatus!=:recordStatus "
			+ "and d.userType.userType=:userType")
	public Long countOfUser(@Param("userId") Integer userId, @Param("userType") String userType,
			@Param("recordStatus") String recordStatus);

	@Query("Select count(d.userId) from DicvUser d where d.recordStatus!=:recordStatus and d.userType.userType=:userType")
	public Long countOfUser(@Param("userType") String userType, @Param("recordStatus") String recordStatus);

	@Query("Select count(d.userId) from DicvUser d where d.managerId=:userId and d.recordStatus!=:recordStatus and d.userType.userType=:userType "
			+ " AND (d.firstName LIKE %:keyword% OR d.lastName LIKE %:keyword% OR d.userName LIKE %:keyword%)")
	public Long countUserByRoleAndKeyword(@Param("userId") Integer userId, @Param("userType") String userType,
			@Param("keyword") String keyword, @Param("recordStatus") String recordStatus);

	@Query("Select count(d.userId) from DicvUser d where d.recordStatus!=:recordStatus and d.userType.userType=:userType "
			+ "AND (d.firstName LIKE %:keyword% OR d.lastName LIKE %:keyword% OR d.userName LIKE %:keyword%)")
	public Long countUserByRoleAndKeyword(@Param("userType") String userType, @Param("keyword") String keyword,
			@Param("recordStatus") String recordStatus);

	@Query("Select d from DicvUser d where d.managerId=:userId and d.userType.userType=:userType and d.recordStatus!=:recordStatus order by modifiedDate desc")
	public List<DicvUser> getUserByRole(@Param("userId") Integer userId, @Param("userType") String userType,
			@Param("recordStatus") String recordStatus, Pageable pageable);

	@Query("Select d from DicvUser d where d.managerId=:userId and d.userType.userType=:userType and d.recordStatus!=:recordStatus order by modifiedDate desc")
	public List<DicvUser> getUserByRole(@Param("userId") Integer userId, @Param("userType") String userType,
			@Param("recordStatus") String recordStatus);

	@Query("Select d from DicvUser d where d.recordStatus!=:recordStatus and d.userType.userType=:userType order by modifiedDate desc")
	public List<DicvUser> getUserByRole(@Param("userType") String userType, @Param("recordStatus") String recordStatus,
			Pageable pageable);

	@Query("Select d from DicvUser d where d.recordStatus!=:recordStatus and d.userType.userType=:userType order by modifiedDate desc")
	public List<DicvUser> getUserByRole(@Param("userType") String userType, @Param("recordStatus") String recordStatus);

	@Query("Select d from DicvUser d where d.managerId=:userId and d.recordStatus!=:recordStatus and d.userType.userType=:userType "
			+ "AND (d.firstName LIKE %:keyword% OR d.lastName LIKE %:keyword% OR d.userName LIKE %:keyword%) order by modifiedDate desc")
	public List<DicvUser> getUserByRoleAndKeyword(@Param("userId") Integer userId, @Param("userType") String userType,
			@Param("keyword") String keyword, @Param("recordStatus") String recordStatus, Pageable pageable);

	@Query("Select d from DicvUser d where d.userType.userType=:userType and d.recordStatus!=:recordStatus "
			+ "AND (d.firstName LIKE %:keyword% OR d.lastName LIKE %:keyword% OR d.userName LIKE %:keyword%) order by modifiedDate desc")
	public List<DicvUser> getUserByRoleAndKeyword(@Param("userType") String userType, @Param("keyword") String keyword,
			@Param("recordStatus") String recordStatus, Pageable pageable);

	@Query("Select new com.dicv.truck.dto.UserSelectedDto(d.userId,d.firstName,d.lastName) from DicvUser d where d.managerId=:managerId "
			+ "and d.userType.userType=:userType and d.recordStatus!=:recordStatus order by modifiedDate desc")
	public List<UserSelectedDto> getSelectedUsers(@Param("managerId") Integer managerId,
			@Param("userType") String userType, @Param("recordStatus") String recordStatus);

	@Query("Select new com.dicv.truck.dto.UserSelectedDto(d.userId,d.firstName,d.lastName) from DicvUser d where "
			+ " d.userType.userType=:userType and d.recordStatus!=:recordStatus order by modifiedDate desc")
	public List<UserSelectedDto> getSelectedUsers(@Param("userType") String userType,
			@Param("recordStatus") String recordStatus);


	@Query("Select new com.dicv.truck.dto.UserSelectedDto(d.userId,d.userName) from DicvUser d where d.managerId=:managerId "
			+ "and d.userType.userType=:userType and d.recordStatus!=:recordStatus order by modifiedDate desc")
	public List<UserSelectedDto> getDriverSelectedUsers(@Param("managerId") Integer managerId,
			@Param("userType") String userType, @Param("recordStatus") String recordStatus);

	@Query("Select new com.dicv.truck.dto.UserSelectedDto(d.userId,d.userName) from DicvUser d where "
			+ " d.userType.userType=:userType and d.recordStatus!=:recordStatus order by modifiedDate desc")
	public List<UserSelectedDto> getDriverSelectedUsers(@Param("userType") String userType,
			@Param("recordStatus") String recordStatus);
	
	@Query("SELECT new com.dicv.truck.dto.UserGroupDto(u.userId,u.dicvGroup.groupName,u.dicvGroup.groupId) FROM DicvUser u"
			+ " where u.managerId=:userId and u.userType.userType =:userType and u.recordStatus!='d' ")
	public List<UserGroupDto> getUserGroupDto(@Param("userId") Integer userId, @Param("userType") String userType);

	@Query("SELECT new com.dicv.truck.dto.UserGroupDto(u.userId,u.dicvGroup.groupName,u.dicvGroup.groupId) FROM DicvUser u"
			+ " where u.userType.userType =:userType and u.recordStatus!='d' ")
	public List<UserGroupDto> getUserGroupDto(@Param("userType") String userType);

	@Query("Select count(d.userId) from DicvUser d where d.recordStatus!='d' and d.dicvCompany.companyId=:companyId")
	public Long userCountBycompanyId(@Param("companyId") Integer companyId);

	@Query("Select d.userId from DicvUser d where d.recordStatus!='d' and d.dicvCompany.companyId=:companyId and d.userType.userType=:userType")
	public List<Integer> getUserIdByCompany(@Param("companyId") Integer companyId, @Param("userType") String userType);

	@Query("Select new com.dicv.truck.dto.UserSelectedDto(d.userId,d.firstName,d.lastName) from DicvUser d where "
			+ " d.userType.userType=:userType and d.dicvCompany.companyId=:companyId and d.recordStatus!='d' order by modifiedDate desc")
	public List<UserSelectedDto> getCustomerByCompany(@Param("companyId") Integer companyId,
			@Param("userType") String userType);

	@Query("Select count(d.userId) from DicvUser d where d.recordStatus!='d' and d.dicvGroup.groupId=:groupId")
	public Long userCountByGroupId(@Param("groupId") Integer groupId);

}
