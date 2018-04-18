package com.ht.risk.eip.logs;

import com.ht.risk.eip.RiskEipApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("commonLogService")
public class CommonLogService{

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertLog(LogEntity logEntity){
        mongoTemplate.insert(logEntity);
    }
}
