package com.ht.risk.activiti.vo;

import java.io.Serializable;
import java.util.Map;

public class Page implements Serializable{

    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}