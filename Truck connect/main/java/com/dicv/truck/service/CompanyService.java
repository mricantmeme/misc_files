package com.dicv.truck.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.model.DicvCompany;
import com.dicv.truck.repo.CompanyRepo;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepo companyRepo;

	private static final Logger LOGGER = Logger.getLogger(CompanyService.class);

	public DicvCompany findByCompanyName(String companyName, String companyAddress) {
		List<DicvCompany> dicvCompanyList = companyRepo.getCompany(companyName);
		DicvCompany dicvCompany = new DicvCompany();
		if (null == dicvCompanyList || dicvCompanyList.isEmpty()) {
			dicvCompany = new DicvCompany();
			dicvCompany.setCompanyName(companyName);
		} else
			dicvCompany = dicvCompanyList.get(0);
		dicvCompany.setCompanyAddress(companyAddress);
		try {
			dicvCompany = companyRepo.save(dicvCompany);
		} catch (PersistenceException persistenceException) {
			LOGGER.info("Error in Save Company :: ", persistenceException);
			return null;
		}
		return dicvCompany;
	}

}
