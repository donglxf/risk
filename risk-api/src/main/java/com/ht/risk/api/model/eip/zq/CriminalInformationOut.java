package com.ht.risk.api.model.eip.zq;

import lombok.Data;

@Data
public class CriminalInformationOut {
    private boolean success;
    private CriminalDtoOut data;
}
