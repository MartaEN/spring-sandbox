package com.marta.sandbox.spring.newsportal;

import com.marta.sandbox.spring.newsportal.service.AdvertisementService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class NewsPortalApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(com.marta.sandbox.spring.newsportal.configuration.ApplicationConfiguration.class);

		AdvertisementService advertisementService = context.getBean("advertisementService", AdvertisementService.class);

	}
}
