package com.ht.risk.rule.vo;

import java.util.List;

/**
 * 说明：
 *
 * @auther 张鹏
 * @create
 */
public class EntitySelectVo {


    private String value;
    private String text;

    private List<EntitySelectVo> sons;

    public List<EntitySelectVo> getSons() {
        return sons;
    }

    public void setSons(List<EntitySelectVo> sons) {
        this.sons = sons;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
