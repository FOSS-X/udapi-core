package com.gupta.udapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * @author amitkumargupta
 */
@Configuration
@SpringBootApplication
public class UdapiApplication extends SpringBootServletInitializer {

	public UdapiApplication() {
		super();
		setRegisterErrorPageFilter(false);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UdapiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UdapiApplication.class, args);
	}
}
