package com.ht.risk.rule.service.impl;

import org.springframework.stereotype.Service;

import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.TempDataContains;
import com.ht.risk.rule.mapper.TempDataContainsMapper;
import com.ht.risk.rule.service.TempDataContainsService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyb
 * @since 2018-01-15
 */
@Service
public class TempDataContainsServiceImpl extends BaseServiceImpl<TempDataContainsMapper, TempDataContains> implements TempDataContainsService {

    @Resource
    private TempDataContainsMapper tempDataContainsMapper;

    @Override
    public List<Map<String,Object>> getAutoValidaionData(String sql) {
        Map<String,String> paramter = new HashMap<String,String>();
        paramter.put("sql",sql);
        return tempDataContainsMapper.getAutoValidaionData(paramter);
    }
}
