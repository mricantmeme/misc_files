package com.warehouse.catalogue.service;

import java.util.List;

import com.warehouse.catalogue.entity.Items;

public interface ItemService {
	Items findItem(String name);
	List<Items> findAll();
}
