package com.tdw.risk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdw.risk.common.result.Result;
import com.tdw.risk.common.util.FieldValidator;
import com.tdw.risk.entity.AccountBankCard;
import com.tdw.risk.service.AccountBankCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 帐号银行卡 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/accountBankCard")
@Api(tags = "AccountBankCardApi", description = "帐号银行卡相关", hidden = true)
public class AccountBankCardController {
    @Autowired
    private AccountBankCardService accountBankCardService;
    /**
     * 实体卡绑定接口<br>
     * @param card 实体卡信息
     * @author 张鹏
     * @date 2017年12月5日 上午10:51:44
     */
    @ApiOperation(value = "实体卡绑定")
    @PostMapping("/binding")
    @Transactional(rollbackFor = Exception.class)
    public Result<AccountBankCard> binding(AccountBankCard card) {
        {
            //非空判断
            if (FieldValidator.validaModelRequiredField(card,"accountBankId,remark")) {
                //数据库操作
                accountBankCardService.insertOrUpdate(card);
            } else {
                return Result.error("-1", "必填字段不能为空");
            }
        }
        return Result.success();
    }
}

