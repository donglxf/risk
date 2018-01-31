package com.ht.risk.activiti.model;

import lombok.Data;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/1/30 17:14
 */
@Data
public class Properties {
    private String process_id;

    private String name;

    private String documentation;

    private String process_author;

    private String process_version;

    private String process_namespace;

    private String executionlisteners;

    private String eventlisteners;

    private String signaldefinitions;

    private String messagedefinitions;
}
