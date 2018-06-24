package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

import java.io.Serializable;

@Data
class Content implements Serializable {

    private static final long serialVersionUID = -1769361476440855788L;

    private Id id;
    private Id cell;
}
