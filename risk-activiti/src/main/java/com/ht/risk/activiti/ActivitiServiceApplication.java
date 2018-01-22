package com.ht.risk.activiti;

import com.ht.risk.activiti.service.SendService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.UUID;


@EnableFeignClients
@EnableDiscoveryClient
@SpringCloudApplication
@EnableTransactionManagement
@MapperScan("com.ht.risk.activiti.mapper")
@ComponentScan(basePackages= {"com.ht.risk.activiti","com.ht.risk.common.exception"})
public class ActivitiServiceApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiServiceApplication.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    accountservice启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}


}
