package com.marta.sandbox.spring.newsportal.service;

import com.marta.sandbox.spring.newsportal.exception.EntityNotFoundException;
import com.marta.sandbox.spring.newsportal.model.Advertisement;
import com.marta.sandbox.spring.newsportal.model.Company;
import com.marta.sandbox.spring.newsportal.repository.AdvertisementRepository;
import com.marta.sandbox.spring.newsportal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyService {

    private CompanyRepository companyRepository;
    private AdvertisementRepository advertisementRepository;

    public CompanyService (@Qualifier("companyRepository") CompanyRepository companyRepository,
                           @Qualifier("advertisementRepository") AdvertisementRepository advertisementRepository) {
        this.companyRepository = companyRepository;
        this.advertisementRepository = advertisementRepository;
    }

    /**
     * Метод получения компании по её идентификатору. В случае, если компания не
     * найдена, выбрасывается исключение @{@link EntityNotFoundException}
     * @param companyId идентификатор категории
     * @return выбранная категория
     */
    public Company getCompany(long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(companyId, Company.class));
    }

    /**
     * Метод получения компании по идентификатору объявления. В случае, если объявление
     * не найдено, выбрасывается исключение @{@link EntityNotFoundException}
     * @param advertisementId идентификатор объявления
     * @return компания, подавшая данное объявление
     */
    public Company getCompanyByAdvertisementId (String advertisementId) {
        return companyRepository.findById(advertisementRepository.findCompanyByAdvertisementId(advertisementId))
                .orElseThrow(()-> new EntityNotFoundException(advertisementId, Advertisement.class));
    }

}
