package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.mapper.ActionInfoMapper;
import com.ht.risk.rule.mapper.ActionRuleRelMapper;
import com.ht.risk.rule.service.ActionInfoService;
import com.ht.risk.rule.util.StringUtil;
import com.ht.risk.rule.vo.ActionInfoVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 规则动作定义信息 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class ActionInfoServiceImpl extends BaseServiceImpl<ActionInfoMapper, ActionInfo> implements ActionInfoService {

    @Resource
    private ActionInfoMapper actionInfoMapper;

    @Resource
    private ActionRuleRelMapper actionRuleRelMapper;

    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取动作列表
     *
     * @param baseRuleActionInfo 参数
     * @param page               分页信息
     */
    @Override
    public Page<ActionInfo> findBaseRuleActionInfoPage(ActionInfo baseRuleActionInfo, Page page) throws Exception {
        List<ActionInfo> list = this.actionInfoMapper.findBaseRuleActionInfoList(baseRuleActionInfo);
        return null;
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据场景获取所有的动作信息
     *
     * @param sceneInfo 参数
     */
    @Override
    public List<ActionInfo> findRuleActionListByScene(SceneInfo sceneInfo) throws Exception {
        if (null == sceneInfo || (null == sceneInfo.getSceneId() &&
                StringUtil.strIsNull(sceneInfo.getSceneIdentify()))) {
            throw new NullPointerException("参数缺失！");
        }
        return this.actionInfoMapper.findRuleActionListByScene(sceneInfo);
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据规则id获取动作集合
     *
     * @param ruleId 参数
     */
    @Override
    public List<ActionInfo> findRuleActionListByRule(final Long ruleId) throws Exception {
        if (null == ruleId) {
            throw new NullPointerException("参数缺失");
        }

        return this.actionInfoMapper.findRuleActionListByRule(ruleId);
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 查询是否有实现类的动作
     *
     * @param ruleId 规则id
     */
    @Override
    public Integer findRuleActionCountByRuleIdAndActionType(Long ruleId) {
        if (null == ruleId) {
            throw new NullPointerException("参数缺失");
        }
        return this.actionInfoMapper.findRuleActionCountByRuleIdAndActionType(ruleId);
    }

    @Override
    public List<ActionInfoVo> findByIds(String ids) {
        if (null == ids) {
            throw new NullPointerException("参数缺失");
        }
        return this.actionInfoMapper.findByIds(ids);
    }

    @Override
    public List<ActionInfoVo> findActionVos(Long sceneId) {

        if (null == sceneId) {
            throw new NullPointerException("参数缺失");
        }

        String actionIds = actionRuleRelMapper.findActionIdsBySceneId(sceneId);
        if(StringUtils.isBlank(actionIds)){
            return new ArrayList<>();
        }
        return this.actionInfoMapper.findByIds(actionIds);


    }

    @Override
    public List<ActionInfoVo> findActionAllVos(Long businessId) {
        return this.actionInfoMapper.findActionAllVos( businessId);
    }

    @Override
    public boolean checkKey(String key,String other,Long id) {
        Integer count = 0;
        if(id != null ){
            count = this.baseMapper.selectCount(new EntityWrapper<ActionInfo>()
                    .eq("action_class", key).and().ne("action_id",id));
        }else{
            count = this.baseMapper.selectCount(new EntityWrapper<ActionInfo>()
                    .eq("action_class", key));
        }

        count = count == null?0:count;
        return count > 0 ? true:false;
    }

    @Override
   public boolean insertOrUpdate(ActionInfo actionInfo){
        return actionInfoMapper.insertOrUpdate(actionInfo);
    }
}
