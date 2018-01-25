package org.ht.risk.log.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import org.apache.commons.lang3.StringUtils;
import org.ht.risk.log.entity.DroolsLog;
import org.ht.risk.log.entity.HitRule;
import org.ht.risk.log.entity.TestDroolsLog;
import org.ht.risk.log.mapper.DroolsLogMapper;
import org.ht.risk.log.mapper.TestDroolsLogMapper;
import org.ht.risk.log.service.DroolsLogService;
import org.springframework.stereotype.Service;

import com.ht.risk.common.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-10
 */
@Service
public class DroolsLogServiceImpl extends BaseServiceImpl<DroolsLogMapper, DroolsLog> implements DroolsLogService {

}
