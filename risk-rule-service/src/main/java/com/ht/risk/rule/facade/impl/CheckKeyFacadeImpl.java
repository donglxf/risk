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
    private ActionParamInfoService actionParamInfoService;

    @Autowired
    private ConstantInfoService constantInfoService;

    @Autowired
    private ActionInfoService actionInfoService;




    @Override
    public boolean checkKey(String key,Integer type,String other,Long id) {
        switch (type){
            case 1:
              return entityInfoService.checkKey(key,null,id);
            case 2:
                return entityItemInfoService.checkKey(key,other,id);
            case 3:
                return sceneInfoService.checkKey(key,null,id);
            case 4:
                return actionParamInfoService.checkKey(key,null,id);
            case 5:
                return constantInfoService.checkKey(key,null,id);
            case 6:
                return actionInfoService.checkKey(key,null,id);
            default:
                return false;
        }
    }
}
