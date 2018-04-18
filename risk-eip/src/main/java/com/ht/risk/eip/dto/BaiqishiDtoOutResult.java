package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.BaiqishiDtoOut;
import com.ht.risk.common.result.Result;

import java.io.Serializable;

public class BaiqishiDtoOutResult extends BaseResult implements Serializable {

    private static final long serialVersionUID = 2491976399667498206L;

    public BaiqishiDtoOutResult(){
        super();
    }

    public BaiqishiDtoOutResult(Result<BaiqishiDtoOut> result){
        super();
        this.data = result.getData();
    }

    private BaiqishiDtoOut data;

    private String identityCard;

    public BaiqishiDtoOut getData() {
        return data;
    }

    public void setData(BaiqishiDtoOut data) {
        this.data = data;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}
