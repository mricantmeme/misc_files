package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.CategoryDto;
import com.dicv.truck.model.DicvCategory;

@Repository
public interface DicvCategoryRepo extends JpaRepository<DicvCategory, Integer> {

	@Query("select new com.dicv.truck.dto.CategoryDto(t.categoryId,t.categoryName,t.createdByUser.userId) "
			+ " from DicvCategory t where t.createdByUser.userId =:userId  and t.isDeleted=0")
	public List<CategoryDto> getCategory(@Param("userId") Integer userId);

	@Query("select d from DicvCategory d where  d.createdByUser.userId=:userId and d.isDeleted=0")
	public List<DicvCategory> getCategoryList(@Param("userId") Integer userId);

}
