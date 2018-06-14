package com.marta.sandbox.spring.avito.repository;

import com.marta.sandbox.spring.avito.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long > {

	

}
