package com.ht.risk.rule.service.impl;

import java.util.List;

import com.ht.risk.rule.mapper.ModelTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.ActProcRelease;
import com.ht.risk.rule.entity.ModelTask;
import com.ht.risk.rule.mapper.ActProcReleaseMapper;
import com.ht.risk.rule.service.ModelTaskService;

/**
 * <p>
 * 风控模型离线任务表 服务实现类
 * </p>
 *
 * @author liuzq
 * @since 2018-01-29
 */



@Service
public class ModelTaskServiceImpl extends BaseServiceImpl<ModelTaskMapper, ModelTask> implements ModelTaskService {

    @Autowired
    private ActProcReleaseMapper actProcReleaseMapper;
	
    @Override
    public Page<ActProcRelease> queryModelTask(Page<ActProcRelease> page, Wrapper<ActProcRelease> wrapper){
//    	Page<ActProcRelease> page = new Page<ActProcRelease>();
//    	page.setCurrent(1);
//    	page.setSize(10);
    	List<ActProcRelease> list = actProcReleaseMapper.findModelTaskList(page, wrapper);
    	page.setRecords(list);
    	return page;
    }
}
