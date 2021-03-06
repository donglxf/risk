package com.ht.risk.service.impl;

import com.github.pagehelper.PageInfo;
import com.ht.risk.mapper.BaseRuleVariableInfoMapper;
import com.ht.risk.model.BaseRuleVariableInfo;
import com.ht.risk.service.RuleVariableService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 * CLASSPATH: com.sky.RuleVariableServiceImpl
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/20
 */
@Service
public class RuleVariableServiceImpl implements RuleVariableService {

    @Resource
    private BaseRuleVariableInfoMapper baseRuleVariableInfoMapper;
    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据变量类型或数值类型获取变量集合信息
     *
     * @param baseRuleVariableInfo 参数
     * @param pageInfo             分页参数
     */
    @Override
    public List<BaseRuleVariableInfo> findBaseRuleVariableInfoList(BaseRuleVariableInfo baseRuleVariableInfo, PageInfo pageInfo) throws Exception {
        return this.baseRuleVariableInfoMapper.findBaseRuleVariableInfoList(baseRuleVariableInfo);
    }
}
