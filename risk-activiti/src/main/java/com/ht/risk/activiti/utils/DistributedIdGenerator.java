package com.ht.risk.activiti.utils;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;

import java.util.UUID;

public class DistributedIdGenerator implements IdGenerator {

    @Override
    public String getNextId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
