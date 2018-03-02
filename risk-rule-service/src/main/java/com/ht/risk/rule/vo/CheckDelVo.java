package com.ht.risk.rule.vo;

import lombok.Data;

/**
 * 说明：
 *
 * @auther 张鹏
 * @create
 */
@Data
public class CheckDelVo {
    private String table;
    private String column;

    public CheckDelVo(){}
    public CheckDelVo(String table,String column){
        this.table = table;
        this.column = column;
    }
}
