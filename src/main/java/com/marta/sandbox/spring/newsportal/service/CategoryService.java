package com.marta.sandbox.spring.newsportal.service;

import com.marta.sandbox.spring.newsportal.exception.EntityNotFoundException;
import com.marta.sandbox.spring.newsportal.model.Advertisement;
import com.marta.sandbox.spring.newsportal.model.Category;
import com.marta.sandbox.spring.newsportal.model.Company;
import com.marta.sandbox.spring.newsportal.repository.AdvertisementRepository;
import com.marta.sandbox.spring.newsportal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("categoryService")
public class CategoryService {

    private CategoryRepository categoryRepository;
    private AdvertisementRepository advertisementRepository;

    public CategoryService(@Qualifier("categoryRepository") CategoryRepository categoryRepository,
                           @Qualifier("advertisementRepository") AdvertisementRepository advertisementRepository) {
        this.categoryRepository = categoryRepository;
        this.advertisementRepository = advertisementRepository;
    }

    /**
     * Метод добавления категории
     * @param category сохраняемый объект категории
     * @return сохранённый объект категории
     */
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Метод получения категории по идентификатору. В случае, если категория не
     * найдена, выбрасывается исключение @{@link EntityNotFoundException}
     * @param categoryId идентификатор категории
     * @return выбранная категория
     */
    public Category getCategory(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(categoryId, Category.class));
    }

    /**
     * Метод получения списка всех категорий в системе
     * @return итератор категорий
     */
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Метод удаления категории с переносом всех объявлений в новую категорию. Если целевая категория не найдена,
     * выбрасывается исключение @{@link EntityNotFoundException}.
     * Так как метод может занимать длительное время, то выполняется он асинхронно в отдельной транзакции.
     * Уровень изоляции запрещает чтение статей из удаляемой категории на время переноса
     * @param deletedCategoryId идентификатор удаляемой категории
     * @param targetCategoryId целевая категория для переноса статей
     */
    @Async
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void deleteCategory(long deletedCategoryId, long targetCategoryId) {
        Collection<String> articleIds = advertisementRepository.findAllIdsByCategoryId(deletedCategoryId);
        Category targetCategory = categoryRepository.findById(targetCategoryId)
                .orElseThrow(() -> new EntityNotFoundException(targetCategoryId, Category.class));
        articleIds
                .forEach(advertisementId -> {
                    Advertisement advertisement = advertisementRepository.getOne(advertisementId);
                    advertisement.setCategory(targetCategory);
                    advertisementRepository.save(advertisement);
                });
        categoryRepository.deleteById(deletedCategoryId);
    }
}
