package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertisementService {
	
	Page<Advertisement> getAll(Pageable pageable);
	Advertisement get(Long id);
	void save(Advertisement advertisement);
	Page<Advertisement> getByCategoryId(Long id, Pageable pageable);

}
