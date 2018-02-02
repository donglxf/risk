package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.constant.StatusConstants;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.*;
import com.ht.risk.rule.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private ConstantInfoService constantInfoService;

    @Autowired
    private RedisTemplate<String, String> redis;

    @GetMapping("list")
    @ApiOperation(value = "查询模型验证信息表")
    public Result<List<ActProcRelease>> list(Page<ActProcRelease> pagination, Integer page, Integer limit, ActProcRelease actProcRelease) {
        logger.info("查询模型验证信息表");
        pagination.setSize(limit);
        pagination.setCurrent(page);
        EntityWrapper<ActProcRelease> ew = new EntityWrapper<>();
        if ("" != actProcRelease.getModelName() && actProcRelease.getModelName() != null) {
            //模糊查询
            ew.like("MODEL_NAME", "%" + actProcRelease.getModelName() + "%");
        }
        if ("" != actProcRelease.getIsValidate() && actProcRelease.getIsValidate() != null) {
            ew.eq("IS_VALIDATE", actProcRelease.getIsValidate());
        }
        if ("" != actProcRelease.getModelCategory() && actProcRelease.getModelCategory() != null) {
            ew.eq("MODEL_CATEGORY", actProcRelease.getModelCategory());
        }
        if ("" != actProcRelease.getIsEffect() && actProcRelease.getIsEffect() != null) {
            ew.eq("IS_EFFECT", actProcRelease.getIsEffect());
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
            switch (act.getIsApprove()) {
                case "1":
                    act.setIsApprovedAlia(StatusConstants.ALREADY_APPROVED);
                    break;
                case "2":
                    act.setIsApprovedAlia(StatusConstants.NOT_ALLOW_ARRPOVED);
                    break;
                default:
                    act.setIsApprovedAlia(StatusConstants.NOT_YET_APPROVE);
            }
            //测试版还是正式版
            switch (act.getVersionType()) {
                case "0":
                    act.setVersionTypeAlia(StatusConstants.BETA_VERSION);
                    break;
                case "1":
                    act.setVersionTypeAlia(StatusConstants.RELEASE_VERSION);
                    break;
            }
            //有效状态
            if ("1".equals(act.getVersionType())) {
                switch (act.getIsEffect()) {
                    case "0":
                        act.setIsEffectAlia(StatusConstants.EFFECT);
                        break;
                    case "1":
                        act.setIsEffectAlia(StatusConstants.NOT_EFFECT);
                        break;
                    default:
                        act.setIsEffectAlia(StatusConstants.NOT_EFFECT);
                }
            } else {
                act.setIsEffectAlia(StatusConstants.NOT_YET_PUBLISH);
            }
        }
        Result<List<ActProcRelease>> result = Result.build(0, "查询成功", pages.getRecords(), pages.getTotal());
        return result;
    }

    @GetMapping(value = "scene/variable/manual")
    @ApiOperation(value = "根据模型id查询策列表，评分卡，以及绑定变量")
    public ActProcRelease getVariablesByActProcRealeseId(String actProcRealeseId, HttpServletRequest request) {
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
                modelSence.setSceneName("未命名的策列表或评分卡");
            }
        }
        //2.查询策列关联的变量
        for (ModelSence modelSence : modelSenceList) {
            List<VariableBind> variableBindList = variableBindService.selectList(new EntityWrapper<VariableBind>().eq("SENCE_VERSION_ID", modelSence.getSenceVersionId()));
            for (VariableBind variableBind : variableBindList) {
                //如果为Constant类型的则查询
                ArrayList<Map<String, String>> list = new ArrayList<>();
                String variableCode = variableBind.getVariableCode();
                if ("CONSTANT".equals(variableBind.getDataType())) {
                    //查询单个变量对象的常量列表
                    //如果redis中无sex缓存,则查找mysql并加入缓存
                    Long val = redis.opsForList().size(variableCode + "name");
                    if (val == 0L) {
                        //根据变量的constant_id查询变量code,再根据变量code查询该code对应的所有变量
                        Long constantId = variableBind.getConstantId();
                        ConstantInfo constantInfo1 = constantInfoService.selectById(constantId);
                        String conKey = constantInfo1.getConKey();
                        List<ConstantInfo> ciList = constantInfoService.selectList(new EntityWrapper<ConstantInfo>().eq("con_key", conKey).eq("con_type", "1"));
                        for (ConstantInfo constantInfo : ciList) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("value", constantInfo.getConCode());
                            map.put("name", constantInfo.getConName());
                            list.add(map);
                            //缓存数据
                            redis.opsForList().leftPush(variableCode + "name", constantInfo.getConName());
                            redis.opsForList().leftPush(variableCode + "value", constantInfo.getConCode());
                        }
                    } else {
                        for (; val-- > 0; ) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("value", redis.opsForList().index(variableCode + "value", val));
                            map.put("name", redis.opsForList().index(variableCode + "name", val));
                            list.add(map);
                        }
                    }
                }
                variableBind.setOptionData(list);
            }
            modelSence.setData(variableBindList);
        }
        return result;
    }

    /**
     * 为变量赋值
     * http://localhost:8765/rule/service/actProcRelease/init
     *
     * @returns
     */
    @PostMapping(value = "scene/variable/init")
    @ApiOperation(value = "给变量赋值")
    public Result addVariable(HttpServletRequest request) {
        try {
            logger.info("----给变量赋值----");
            Map<String, String[]> parameterMap = request.getParameterMap();
            logger.info(parameterMap.toString());
            Set<String> keys = parameterMap.keySet();
            Iterator<String> key = keys.iterator();
            while (key.hasNext()) {
                String next = key.next();
                String temValue = parameterMap.get(next)[0];
                String[] strings = next.split("#");
                logger.info(strings.toString());
                EntityWrapper<VariableBind> wrapper = new EntityWrapper<>();
                wrapper.eq("SENCE_VERSION_ID", strings[0]);
                wrapper.eq("VARIABLE_CODE", strings[1]);
                VariableBind variableBind = new VariableBind();
                variableBind.setTmpValue(temValue);
                variableBindService.update(variableBind, wrapper);
            }
            Result result = Result.success();
            result.setMsg("保存成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(1, "保存失败");
    }

    @GetMapping(value = "scene/variable/auto")
    @ApiOperation(value = "执行自动测试")
    public ActProcRelease getVariablesByActProcRealeseIdAuto(String actProcRealeseId, HttpServletRequest request) {
        logger.info("开始自动测试" + actProcRealeseId);
        return this.getVariablesByActProcRealeseId(actProcRealeseId, request);
    }

    /**
     * 为变量赋值
     * http://localhost:8765/rule/service/actProcRelease/init
     *
     * @returns
     */
    @PostMapping(value = "scene/variable/init/auto")
    @ApiOperation(value = "自动测试给变量赋值")
    public Result addVariableAuto(HttpServletRequest request) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<String> keys = parameterMap.keySet();
            Iterator<String> key = keys.iterator();
            while (key.hasNext()) {
                String next = key.next();
                logger.info("--------参数名称------" + next);
                if (!"amount".equals(next)) {
                    String val = parameterMap.get(next)[0];
                    String[] strings = next.split("#");
                    //String senceVersionId,String variableCode,String tmpValue,String bindTable,String bindColumn
                    String senceVersionId = strings[0];
                    String variableCode = strings[1];
                    String field = strings[2];
                    //判断是绑定表格的值还是绑定列
                    if ("table".equals(field) && val != null) {
                        variableBindService.myUpdate(senceVersionId, variableCode, null, val, null);
                    } else if ("column".equals(field) && val != null) {
                        variableBindService.myUpdate(senceVersionId, variableCode, null, null, val);
                    }
                }
            }
            Result result = Result.success();
            result.setMsg("保存成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(1, "保存失败");
    }

    @PutMapping(value = "status")
    @ApiOperation(value = "发布.启用或停用模型")
    public Result publishModelById(ActProcRelease actProcRelease, String flag) {
        Result<Object> result = new Result<>();
        //通过flag判断要修改的状态
        if ("0".equals(flag)) {
            actProcRelease.setIsEffect("1");
        } else {
            actProcRelease.setIsEffect("0");
            actProcRelease.setVersionType("1");
        }
        boolean b = actProcReleaseService.updateById(actProcRelease);
        if (b) {
            result.setCode(1);
        } else {
            result.setCode(0);
        }
        return result;
    }

    @GetMapping(value = "redis")
    @ApiOperation(value = "redis测试")
    public Object redisTest() {
        ArrayList<String> list = new ArrayList<>();
        //如果redis中无sex缓存,则查找mysql并加入缓存
        Long sex = redis.opsForList().size("education");
        if (sex == 0L) {
            EntityWrapper<ConstantInfo> wrapper = new EntityWrapper<>();
            wrapper.eq("con_key", "education");
            wrapper.eq("con_type", "1");
            List<ConstantInfo> constantInfos = constantInfoService.selectList(wrapper);
            for (ConstantInfo constantInfo : constantInfos) {
                list.add(constantInfo.getConName());
                redis.opsForList().leftPush("sex", constantInfo.getConName());
            }
            //加入缓存
        } else {
            list.addAll(redis.opsForList().range("sex", 0, -1));
        }

        return null;
    }

}

