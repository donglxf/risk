package com.ht.risk.activiti.config;

import com.ht.risk.activiti.utils.DistributedIdGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class MyProcessEngineCfgConfigurer implements ProcessEngineConfigurationConfigurer {

    /**
     * 设置主键生策略 UUID
     * @param springProcessEngineConfiguration
     */

    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        DistributedIdGenerator distributedIdGenerator = new DistributedIdGenerator();
        springProcessEngineConfiguration.setIdGenerator(distributedIdGenerator);
    }
}
