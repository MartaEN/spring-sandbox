package com.marta.sandbox.spring.newsportal.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.marta.sandbox.spring.newsportal")
@EnableJpaRepositories("com.marta.sandbox.spring.newsportal.repository")
@Import(DataSourceConfiguration.class)
public class ApplicationConfiguration {

}
