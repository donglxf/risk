package com.ht.risk.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-05-25 12:44
 */
//@EnableDiscoveryClient  //激活eureka中的DiscoveryClient实现
@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
public class AdminServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServiceApplication.class).web(true).run(args);    }
}
