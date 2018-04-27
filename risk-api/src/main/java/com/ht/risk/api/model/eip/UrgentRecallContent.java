package com.ht.risk.api.model.eip;

import lombok.Data;

import java.io.Serializable;

@Data
public class UrgentRecallContent implements Serializable {
    private static final long serialVersionUID = 6260356377073142231L;

    private UrgentRecallDetail dunning;
    private UrgentRecallDetail notSureDunning;
}
