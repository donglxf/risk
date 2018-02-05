package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.VariableBind;
import com.ht.risk.common.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
public interface VariableBindMapper extends SuperMapper<VariableBind> {

    Integer myUpdate(
            @Param("senceVersionId")String senceVersionId,
            @Param("variableCode")String variableCode,
            @Param("tmpValue")String tmpValue,
            @Param("bindTable")String bindTable,
            @Param("bindColumn")String bindColumn

    );

     List<VariableBind> querySenceBindTableInfo(Map<String,String> paramter);

     List<Map> getRandAutoData(Map<String,String> paramter);

}
