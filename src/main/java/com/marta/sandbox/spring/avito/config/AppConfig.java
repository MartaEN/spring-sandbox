package com.marta.sandbox.spring.avito.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.marta.sandbox.spring.avito.repository")
@EnableTransactionManagement
@ComponentScan("com.marta.sandbox.spring.avito.service")
public class AppConfig {
	
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		//Создания источника данных
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//Задание параметров подключения к базе данных
		dataSource.setUrl("jdbc:postgresql://localhost:5432/avito");
		dataSource.setUsername("postgres");
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setPassword("Chip&Da1e");
		return dataSource;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManager(){
	    //Создание класса фабрики реализующей интерфейс FactoryBean<EntityManagerFactory>
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		//Задание источника подключения
		factory.setDataSource(getDataSource());
		//Задание адаптера для конкретной реализации JPA, 
		//указывает, какая именно библиотека будет использоваться в качестве постовщика постоянства
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	    //указание пакетов в котром будут находится классы-сущности
		factory.setPackagesToScan("com.marta.sandbox.spring.avito.domain");

		//создание свойств для настройки Hibernate
		Properties jpaProperties = new Properties();
		//Указание диалекта конкретной базы данных,необходимо для генерации запросов Hibernate к БД
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		//Указание максимальной глубины связи(будет рассмотрено в след. уроке)
		jpaProperties.put("hibernate.max_fetch_depth", 3);
		//Определение максимального количества строк, возвращаемых за один запрос из БД
		jpaProperties.put("hibernate.jdbc.fetch_size", 50);
		//Определение максимального количества запросов при использовании пакетных операций
		jpaProperties.put("hibernate.jdbc.batch_size", 10);
		//Включает логгирование 
		jpaProperties.put("hibernate.show_sql", true);
		//Включает редактирование структуры базы данных через hibernate
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		
		factory.setJpaProperties(jpaProperties);
		return factory;
	}
	
	@Bean(name="transactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		
		JpaTransactionManager tm= new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory);
		return tm;
		
	}

}
