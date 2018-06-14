package com.marta.sandbox.spring.avito.service;

import java.util.List;

import com.marta.sandbox.spring.avito.domain.Category;

public interface CategoryService {

	Category get(Long id);
	List<Category> getAll();
	void save(Category category);
	void remove(Category category);

}
