package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.Company;
import com.marta.sandbox.spring.avito.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Company get(Long id) {
		return companyRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Company company) {
		companyRepository.save(company);
	}

	@Override
	@Transactional
	public void remove(Company company) {
		companyRepository.delete(company);
	}

}
