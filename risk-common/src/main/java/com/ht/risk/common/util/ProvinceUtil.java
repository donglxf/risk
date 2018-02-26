package com.ht.risk.common.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import sun.applet.Main;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 省市互转
 */

public class ProvinceUtil {

    /**
     * 借款人年龄加借款期限
     * @param age 借款人年龄
     * @param borrowMonth 借款期限
     * @return
     */
    public static double getborrowAge(int age,int borrowMonth){
        DecimalFormat df=new DecimalFormat("0.0");
        BigDecimal ageb=new BigDecimal(String.valueOf(age));
        BigDecimal bigDecimal=new BigDecimal(String.valueOf(df.format((float)borrowMonth/12)));
        BigDecimal result=bigDecimal.add(ageb);
        return Double.parseDouble(result.toString());
    }

    public static void main(String args[]){
       System.out.println( getborrowAge(12,18));
    }
}
