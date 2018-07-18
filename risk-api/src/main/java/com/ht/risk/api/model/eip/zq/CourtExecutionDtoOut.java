package com.ht.risk.api.model.eip.zq;

import lombok.Data;

@Data
public class CourtExecutionDtoOut {
    private String del_flag;
    private String case_no;
    private String case_date;
    private String unperform_part;
    private String memo;
    private String court;
    private String gist_id;
    private String pub_date;
    private String province;
    private String gist_unit;
    private String obligation;
    private String case_id;
    private String create_date;
    private String performed_part;
    private String status;
}
