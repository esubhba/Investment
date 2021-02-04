package za1975.equity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHypermediaSupport(type = {HypermediaType.HAL_FORMS,HypermediaType.HAL})
@EnableTransactionManagement
public class EquityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquityApplication.class, args);
	}

}
