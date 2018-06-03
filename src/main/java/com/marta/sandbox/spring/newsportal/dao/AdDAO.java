package com.marta.sandbox.spring.newsportal.dao;

import com.marta.sandbox.spring.newsportal.entity.Ad;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AdDAO extends AbstractDAO {

    public Ad merge(Ad ad) {
        return em.merge(ad);
    }

    public List<Ad> getAdsList() {
        return em.createQuery("SELECT e FROM ad e", Ad.class).getResultList();
    }
}
