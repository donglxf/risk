package com.ht.risk.eip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients()
@EnableDiscoveryClient
@SpringCloudApplication
@MapperScan("com.ht.risk.eip.mapper")
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.ht.risk.eip","com.ht.risk.common.exception"})
public class RiskEipApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskEipApplication.class, args);


	}
}
