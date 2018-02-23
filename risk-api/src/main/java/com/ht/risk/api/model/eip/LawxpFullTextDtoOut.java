package com.ht.risk.api.model.eip;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 汇法网全文检索
 *
 * @author:喻尊龙
 * @date: 2018/2/7
 */
@Data
public class LawxpFullTextDtoOut implements Serializable {

    private static final long serialVersionUID = -2307467846618730377L;

    private String tianChengStatus;
    private String success;
    private String message;
    private String seconds;
    private String fxmsgnum;
    private String fxpgnum;
    private FxContentDetail fxcontent;
    private FxPgReturnDetail fxpgturn;
    private Fxnavigate fxnavigate;


}

@Data
class Fxnavigate implements Serializable {

    private static final long serialVersionUID = 12302586457231389L;

    private FullDtail caipan;
    private FullDtail zhixing;
    private FullDtail shixin;
    private FullDtail kaiting;
    private FullDtail songda;
    private FullDtail shenpan;
    private FullDtail pochan;
    private FullDtail shuiwu;
    private FullDtail shichang;
    private FullDtail huanbao;
    private FullDtail haiguan;
    private FullDtail qita;
    private FullDtail wangdai;
    private FullDtail lvshi;
    private FullDtail baidu;

}

@Data
class FullDtail implements Serializable {

    private static final long serialVersionUID = 160937544030665293L;

    private int num;
    private String url;
    private String para;

}

@Data
class PgDetail implements Serializable {

    private static final long serialVersionUID = 8396567356127672904L;

    private String pg;
    private String url;
    private String para;

}

@Data
class EveryInfo implements Serializable {

    private static final long serialVersionUID = 191143281740991662L;
    private String keyid;
    private String typet;
    private String typetname;
    private String title;
    private String description1;
    private String description2;
    private String url;

}

@Data
class FxContentDetail implements Serializable {

    private static final long serialVersionUID = 6826888230474588281L;

    private List<EveryInfo> caipan;
    private List<EveryInfo> zhixing;
    private List<EveryInfo> shixin;
    private List<EveryInfo> kaiting;
    private List<EveryInfo> songda;
    private List<EveryInfo> shenpan;
    private List<EveryInfo> pochan;
    private List<EveryInfo> shuiwu;
    private List<EveryInfo> shichang;
    private List<EveryInfo> huanbao;
    private List<EveryInfo> haiguan;
    private List<EveryInfo> qita;
    private List<EveryInfo> wangdai;
    private List<EveryInfo> lvshi;
    private List<EveryInfo> baidu;
}

@Data
class FxPgReturnDetail implements Serializable {

    private static final long serialVersionUID = 8101813994910075747L;

    private PgDetail prepage;
    private PgDetail nexpage;
}

