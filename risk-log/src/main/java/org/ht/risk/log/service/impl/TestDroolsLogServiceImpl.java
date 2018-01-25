package org.ht.risk.log.service.impl;

import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.ht.risk.log.entity.HitRule;
import org.ht.risk.log.entity.TestDroolsLog;
import org.ht.risk.log.mapper.TestDroolsLogMapper;
import org.ht.risk.log.service.TestDroolsLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
@Service
public class TestDroolsLogServiceImpl extends BaseServiceImpl<TestDroolsLogMapper, TestDroolsLog> implements TestDroolsLogService {

    @Resource
    private TestDroolsLogMapper testDroolsLogMapper;

    @Override
    public List<RpcHitRuleInfo> queryHitRuleInfoByProcInstId(String procInstId) {
        List<HitRule> hitrules = testDroolsLogMapper.queryHitRuleByProcInstId(procInstId);
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
        List<HitRule> hitrules = testDroolsLogMapper.queryHitRuleByProcInstIds(paramter);
        List<RpcHitRuleInfo> rpcHitRuleInfos = new ArrayList<RpcHitRuleInfo>();
        for(Iterator<HitRule> iterator = hitrules.iterator();iterator.hasNext();){
            rpcHitRuleInfos.add(convertHitRule(iterator.next()));
        }
        return rpcHitRuleInfos;
    }

    @Override
    public List<RpcDroolsLog> queryTestModelDroolsLogs(String procInstId) {
        Map<String,Object> paramter = new HashMap<String,Object>();
        paramter.put("PROCINST_ID",procInstId);
        List<TestDroolsLog> logs = testDroolsLogMapper.selectByMap(paramter);
        List<RpcDroolsLog> rpcDroolsLogs = new ArrayList<RpcDroolsLog>();
        for(Iterator<TestDroolsLog> iterator = logs.iterator();iterator.hasNext();){
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

    private RpcDroolsLog convertDroolsLog(TestDroolsLog droolsLog){
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
