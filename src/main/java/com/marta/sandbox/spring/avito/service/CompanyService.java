package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.Company;

import java.util.List;

public interface CompanyService {

	Company get(Long id);
	List<Company> getAll();
	void save(Company company);
	void remove(Company company);

}
