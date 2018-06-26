package com.marta.sandbox.spring.avito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.marta.sandbox.spring.avito.web")
@Import(AppConfig.class)
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver setupViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean ("messageSource")
	public ReloadableResourceBundleMessageSource messageSource (){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("WEB-INF/i18n/messages", "WEB-INF/i18n/application");
		return messageSource;
	}

	@Bean ("localeChangeInterceptor")
	public LocaleChangeInterceptor localeChangeInterceptor (){
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry ) {
		registry.addInterceptor(localeChangeInterceptor ());
	}

	@Bean ("localeResolver")
	public CookieLocaleResolver localeResolver (){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName ("locale");
		return localeResolver;
	}

}
