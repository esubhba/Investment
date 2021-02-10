package za1975.equity.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class AppConfiguration {

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();  
		localeResolver.setDefaultLocale(Locale.forLanguageTag("hi"));  
		return localeResolver;  
	}
	@Bean
	public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter()
	{
		FilterRegistrationBean<ForwardedHeaderFilter> filterRegistrationBean=new FilterRegistrationBean<ForwardedHeaderFilter>();
		filterRegistrationBean.setFilter(new ForwardedHeaderFilter());
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		resourceBundleMessageSource.addBasenames("classpath:locale/messages");
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return resourceBundleMessageSource;
	}

	@Bean
	public OpenAPI openApi(@Value("${application.title}") String title,
			@Value("${application.description}") String description, @Value("${application.version}") Double version) {
		return new OpenAPI().info(new Info().description(description).title(title)
				.license(new License().name("Subhadeep Baladhikari")).version(version.toString()));
	}
	
}
