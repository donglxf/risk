package com.tdw.risk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableFeignClients
@SpringCloudApplication
@MapperScan("com.tdw.risk.mapper")
@EnableTransactionManagement 
@ComponentScan(basePackages= {"com.tdw.risk"})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    accountservice启动成功      ヾ(◍fefef°∇°◍)ﾉﾞ\n");
	}

}
