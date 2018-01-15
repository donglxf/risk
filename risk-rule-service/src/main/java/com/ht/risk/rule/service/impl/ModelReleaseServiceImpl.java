package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ModelRelease;
import com.ht.risk.rule.entity.ValidateBatch;
import com.ht.risk.rule.mapper.ModelReleaseMapper;
import com.ht.risk.rule.mapper.ValidateBatchMapper;
import com.ht.risk.rule.service.ModelReleaseService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.vo.ModelVerficationDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@Service
public class ModelReleaseServiceImpl extends BaseServiceImpl<ModelReleaseMapper, ModelRelease> implements ModelReleaseService {

    @Resource
    private ModelReleaseMapper modelReleaseMapper;

    private ValidateBatchMapper validateBatchMapper;


    @Override
    public List<ModelRelease> queryWaitVerficationModelList(){
        return null;
    };

    @Override
    public Page<ModelRelease> queryWaitVerficationModelForPage(Page<ModelRelease> page,ModelRelease modelRelease) {
        EntityWrapper<ModelRelease> entityWrapper = new EntityWrapper<ModelRelease>();
        modelRelease.setIsValidate("0");
        entityWrapper.setEntity(modelRelease);
        return page.setRecords(modelReleaseMapper.selectPage(page,entityWrapper));
    }

    @Override
    public Page<ModelRelease> queryWaitReleaseModelForPage(Page<ModelRelease> page, ModelRelease modelRelease) {
        EntityWrapper<ModelRelease> entityWrapper = new EntityWrapper<ModelRelease>();
        modelRelease.setIsValidate("1");
        entityWrapper.setEntity(modelRelease);
        return page.setRecords(modelReleaseMapper.selectPage(page,entityWrapper));
    }

    @Override
    public Page<ModelRelease> queryApprovalPassModelForPage(Page<ModelRelease> page, ModelRelease modelRelease) {
        EntityWrapper<ModelRelease> entityWrapper = new EntityWrapper<ModelRelease>();
        modelRelease.setIsApprove("1");
        entityWrapper.setEntity(modelRelease);
        return page.setRecords(modelReleaseMapper.selectPage(page,entityWrapper));
    }

    @Override
    public boolean createVerficationModelBatch(String id) {
        ModelRelease modelRelease = modelReleaseMapper.selectById(id);
        if(modelRelease == null){
            return false;
        }
        ValidateBatch batch = new ValidateBatch();
        batch.setBatchSize(10);
        batch.setCreateTime(new Date(System.currentTimeMillis()));
        batch.setDeploymenTid(modelRelease.getModelProcdefId());
        //TODO 用户信息获取
        batch.setCreateUser("Robot");
        validateBatchMapper.insert(batch);
        return true;
    }

    @Override
    public ModelVerficationDetailVo queryModelVariable(String id) {
        ModelVerficationDetailVo modelVerficationDetailVo = new ModelVerficationDetailVo();

        return null;
    }
}
