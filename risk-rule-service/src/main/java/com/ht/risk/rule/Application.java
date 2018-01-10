package com.ht.risk.rule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableFeignClients
@EnableDiscoveryClient
@SpringCloudApplication
@MapperScan("com.ht.risk.rule.mapper")
@EnableTransactionManagement 
@ComponentScan(basePackages= {"com.ht.risk.rule","com.ht.risk.common.exception"})
//////@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    accountservice启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}

}
