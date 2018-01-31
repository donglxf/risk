package com.ht.risk.activiti.model;

import lombok.Data;

import java.util.List;
/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/1/30 17:25
 */
@Data
public class Root {

    private String resourceId;

    private Properties properties;

    private Stencil stencil;

    private Bounds bounds;

    private Stencilset stencilset;


}
