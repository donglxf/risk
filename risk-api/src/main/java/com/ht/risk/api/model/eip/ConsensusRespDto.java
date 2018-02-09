package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConsensusRespDto {

    private List<String> keyList;
    private List<ConsensusResult> resList;

}

@Data
class ConsensusResult implements Serializable {

    private static final long serialVersionUID = 814630082461851264L;

    /**
     *搜索关键字
     */
    @ApiModelProperty(value = "搜索关键字")
    private String keyword;
    /**
     *权重，由结果所含关键字数量决定
     */
    @ApiModelProperty(value = "权重")
    private String weight;
    /**
     *搜索结果标题
     */
    @ApiModelProperty(value = "搜索结果标题")
    private String title;
    /**
     *搜索结果内容
     */
    @ApiModelProperty(value = "搜索结果内容")
    private String text;
    /**
     *搜索引擎名称
     */
    @ApiModelProperty(value = "搜索引擎名称")
    private String engineName;
    /**
     *搜索结果链接
     */
    @ApiModelProperty(value = "搜索结果链接")
    private String href;
    /**
     *快照链接
     */
    @ApiModelProperty(value = "快照链接")
    private String snapshot;
    /**
     *暂无意义
     */
    @ApiModelProperty(value = "暂无意义")
    private String rIndex;

}

