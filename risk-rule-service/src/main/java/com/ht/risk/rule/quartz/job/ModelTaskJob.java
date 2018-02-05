package com.ht.risk.rule.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ModelTaskJob implements Job {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelTaskJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("ModelTaskJob start!");
        System.out.println("----------------------执行离线任务-----------------------------");
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(dataMap.get("modelid")+":根据modelid执行model的详细类容");
        System.out.println(jobExecutionContext.getJobRunTime());
        LOGGER.info("ModelTaskJob end!");
    }
}
