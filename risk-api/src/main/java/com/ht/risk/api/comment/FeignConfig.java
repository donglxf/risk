package com.ht.risk.api.comment;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class FeignConfig implements RequestInterceptor{

	@Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token", getHeaders(getHttpServletRequest()).get("token"));
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        Enumeration<String> body=request.getParameterNames();
        while (body.hasMoreElements()) {
            String key = body.nextElement();
            System.out.println(key);
//            String value = request.getHeader(key);
//            map.put(key, value);
        }
        return map;
    }
}

