package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.Business;
import com.ht.risk.rule.mapper.BusinessMapper;
import com.ht.risk.rule.service.BusinessService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangpeng
 * @since 2018-01-22
 */
@Service
public class BusinessServiceImpl extends BaseServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Override
    public boolean checkKey(String key,String other,Long id) {
        Integer count = 0;
        if(id != null ){
            count = this.baseMapper.selectCount(new EntityWrapper<Business>()
                    .eq("business_name", key).and().ne("business_id",id));
        }else{
            count = this.baseMapper.selectCount(new EntityWrapper<Business>()
                    .eq("business_name", key));
        }

        count = count == null?0:count;
        return count > 0 ? true:false;
    }
}
