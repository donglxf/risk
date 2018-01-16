package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ModelRelease;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.vo.ModelVerficationVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
public interface ModelReleaseService extends BaseService<ModelRelease> {

    public List<ModelRelease> queryWaitVerficationModelList();

    /**
     * 查询待验证的模型版本分页信息
     * @param page
     * @param modelRelease
     * @return
     */
    public Page<ModelRelease> queryWaitVerficationModelForPage(Page<ModelRelease> page,ModelRelease modelRelease);


    /**
     * 查询待发布的模型版本分页信息
     * @param page
     * @param modelRelease
     * @return
     */
    public Page<ModelRelease> queryWaitReleaseModelForPage(Page<ModelRelease> page,ModelRelease modelRelease);

    /**
     * 查询审核通过的模型版本分页信息
     * @param page
     * @param modelRelease
     * @return
     */
    public Page<ModelRelease> queryApprovalPassModelForPage(Page<ModelRelease> page,ModelRelease modelRelease);


    /**
     * 创建模型验证批次
     * @param id
     * @return
     */
    public boolean createVerficationModelBatch(String id);


    public ModelVerficationVo queryModelVariable(String id);



}
