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

}
