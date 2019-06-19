package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Jzjqxx;
import com.mtlckj.base.jqfx.domain.JzjqxxVo;

public interface WffzjqMapper {

	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 警情信息统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<Jzjqxx> getWeekList(Map<String,Object> map);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 用于页面文字描述 警情信息统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<Jzjqxx> getWeekDs(Map<String,Object> map);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 违法犯罪警情总和
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	Jzjqxx getSum(Map<String,Object> map);
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description:今年每周的违法犯罪警情统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<Jzjqxx> getNowYearWeek(Map<String,Object> map);
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 上年每周的违法犯罪警情统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<Jzjqxx> getLastYearWeek(Map<String,Object> map);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 全区各所辖区警情分类统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<JzjqxxVo> getQqjq(Map<String,Object> map);
	/**
	 * 
	* @Function: JzjqxxService.java
	* @Description: 全区警情分类本周，上周，上年同期总和
	*
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<JzjqxxVo> getQqjqSum(Map<String,Object> map );
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 全区各辖区违法警情累计同比统计表
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	 */
	List<JzjqxxVo> getQqjqTb(Map<String,Object> map );
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: ”三盗两抢一诈骗“警情及其他警情情况
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	 */
	List<JzjqxxVo> getDqzTbHb(Map<String,Object> map );



}
