package com.ht.risk;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.ht.risk.mapper")
public class DroolsServiceApplication extends SpringBootServletInitializer {

	static Logger logger = LoggerFactory.getLogger(DroolsServiceApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DroolsServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DroolsServiceApplication.class, args);
	}
}