package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.ConstantInfo;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.mapper.ConstantInfoMapper;
import com.ht.risk.rule.service.ConstantInfoService;
import com.ht.risk.rule.vo.EntitySelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-29
 */
@Service
public class ConstantInfoServiceImpl extends BaseServiceImpl<ConstantInfoMapper, ConstantInfo> implements ConstantInfoService {
    //定义：常量id和变量之间的对应关系
    public static Map<Long,List<EntitySelectVo>> constantMap = new HashMap<>();
    @Autowired
    private ConstantInfoMapper constantInfoMapper;
    @Override
    public EntitySelectVo setItemConstants(EntitySelectVo itemvo,EntityItemInfo item) {

        Long constantId = item.getConstantId();
        if(constantId != null ){
            List<EntitySelectVo> constants = constantMap.get(constantId);
            if(constants == null || constants.size() < 1){
                ConstantInfo constantInfo = constantInfoMapper.selectById(constantId);
                Wrapper<ConstantInfo> wrapper = new EntityWrapper<>();
                wrapper.ne("con_type", 0);
                wrapper.eq("con_key", constantInfo.getConKey());
                List<ConstantInfo> list = constantInfoMapper.selectList(wrapper);
                //常量 设置进变量的子集
                List<EntitySelectVo> constantSons = new ArrayList<>();
                for (ConstantInfo info : list) {
                    EntitySelectVo constantSon = new EntitySelectVo();
                    constantSon.setValue(info.getConCode());
                    constantSon.setText(info.getConName());
                    //添加进子集
                    constantSons.add(constantSon);
                }
                itemvo.setSons(constantSons);
                constantMap.put(constantId,constantSons);
            }else {
                itemvo.setSons(constants);
            }
        }
        return itemvo;
    }
}
