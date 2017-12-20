package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.Variable;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 * 规则引擎常用变量 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface VariableMapper extends SuperMapper<Variable> {

    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据变量类型或数值类型查询变量信息
     * @param baseRuleVariableInfo 参数
     */
    List<Variable> findBaseRuleVariableInfoList(Variable baseRuleVariableInfo);

}
