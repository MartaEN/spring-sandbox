package com.marta.sandbox.spring.avito.service;

import java.util.List;

import com.marta.sandbox.spring.avito.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marta.sandbox.spring.avito.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Category get(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void remove(Category category) {
		categoryRepository.delete(category);
	}
	
	

}
