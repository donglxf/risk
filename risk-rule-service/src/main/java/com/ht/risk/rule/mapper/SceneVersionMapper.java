package com.ht.risk.rule.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.SceneVersion;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-03
 */
public interface SceneVersionMapper extends SuperMapper<SceneVersion> {
	/**
	 * 
	* @Title: getNoBindVariableRecord
	* @Description: 查询所有未绑定变量的版本记录
	* @param @return    设定文件
	* @return List<SceneVersion>    返回类型
	* @throws
	 */
	public List<SceneVersion> getNoBindVariableRecord(Page<SceneVersion> pages, Wrapper<SceneVersion> wrapper);

	/**
	 * 
	* @Title: getMaxTestVersion
	* @Description: 查找最大测试版本号
	* @param @param scene
	* @param @return    设定文件
	* @return float    返回类型
	* @throws
	 */
	public Map<String,Object> getMaxTestVersion(Map<String,Object> paramMap);

	/**
	 * 获取规则执行信息
	 * @return
	 */
	Map<String,Object> getRuleExecInfo(Map<String,Object> obj);

	/**
	 * 计算规则平均耗时
	 * @return
	 */
	List<Map<String,Object>> getRuleAgeTime(Map<String,Object> obj);

	/**
	 * 统计规则在某段时间内执行次数
	 * @return
	 */
	List<Map<String,Object>> getRuleExecTotal(Map<String,Object> obj);

}
