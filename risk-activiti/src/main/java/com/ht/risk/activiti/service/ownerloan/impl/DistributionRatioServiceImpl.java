package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.service.ownerloan.DistributionRatioService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;

import java.util.Random;

public class DistributionRatioServiceImpl implements DistributionRatioService {

    private Expression persionRatioExp;
    private Expression enterpriseRatioExp;
    private static Random random = new Random();

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }

    // d  百分比，0是人工，1 是自動
    public static int randomRatio(double d) {
        double ratio  = d * 100;
        double autoRation = 100 - ratio;
        // 如果配置錯誤，判定走人工
        if (ratio <= 0 || ratio > 100) {
            return 0;
        }
        Integer n = random.nextInt(100);
        if(n>0 && n< ratio){
            return 0;
        }
        return 1;
    }

    public Expression getPersionRatioExp() {
        return persionRatioExp;
    }

    public void setPersionRatioExp(Expression persionRatioExp) {
        this.persionRatioExp = persionRatioExp;
    }

    public Expression getEnterpriseRatioExp() {
        return enterpriseRatioExp;
    }

    public void setEnterpriseRatioExp(Expression enterpriseRatioExp) {
        this.enterpriseRatioExp = enterpriseRatioExp;
    }
}
