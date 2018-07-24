package com.warehouse.catalogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.catalogue.entity.Items;
import com.warehouse.catalogue.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	ItemService itemservice;
	@RequestMapping(value="/all")
	public List<Items> getAll()
	{
		List<Items> temp= itemservice.findAll();
		return temp;
	}
	
	@RequestMapping(value="/hi")
	public String get()
	{
		return "temp";
	}

}
