package com.ht.risk.rule.quartz.init;

import com.ht.risk.rule.service.ModelTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.rule.entity.ModelTask;
import com.ht.risk.rule.quartz.job.ModelTaskJob;

import java.util.List;

import javax.annotation.Resource;

/**
 * 任务调度初始化类
 */
@Component
@Order(value = 2)
public class ModelTaskInitRunner implements CommandLineRunner {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ModelTaskInitRunner.class);

	private static Scheduler scheduler;

	@Autowired
	private ModelTaskService modelTaskService;

	@Override
	public void run(String... strings) throws Exception {
		LOGGER.info(">>>>>>>>>>>>>>>任务调度初始化类 <<<<<<<<<<<<<");
		// String group = "modelTask";
		// String trigerName = "modelTask-Task1";
		// String jobName = "modelTaskJob";

		String cron = "5/1 * * * * ? ";

		// 定义一个触发器，命名为modelTask-Task,分组为modelTask，使用corn时间表达式
		// CronTrigger trigger = newTrigger()
		// .withIdentity(trigerName, group)
		// .withSchedule(cronSchedule(cron))
		// .build();

		// 定义一个作业，并绑定到modelTaskJob上，命名为myJob，分组为modelTask，usingJobData为传递参数
		// JobDataMap map = new JobDataMap();
		// map.put("modelid", "传离线任务de modelid");
		// JobDetail jobDetail =
		// newJob(ModelTaskJob.class).setJobData(map).withIdentity(jobName,
		// group).build();
		// scheduler.scheduleJob(jobDetail, trigger);
		// 启动调度器，也可以在job和trigger设置好后启动
		// scheduler.start();
		startJobs();
		/*
		 * String jobName = "modelTaskJob"; String jobGroupName = "modelTaskJobGroup";
		 * String triggerName = "modelTasktrigger"; String triggerGroupName =
		 * "modelTasktriggerGroup"; String cron1 = "5/3 * * * * ? ";
		 * 
		 * JobDataMap map = new JobDataMap(); map.put("modelid", 111); addJob(jobName,
		 * jobGroupName, triggerName, triggerGroupName, ModelTaskJob.class, cron1,map);
		 * 
		 * String jobName2 = "modelTaskJob2"; String jobGroupName2 =
		 * "modelTaskJobGroup"; String triggerName2 = "modelTasktrigger2"; String
		 * triggerGroupName2 = "modelTasktriggerGroup"; String cron2 = "5/5 * * * * ? ";
		 * JobDataMap map2 = new JobDataMap(); map2.put("modelid", 222);
		 * addJob(jobName2, jobGroupName2, triggerName2, triggerGroupName2,
		 * ModelTaskJob.class, cron2,map2);
		 */
		//
		// 初始化离线模型调度任务
		initModelTask();

		LOGGER.info(">>>>>>>>>>>>>>>任务调度初始完成 ！<<<<<<<<<<<<<");
	}

	/**
	 * @Description: 初始化离线任务
	 * 
	 */
	public void initModelTask() {
		Wrapper<ModelTask> wrapper = new EntityWrapper<>();
		wrapper.andNew("task_status = 1");
		
		//查询已启动的任务，启动它
		List<ModelTask> modelTaskList = modelTaskService.selectList(wrapper);
		for (ModelTask modelTask : modelTaskList) {
            String jobName = "modelTaskJob"+modelTask.getModelProcdefId();//job前缀抽出到配置文件
            String jobGroupName = "modelTaskJobGroup";//抽出到配置文件
            String triggerName = "modelTasktrigger"+modelTask.getModelProcdefId();//trigger前缀抽出到配置文件
            String triggerGroupName = "modelTasktriggerGroup";//抽出到配置文件
            String cronText = modelTask.getCornText();
            JobDataMap map = new JobDataMap();
            map.put("modelid", modelTask.getModelProcdefId());
        	ModelTaskInitRunner.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ModelTaskJob.class, cronText, map);
		}
	}

	/**
	 * @Description: 添加一个定时任务 ，任務啓動時候調用
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class jobClass, String cron, JobDataMap map) {
		try {
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(jobClass).setJobData(map).withIdentity(jobName, jobGroupName)
					.build();

			// 触发器
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组
			triggerBuilder.withIdentity(triggerName, triggerGroupName);
			triggerBuilder.startNow();
			// 触发器时间设定
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();

			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			String cron) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}

			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				/** 方式一 ：调用 rescheduleJob 开始 */
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName, triggerGroupName);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 方式一 ：修改一个任务的触发时间
				scheduler.rescheduleJob(triggerKey, trigger);
				/** 方式一 ：调用 rescheduleJob 结束 */

				/** 方式二：先删除，然后在创建一个新的Job */
				// JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName,
				// jobGroupName));
				// Class<? extends Job> jobClass = jobDetail.getJobClass();
				// removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
				// addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
				/** 方式二 ：先删除，然后在创建一个新的Job */
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

			scheduler.pauseTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:启动所有定时任务
	 */
	public static void startJobs() {
		try {
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Resource
	public void setScheduler(Scheduler scheduler) {
		ModelTaskInitRunner.scheduler = scheduler;
	}

}
