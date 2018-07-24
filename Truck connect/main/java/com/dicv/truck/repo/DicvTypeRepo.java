package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.TypeDto;
import com.dicv.truck.model.DicvType;

@Repository
public interface DicvTypeRepo extends JpaRepository<DicvType, Integer> {

	@Query("select new com.dicv.truck.dto.TypeDto(t.typeId,t.typeName,t.subTypeId,t.createdByUser.userId) "
			+ "from DicvType t where t.createdByUser.userId =:userId  and t.isDeleted=0 and t.subTypeId=:subTypeId ")
	public List<TypeDto> getSubType(@Param("userId") Integer userId, @Param("subTypeId") Integer subTypeId);

	@Query("select t from DicvType t where t.createdByUser.userId=:userId and t.isDeleted=0")
	public List<DicvType> getTypeList(@Param("userId") Integer userId);

}
