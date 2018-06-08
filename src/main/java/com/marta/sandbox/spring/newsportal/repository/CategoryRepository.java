package com.marta.sandbox.spring.newsportal.repository;

import com.marta.sandbox.spring.newsportal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
