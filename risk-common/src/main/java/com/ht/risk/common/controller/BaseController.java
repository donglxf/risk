package com.ht.risk.common.controller;

import com.ht.ussp.bean.LoginUserInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 说明：
 * @auther 张鹏
 * @create
 */
public class BaseController {
    @Autowired
    public LoginUserInfoHelper userInfoHelper;

}
