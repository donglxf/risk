package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

import java.io.Serializable;

@Data
class Cdata implements Serializable {

    private static final long serialVersionUID = -15736017638431827L;

    private Detail bank;
    private Detail nbank;
}
