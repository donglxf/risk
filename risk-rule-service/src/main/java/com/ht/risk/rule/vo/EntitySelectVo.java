package com.ht.risk.rule.vo;

import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @auther 张鹏
 * @create
 */
@Data
public class EntitySelectVo {


    private String value;
    private String text;
    private String key;
    private List<EntitySelectVo> sons;

}
