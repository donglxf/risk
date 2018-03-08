package com.ht.risk;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients(basePackages = {"com.ht.ussp.client","com.ht.risk.rpc"})
@EnableTransactionManagement
@EnableDiscoveryClient 
@SpringBootApplication
@MapperScan("com.ht.risk.mapper")
@ComponentScan(basePackages= {"com.ht.risk","com.ht.risk.common.exception","com.ht.ussp.bean"})
public class DroolsServiceApplication extends SpringBootServletInitializer {

	static Logger logger = LoggerFactory.getLogger(DroolsServiceApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DroolsServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DroolsServiceApplication.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    DroolsServiceApplication启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}
}
