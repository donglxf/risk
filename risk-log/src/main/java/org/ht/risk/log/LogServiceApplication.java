package org.ht.risk.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
* @ClassName: LogServiceApplication
* @Description: 
* @author dyb
* @date 2018年1月4日 下午3:18:36
* 
*/
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class LogServiceApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(LogServiceApplication.class, args);
    	System.err.println("ヾ(◍°∇°◍)ﾉﾞ    LogService启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
    }
}
