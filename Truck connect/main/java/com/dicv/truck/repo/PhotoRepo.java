package com.dicv.truck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.Photo;


@Repository
public interface PhotoRepo extends JpaRepository<Photo,Integer> {


}
