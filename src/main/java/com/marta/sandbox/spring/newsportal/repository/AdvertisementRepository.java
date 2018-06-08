package com.marta.sandbox.spring.newsportal.repository;

import com.marta.sandbox.spring.newsportal.model.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("advertisementRepository")
public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {

    @Query("select a from Advertisement a where a.category.id = :categoryId")
    Collection<Advertisement> findAllByCategoryId(@Param("categoryId") long categoryId);

    @Query("select a.id from Advertisement a where a.category.id = :categoryId")
    Collection<String> findAllIdsByCategoryId(@Param("categoryId") long categoryId);

    @Query("select a from Advertisement a order by a.date desc")
    Page<Advertisement> findPage(Pageable pageable);

    @Query("select a.company from Advertisement a where a.id = :advertisementId")
    long findCompanyByAdvertisementId(@Param("advertisementId") String advertisementId);
}
