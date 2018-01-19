package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.ht.risk.common.constant.StatusConstants;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActProcRelease;
import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.entity.VariableBind;
import com.ht.risk.rule.service.ActProcReleaseService;
import com.ht.risk.rule.service.ModelSenceService;
import com.ht.risk.rule.service.SceneVersionService;
import com.ht.risk.rule.service.VariableBindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hzm
 * @since 2018-01-17
 */
@RestController
@RequestMapping("/actProcRelease")
@Api(tags = "actProcReleaseController", description = "模型查询", hidden = true)
public class ActProcReleaseController {

    private static Logger logger = LoggerFactory.getLogger(ActProcReleaseController.class);

    @Autowired
    private ActProcReleaseService actProcReleaseService;

    @Autowired
    private ModelSenceService modelSenceService;

    @Autowired
    private SceneVersionService sceneVersionService;

    @Autowired
    private VariableBindService variableBindService;

    @GetMapping("list")
    @ApiOperation(value = "查询模型验证信息表")
    public Result<List<ActProcRelease>> list(Page<ActProcRelease> pagination, Integer page, Integer limit, ActProcRelease actProcRelease) {
        logger.info("查询模型验证信息表");
        pagination.setSize(limit);
        pagination.setCurrent(page);
        EntityWrapper<ActProcRelease> ew = new EntityWrapper<>();
        if ("" != actProcRelease.getModelName() && actProcRelease.getModelName() != null) {
            ew.eq("MODEL_NAME", actProcRelease.getModelName());
        }
        if ("" != actProcRelease.getIsValidate() && actProcRelease.getIsValidate() != null) {
            ew.eq("IS_VALIDATE", actProcRelease.getIsValidate());
        }
        if ("" != actProcRelease.getModelCategory() && actProcRelease.getModelCategory() != null) {
            ew.eq("MODEL_CATEGORY", actProcRelease.getModelCategory());
        }
        Page<ActProcRelease> pages = actProcReleaseService.selectPage(pagination, ew);
        // 0-待验证，1-验证通过，2-验证不通过；默认为0;
        List<ActProcRelease> list = pages.getRecords();
        for (ActProcRelease act : list) {
            switch (act.getIsValidate()) {
                case "1":
                    act.setIsValidateAlia(StatusConstants.ALREADY_VALIDATE);
                    break;
                case "2":
                    act.setIsValidateAlia(StatusConstants.NOT_ALLOW_VALIDATE);
                    break;
                default:
                    act.setIsValidateAlia(StatusConstants.NOT_YET_VALIDATE);
            }
        }
        Result<List<ActProcRelease>> result = Result.build(0, "查询成功", pages.getRecords(), pages.getTotal());
        return result;
    }


    @PostMapping(value = "scene/variable")
    @ApiOperation(value = "根据模型id查询策列表，评分卡，以及绑定变量")
    public ActProcRelease getVariablesByActProcRealeseId(String actProcRealeseId) {
        logger.info("---根据模型id查找变量---");
        logger.info("---模型id为 ：" + actProcRealeseId + "---");
        ActProcRelease result = new ActProcRelease();

        //0.根据id查找actProRea对象
        ActProcRelease actProcRelease = actProcReleaseService.selectById(actProcRealeseId);

        String modelProcdefId = actProcRelease.getModelProcdefId();
        //1.查询模型对应属性
        result.setModelName(actProcRelease.getModelName());//获取到模型名字
        result.setModelVersion(actProcRelease.getModelVersion());//获取到模型版本

        //获取策列表
        List<ModelSence> modelSenceList = modelSenceService.selectList(new EntityWrapper<ModelSence>().eq("MODEL_PROCDEF_ID", modelProcdefId));
        result.setVariableMap(modelSenceList);

        //查询策列列表对应的title
        for (ModelSence modelSence : modelSenceList) {
            SceneVersion sceneVersion = sceneVersionService.selectOne(new EntityWrapper<SceneVersion>().eq("version_id", modelSence.getSenceVersionId()));
            if (sceneVersion != null) {
                modelSence.setSceneName(sceneVersion.getTitle());
            } else {
                modelSence.setSceneName("无名的策列表或评分卡");
            }
        }
        //2.查询策列关联的变量

        for (ModelSence modelSence : modelSenceList) {
            List<VariableBind> variableBindList = variableBindService.selectList(new EntityWrapper<VariableBind>().eq("SENCE_VERSION_ID", modelSence.getSenceVersionId()));
            modelSence.setData(variableBindList);
        }


        return result;

    }
}

