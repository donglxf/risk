package org.ht.risk.log.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import org.apache.commons.lang3.StringUtils;
import org.ht.risk.log.entity.DroolsLog;
import org.ht.risk.log.entity.HitRule;
import org.ht.risk.log.mapper.DroolsLogMapper;
import org.ht.risk.log.service.DroolsLogService;
import org.springframework.stereotype.Service;

import com.ht.risk.common.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        for(Iterator<HitRule> iterator = hitrules.iterator();iterator.hasNext();){
            rpcHitRuleInfos.add(convertHitRule(iterator.next()));
        }
        return rpcHitRuleInfos;
    }

    @Override
    public List<RpcHitRuleInfo> countHitRuleInfo(List<String> procInstIds) {
        List<HitRule> hitrules = droolsLogMapper.queryHitRuleByProcInstIds(procInstIds);
        List<RpcHitRuleInfo> rpcHitRuleInfos = new ArrayList<RpcHitRuleInfo>();
        for(Iterator<HitRule> iterator = hitrules.iterator();iterator.hasNext();){
            rpcHitRuleInfos.add(convertHitRule(iterator.next()));
        }
        return rpcHitRuleInfos;
    }

    public RpcHitRuleInfo convertHitRule(HitRule hitRule){
        RpcHitRuleInfo rpcHitRuleInfo = new RpcHitRuleInfo();
        rpcHitRuleInfo.setSenceVersionId(hitRule.getSenceVersionId());
        rpcHitRuleInfo.setRuleDesc(hitRule.getRuleDesc());
        rpcHitRuleInfo.setRuleName(hitRule.getRuleName());
        rpcHitRuleInfo.setSenceName(hitRule.getSenceName());
        rpcHitRuleInfo.setCount(StringUtils.isEmpty(hitRule.getFlag())?0:1);
        return rpcHitRuleInfo;
    }
}
