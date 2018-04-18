package com.ht.risk.eip.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@RefreshScope
@Component
public class BlackAtiveUtil {

    @Value("${risk.eip.cache.black.active.time}")
    private int blackActiveTime;

     /**
     * 默认黑名单缓存时间为一个月
     * @return
     */
    public  Date getDefaultBlackActiveTime(){
        System.out.println(blackActiveTime);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,-blackActiveTime);
        return  c.getTime();
    }

    public boolean hasBlackActive(Date date){
        if(date == null){
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,blackActiveTime);
        if(System.currentTimeMillis() - c.getTime().getTime() > 0){
            return false;
        }
        return true;
    }

    public  Date getBlackActiveTime(int dayCount){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,-dayCount);
        return  c.getTime();
    }

    /**
     * 默认白名单缓存时间为一个月
     * @return
     */
    public  Date getDefaultWhiteActiveTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,-1);
        return  c.getTime();
    }

    public  Date getWhiteActiveTime(int dayCount){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,-dayCount);
        return  c.getTime();
    }

    public int getBlackActiveTime() {
        return blackActiveTime;
    }

    public void setBlackActiveTime(int blackActiveTime) {
        this.blackActiveTime = blackActiveTime;
    }
}
