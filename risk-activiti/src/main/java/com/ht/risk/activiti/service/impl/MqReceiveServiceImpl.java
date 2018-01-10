package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.service.MqReceiveService;

public class MqReceiveServiceImpl implements MqReceiveService {
    @Override
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
