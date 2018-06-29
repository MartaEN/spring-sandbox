package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface AdvertisementService {
	
	Page<Advertisement> getAll(Pageable pageable);
	Advertisement get(Long id);
	Page<Advertisement> getByCategoryId(Long id, Pageable pageable);

	@PreAuthorize( "hasAuthority('user') or hasAuthority('admin')")
	void save(Advertisement advertisement);

	@PreAuthorize ( "hasAuthority('admin')")
	void update ( Advertisement advertisement );

	@PreAuthorize ( "hasAuthority('admin')")
	void delete ( Long id );

}
