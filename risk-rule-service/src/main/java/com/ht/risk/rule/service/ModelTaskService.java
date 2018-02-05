package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.ActProcRelease;
import com.ht.risk.rule.entity.ModelTask;

/**
 * <p>
 * 风控模型离线任务表 服务类
 * </p>
 *
 * @author liuzq
 * @since 2018-01-29
 */
public interface ModelTaskService extends BaseService<ModelTask> {

	Page<ActProcRelease> queryModelTask(Page<ActProcRelease> page, Wrapper<ActProcRelease> wrapper);

}
