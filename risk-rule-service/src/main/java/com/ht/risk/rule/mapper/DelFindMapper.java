package com.ht.risk.rule.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 规则动作定义信息 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface DelFindMapper {

    @Select("select count(*) from ${table} where ${col} = #{kid}")
    public Integer findCount(@Param(value = "table")String table ,@Param(value = "col") String col,@Param(value = "kid") Object kid);

}
