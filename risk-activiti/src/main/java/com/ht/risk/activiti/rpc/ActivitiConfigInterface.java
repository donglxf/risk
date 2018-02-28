package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.activiti.RpcActExcuteTask;
import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-activiti-extend")
public interface ActivitiConfigInterface {

	@RequestMapping("/task/updateTask")
	public Result<Boolean> updateTask(RpcActExcuteTask rpcActExcuteTask);

}
