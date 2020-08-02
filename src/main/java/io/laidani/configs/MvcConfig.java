package io.laidani.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	/*
	 * TECH : Internationalization
	 *        1. two beans 'localeResolver' and 'localChangeInterceptor'
	 *        2. override addInterceptors()
	 *        3. add  messages_XX.properties files.
	 */

	@Bean
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localChangeInterceptor() {
		
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localChangeInterceptor());
	}
	
	
}
