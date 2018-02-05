package com.ht.risk.rule.quartz.init;

import com.ht.risk.rule.quartz.job.ModelValidateJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobBuilder.newJob;

/**
 *  任务调度初始化类
 */
//@Component
@Order(value=2)
public class QuartzInitRunner implements CommandLineRunner {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelValidateJob.class);

    @Resource
    private Scheduler scheduler;

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info(">>>>>>>>>>>>>>>任务调度初始化类 <<<<<<<<<<<<<");
        /*String name = "validate-model";
        String group = "validate";
        String cron = "1 * * * * ?";
        TriggerKey triggerKey = TriggerKey.triggerKey("validate-model", "validate");
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        JobDetail jobDetail = newJob(ModelValidateJob.class).withIdentity("validate-model", "validate").build();
        trigger = newTrigger()
                .withIdentity(name, group)
                .startNow()
                .withSchedule(cronSchedule(cron))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();*/
        LOGGER.info(">>>>>>>>>>>>>>>任务调度初始完成 ！<<<<<<<<<<<<<");
    }
}
