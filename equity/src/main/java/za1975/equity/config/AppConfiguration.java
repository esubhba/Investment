package za1975.equity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class AppConfiguration {

	@Bean
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource resourceBundleMessageSource=new ReloadableResourceBundleMessageSource();
		resourceBundleMessageSource.addBasenames("classpath:locale/messages");
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return resourceBundleMessageSource;
	}
	
	@Bean
	public OpenAPI openApi(@Value("${application.title}")String title,@Value("${application.description}")String description,@Value("${application.version}")Double version)
	{
		return new OpenAPI()
				.info(new Info()
						.description(description)
						.title(title)
						.license(new License().name("Subhadeep Baladhikari"))
						.version(version.toString())
					);
	}
}
