package com.marta.sandbox.spring.avito.service;

import com.marta.sandbox.spring.avito.domain.Advertisement;
import com.marta.sandbox.spring.avito.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Page<Advertisement> getAll(Pageable pageable) {
		return advertisementRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Advertisement get(Long id) {
		return advertisementRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(Advertisement advertisement) {
		advertisementRepository.save(advertisement);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Advertisement> getByCategoryId(Long id, Pageable pageable) {
		return advertisementRepository.findByCategoryId(id, pageable);
	}
}
