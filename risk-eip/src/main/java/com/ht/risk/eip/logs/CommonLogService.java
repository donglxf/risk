package com.ht.risk.eip.logs;

import com.ht.risk.eip.RiskEipApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("commonLogService")
public class CommonLogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertLog(LogEntity logEntity) {
        mongoTemplate.insert(logEntity);
    }

    public <T> List<T> find(Query query, Class<T> t) {
        return mongoTemplate.find(query, t);
    }
}
