package com.marta.sandbox.spring.newsportal.repository;

import com.marta.sandbox.spring.newsportal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
