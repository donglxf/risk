package org.ht.risk.log.service;

import org.ht.risk.log.entity.DroolsLog;

import com.ht.risk.common.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-05
 */
public interface DroolsLogService extends BaseService<DroolsLog> {
	
	public String saveLog(DroolsLog entity) ;
	
}
