package org.ht.risk.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
* @ClassName: LogServiceApplication
* @Description: 
* @author dyb
* @date 2018年1月4日 下午3:18:36
* 
*/
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@MapperScan("org.ht.risk.log.mapper")
public class LogServiceApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(LogServiceApplication.class, args);
    	System.err.println("ヾ(◍°∇°◍)ﾉﾞ    LogService启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
    }
}
