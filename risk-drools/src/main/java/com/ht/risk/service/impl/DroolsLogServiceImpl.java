package com.ht.risk.service.impl;

import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.mapper.DroolsLogMapper;
import com.ht.risk.mapper.TestDroolsLogMapper;
import com.ht.risk.model.DroolsLog;
import com.ht.risk.model.HitRule;
import com.ht.risk.model.TestDroolsLog;
import com.ht.risk.service.DroolsLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-10
 */
@Service
public class DroolsLogServiceImpl extends BaseServiceImpl<DroolsLogMapper, DroolsLog> implements DroolsLogService {

    @Resource
    private DroolsLogMapper droolsLogMapper;

    @Override
    public List<RpcHitRuleInfo> queryHitRuleInfoByProcInstId(String procInstId) {
        List<HitRule> hitrules = droolsLogMapper.queryHitRuleByProcInstId(procInstId);
        List<RpcHitRuleInfo> rpcHitRuleInfos = new ArrayList<RpcHitRuleInfo>();
        for(Iterator<HitRule> iterator = hitrules.iterator(); iterator.hasNext();){
            rpcHitRuleInfos.add(convertHitRule(iterator.next()));
        }
        return rpcHitRuleInfos;
    }

    @Override
    public List<RpcHitRuleInfo> countHitRuleInfo(List<String> procInstIds) {
        Map<String,List<String>> paramter = new HashMap<String,List<String>>();
        paramter.put("procInstIds",procInstIds);
        List<HitRule> hitrules = droolsLogMapper.queryHitRuleByProcInstIds(paramter);
        List<RpcHitRuleInfo> rpcHitRuleInfos = new ArrayList<RpcHitRuleInfo>();
        for(Iterator<HitRule> iterator = hitrules.iterator();iterator.hasNext();){
            rpcHitRuleInfos.add(convertHitRule(iterator.next()));
        }
        return rpcHitRuleInfos;
    }

    @Override
    public List<RpcDroolsLog> queryModelDroolsLogs(String procInstId) {
        Map<String,Object> paramter = new HashMap<String,Object>();
        paramter.put("PROCINST_ID",procInstId);
        List<DroolsLog> logs = droolsLogMapper.selectByMap(paramter);
        List<RpcDroolsLog> rpcDroolsLogs = new ArrayList<RpcDroolsLog>();
        for(Iterator<DroolsLog> iterator = logs.iterator();iterator.hasNext();){
            rpcDroolsLogs.add(convertDroolsLog(iterator.next()));
        }
        return rpcDroolsLogs;
    }

    private RpcHitRuleInfo convertHitRule(HitRule hitRule){
        RpcHitRuleInfo rpcHitRuleInfo = new RpcHitRuleInfo();
        rpcHitRuleInfo.setSenceVersionId(hitRule.getSenceVersionId());
        rpcHitRuleInfo.setRuleDesc(hitRule.getRuleDesc());
        rpcHitRuleInfo.setRuleName(hitRule.getRuleName());
        rpcHitRuleInfo.setSenceName(hitRule.getSenceName());
        rpcHitRuleInfo.setCount(StringUtils.isEmpty(hitRule.getFlag())?0:1);
        return rpcHitRuleInfo;
    }

    private RpcDroolsLog convertDroolsLog(DroolsLog droolsLog){
        RpcDroolsLog rpcDroolsLog = new RpcDroolsLog();
        rpcDroolsLog.setId(droolsLog.getId());
        rpcDroolsLog.setInParamter(droolsLog.getInParamter());
        rpcDroolsLog.setModelName(droolsLog.getModelName());
        rpcDroolsLog.setOutParamter(droolsLog.getOutParamter());
        rpcDroolsLog.setProcinstId(String.valueOf(droolsLog.getProcinstId()));
        rpcDroolsLog.setSenceVersionid(droolsLog.getSenceVersionid());
        rpcDroolsLog.setType(droolsLog.getType());
        rpcDroolsLog.setCreateTime(droolsLog.getCreateTime());
        return rpcDroolsLog;
    }
}
