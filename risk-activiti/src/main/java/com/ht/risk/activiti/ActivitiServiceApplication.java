package com.ht.risk.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringCloudApplication
public class ActivitiServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiServiceApplication.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    accountservice启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}

}
