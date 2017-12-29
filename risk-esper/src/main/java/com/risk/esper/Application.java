package com.risk.esper;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.risk.esper.event.PersonEvent;


@SpringCloudApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.err.println("ヾ(◍°∇°◍)ﾉﾞ    accountservice启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}
	
	
	
	@Bean
    public EPServiceProvider epServiceProvider() {
        com.espertech.esper.client.Configuration config = new com.espertech.esper.client.Configuration();
        //add event type
        config.addEventType(PersonEvent.class);
        EPServiceProvider epServiceProvider = EPServiceProviderManager.getDefaultProvider(config);
        // epServiceProvider.initialize();
        return epServiceProvider;
    }

    @Bean
    public EPAdministrator epAdministrator() {
        return epServiceProvider().getEPAdministrator();
    }

}
