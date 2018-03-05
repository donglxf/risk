package com.ht.risk.rule.facade.impl;

import com.ht.risk.rule.facade.CheckKeyFacade;
import com.ht.risk.rule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 统一验证 标识的唯一性 服务组合件
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class CheckKeyFacadeImpl implements CheckKeyFacade {
    @Autowired
    private EntityInfoService entityInfoService;

    @Autowired
    private SceneInfoService sceneInfoService;
    @Autowired
    private EntityItemInfoService entityItemInfoService;

    @Autowired
    private ConstantInfoService constantInfoService;

    @Autowired
    private ActionInfoService actionInfoService;

    @Autowired
    private ActionParamInfoService actionParamInfoService;

    @Override
    public boolean checkKey(String key,Integer type,String other) {
        switch (type){
            case 1:
              return entityInfoService.checkKey(key,null);
            case 2:
                return entityItemInfoService.checkKey(key,other);
            case 3:
                return sceneInfoService.checkKey(key,null);
            case 4:
                return constantInfoService.checkKey(key,null);
            case 5:
                return actionInfoService.checkKey(key,null);
            case 6:
                return actionParamInfoService.checkKey(key,null);
            default:
                return false;
        }
    }
}
