package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.Variable;
import com.ht.risk.rule.mapper.VariableMapper;
import com.ht.risk.rule.service.VariableService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规则引擎常用变量 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class VariableServiceImpl extends BaseServiceImpl<VariableMapper, Variable> implements VariableService {

}
