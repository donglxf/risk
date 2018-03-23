package com.ht.risk.eip.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;


public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {
    public FeignBasicAuthRequestInterceptor() {
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("app", "fk");
//            template.header("Authorization", System.getProperty("fangjia.auth.token"));
    }
}
