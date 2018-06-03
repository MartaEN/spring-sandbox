package com.marta.sandbox.spring.newsportal;


import com.marta.sandbox.spring.newsportal.dao.AdDAO;
import com.marta.sandbox.spring.newsportal.dao.CategoryDAO;
import com.marta.sandbox.spring.newsportal.dao.CompanyDAO;
import com.marta.sandbox.spring.newsportal.entity.Ad;
import com.marta.sandbox.spring.newsportal.entity.Category;
import com.marta.sandbox.spring.newsportal.entity.Company;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class NewsPortalApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(com.marta.sandbox.spring.newsportal.config.AppConfig.class);

		final CompanyDAO companyDAO = context.getBean(CompanyDAO.class);
		Company company = new Company();
		company.setName("Рога и копыта");
		companyDAO.merge(company);

		final CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
		Category category1 = new Category();
		category1.setName("Заготбытснаб");
		categoryDAO.merge(category1);
		Category category2 = new Category();
		category2.setName("Кульпросвет");
		categoryDAO.merge(category2);

		final AdDAO adDAO = context.getBean(AdDAO.class);
		Ad ad1 = new Ad ();
		ad1.setTitle("Все в Новые Васюки!");
		ad1.setText("Спешите попасть - в ближайшее воскресенье состоится шахматный турнир мирового уровня ... ");
		ad1.setCompany(company);
		ad1.setCategory(category2);
		adDAO.merge(ad1);
		Ad ad2 = new Ad ();
		ad2.setTitle("Сдайте свои рога выгодно");
		ad2.setCompany(company);
		ad2.setCategory(category1);
		adDAO.merge(ad2);

		System.out.println(Arrays.deepToString(adDAO.getAdsList().toArray()));
	}
}
