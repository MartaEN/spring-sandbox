package com.marta.sandbox.spring.newsportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.marta.sandbox.spring.newsportal.dao")
public class AppConfig {

        @Bean(name="dataSource")
        public DataSource getDataSource(){
            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl("jdbc:postgresql://localhost:5432/newsportal");
            dataSource.setUsername("postgres");
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setPassword("Chip&Da1e");
            return dataSource;
        }

        @Bean(name="entityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
            // Создание класса фабрики, реализующей интерфейс FactoryBean<EntityManagerFactory>
            final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            // Задание источника подключения
            factory.setDataSource(getDataSource());
            // Задание адаптера для конкретной реализации JPA
            // указывает, какая именно библиотека будет использоваться в качестве поставщика постоянства
            factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            // Указание пакетов, в которых будут находиться классы-сущности
            factory.setPackagesToScan("com.marta.sandbox.spring.newsportal.entity");
            factory.setPersistenceUnitName("persistenceUnit");
            // Создание свойств для настройки Hibernate
            final Properties properties = new Properties();
            // Указание диалекта конкретной базы данных – необходимо для генерации запросов Hibernate к БД
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
            // Указание максимальной глубины связи (будет рассмотрено в следующем уроке)
            properties.put("hibernate.max_fetch_depth", 3);
            // Определение максимального количества строк, возвращаемых за один запрос из БД
            properties.put("hibernate.jdbc.fetch_size", 50);
            // Определение максимального количества запросов при использовании пакетных операций
            properties.put("hibernate.jdbc.batch_size", 10);
            // Включает логирование
            properties.put("hibernate.show_sql", true);
            properties.put("hibernate.hbm2ddl.auto", "update");
            factory.setJpaProperties(properties);
            return factory;
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            final JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
            return transactionManager;
        }
}
