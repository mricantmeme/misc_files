package com.warehouse.catalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.catalogue.entity.Items;
import com.warehouse.catalogue.repository.ItemRepository;

@Service
public class ItemServiceImp implements ItemService{
	@Autowired
	ItemRepository itemRepository;
	@Override
	public List<Items> findAll() {
		return itemRepository.findByIstrue(true);
	}

	@Override
	public Items findItem(String name) {
		return null;
	}
	

}
