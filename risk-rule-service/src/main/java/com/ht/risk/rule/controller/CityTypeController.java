package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.comenum.ActionEnum;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.mapper.DelFindMapper;
import com.ht.risk.rule.service.ActionInfoService;
import com.ht.risk.rule.service.SceneInfoService;
import com.ht.risk.rule.util.anno.OperationDelete;
import com.ht.risk.rule.vo.ActionInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


//import java.util.List;


/**
 * <p>
 * 规则动作定义信息 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/cityType")
public class CityTypeController extends BaseController {

    @Autowired
    private DelFindMapper delFindMapper;

    @GetMapping("/getCityTypeByCityName")
    public Result<String> getByIds(String cityName) {
        String list = delFindMapper.findCityType(cityName);
        return Result.success(list);

    }

}

