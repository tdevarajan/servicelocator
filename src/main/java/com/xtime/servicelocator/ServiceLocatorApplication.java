package com.xtime.servicelocator;

import static springfox.documentation.builders.PathSelectors.regex;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.xtime.servicelocator")
@ImportResource("classpath*:/META-INF/spring/*.xml")
public class ServiceLocatorApplication extends SpringBootServletInitializer {

    @Autowired
    Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ServiceLocatorApplication.class, args); 
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServiceLocatorApplication.class);
	}
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Service Locator Swagger")
                .description("Service Locator Swagger")
                .version("1.0")
                .build();
    }
    

    
    @Bean
    public Docket newsApi() throws URISyntaxException {

       Docket d = new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(apiInfo());

        return d;
    }


}
