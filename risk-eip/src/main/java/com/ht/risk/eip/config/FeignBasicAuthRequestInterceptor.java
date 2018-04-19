package com.ht.risk.eip.config;

import com.alibaba.fastjson.JSON;
import com.ht.risk.common.util.StringUtils;
import com.ht.risk.eip.controller.BlackController;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {

    protected static final Logger log = LoggerFactory.getLogger(BlackController.class);

    public FeignBasicAuthRequestInterceptor() {
    }

    @Resource
    private HttpServletRequest request;

    @Override
    public void apply(RequestTemplate template) {
        try {
            byte[] obj = template.request().body();
            String str = new String(obj,"UTF-8");
            Map map = JSON.parseObject(str,Map.class);
            Object appObj = map.get("app");
            if(appObj == null){
                log.info("apply set app:fk");
                template.header("app", "fk");
            }else{
                log.info("apply set app:"+String.valueOf(appObj));
                template.header("app", "fk-"+String.valueOf(appObj));
            }
            template.header("content-type", "application/json");
        } catch (Exception e) {
            log.error("获取header信息失败！",e);
            template.header("app", "fk");
        }
    }

}
