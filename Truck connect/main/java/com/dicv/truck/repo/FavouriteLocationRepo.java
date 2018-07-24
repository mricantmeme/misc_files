package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.FavoriteLocation;

@Repository
public interface FavouriteLocationRepo extends JpaRepository<FavoriteLocation, Integer> {
	
	@Query("Select g from FavoriteLocation g where g.dicvUser.userId=:userId and g.isDeleted=0")
	public List<FavoriteLocation> getFavoriteLocationList(@Param("userId") Integer userId);


}
