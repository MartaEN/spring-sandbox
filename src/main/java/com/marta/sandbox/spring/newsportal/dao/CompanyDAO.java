package com.marta.sandbox.spring.newsportal.dao;

import com.marta.sandbox.spring.newsportal.entity.Company;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CompanyDAO extends AbstractDAO {

    public Company merge(Company company) {
        return em.merge(company);
    }

    public List<Company> getCompanyList() {
        return em.createQuery("SELECT e FROM company e", Company.class).getResultList();
    }
}
