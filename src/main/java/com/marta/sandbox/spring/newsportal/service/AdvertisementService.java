package com.marta.sandbox.spring.newsportal.service;

import com.marta.sandbox.spring.newsportal.exception.EntityNotFoundException;
import com.marta.sandbox.spring.newsportal.model.Advertisement;
import com.marta.sandbox.spring.newsportal.model.Category;
import com.marta.sandbox.spring.newsportal.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("articleService")
@PropertySource("classpath:interface.properties")
public class AdvertisementService {

    @Value("${feed.page.size}")
    private int pageSize;

    private AdvertisementRepository advertisementRepository;

    public AdvertisementService(@Qualifier("advertisementRepository") AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    /**
     * Метод сохранения объявления
     * @param advertisement объект объявления для сохранения
     * @return сохраненное объявление
     */
    public Advertisement addAdvertisement (Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    /**
     * Метод удаления объявления по идентификатору
     * @param advertisementId идентификатор удаляемой категории
     */
    public void deleteAdvertisement (String advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    /**
     * Метод редактирования заголовка для объявления, найденного по идентификатору.
     * В случае, если объявление не найдено, выбрасывается исключение @{@link EntityNotFoundException}
     * @param advertisementId идентификатор редактируемой категории
     */
    public void updateTitle (String advertisementId, String newTitle) {
        Advertisement advertisement = getAdvertisement(advertisementId);
        advertisement.setTitle(newTitle);
        advertisementRepository.save(advertisement);
    }

    /**
     * Метод получения объявления по id. В случае, если объявление не
     * найдено, выбрасывается исключение @{@link EntityNotFoundException}
     * @param advertisementId идентификатор категории
     * @return выбранная категория
     */
    public Advertisement getAdvertisement (String advertisementId) {
        return advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new EntityNotFoundException(advertisementId, Category.class));
    }

    /**
     * Метод получения списка всех объявлений в системе
     * @return итератор объявлений
     */
    public Iterable<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }


    /**
     * Метод получения всех объявлений в данной категории.
     * @param categoryId идентификатор категории, для которой выбираются статьи
     * @return результирующая страница статей
     */
    public Iterable<Advertisement> getAllAdvertisementsByCategoryId(long categoryId) {
        return advertisementRepository.findAllByCategoryId (categoryId);
    }

    /**
     * Метод получения страницы объявлений для общей ленты.
     * @param page номер страницы (начинается с 1)
     * @return страница статей
     */
    public Page<Advertisement> getFeedPage(int page) {
        return advertisementRepository.findPage(PageRequest.of(page - 1, pageSize));
    }

}
