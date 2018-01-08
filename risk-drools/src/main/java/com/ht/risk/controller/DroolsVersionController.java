package com.ht.risk.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ht.risk.service.DroolsRuleEngineService;


/**
* @ClassName: DroolsVersionController
* @Description: drools版本控制
* @author dyb
* @date 2018年1月3日 下午3:09:05
* 
*/
@RestController
public class DroolsVersionController {
	
	@Resource
    private DroolsRuleEngineService droolsRuleEngineService;
	
	/**
	 * 
	* @Title: excuteDroolsScene
	* @Description: 根据场景生成规则文件字符串
	* @param @param dev 场景名
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/getDroolsVersion/{dev}")
	@ResponseBody
	public String getDroolsVersion(@PathVariable(name="dev") String id){
		String str=null;
        try {
            String identity=droolsRuleEngineService.getSceneIdentifyById(id);
            str= droolsRuleEngineService.getDroolsString(identity,null);
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }
}
