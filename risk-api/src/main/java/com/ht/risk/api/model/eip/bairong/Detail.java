package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

import java.io.Serializable;

@Data
class Detail implements Serializable {

    private static final long serialVersionUID = 1599086711518389048L;

    /**
     * 申请的间隔天数
     */
    private String inteday;
    /**
     * 连续申请的次数
     */
    private String consnum;
    /**
     * 连续申请的持续天数
     */
    private String csinteday;

}
