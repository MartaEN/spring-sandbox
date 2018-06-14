package com.marta.sandbox.spring.avito.repository;

import com.marta.sandbox.spring.avito.domain.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, Long>{

	@Query("select a FROM Advertisement a WHERE a.category.id=:id")
	Page<Advertisement> findByCategoryId(@Param("id") Long id, Pageable pageable);

}
