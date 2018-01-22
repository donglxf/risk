package com.ht.risk.activiti;

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
@MapperScan("com.ht.risk.activiti.mapper")
@EnableTransactionManagement 
@ComponentScan(basePackages= {"com.ht.risk.activiti","com.ht.risk.common.exception"})
public class ActivitiConfigApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiConfigApplication.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    accountservice启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}

}
