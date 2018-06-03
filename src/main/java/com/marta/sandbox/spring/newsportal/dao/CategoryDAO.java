package com.marta.sandbox.spring.newsportal.dao;

import com.marta.sandbox.spring.newsportal.entity.Category;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CategoryDAO extends AbstractDAO{

    public Category merge(Category category) {
        return em.merge(category);
    }

    public List<Category> getCategoryList() {
        return em.createQuery("SELECT e FROM category e", Category.class).getResultList();
    }

}
