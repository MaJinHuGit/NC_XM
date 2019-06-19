package com.mtlckj.base.jqfx.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Wffzjq;

/**
 * 违法犯罪警情Service
 * 
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */
public interface BzjqService {

	/**
	 * 每周统计
	 * 
	 * @return
	 */
	Map<String, Object> countByWeek(Map<String, Object> params);
	/**
	 * 
	* @Function: BzjqService.java
	* @Description:  按小时统计
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2019年1月7日 上午11:12:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年1月7日      唐青                                        v1.0.0               修改原因
	 */
	Map<String, Object> countByHours(Map<String, Object> params);
	/**
	 * 警情列表
	 * 
	 * @return
	 */
	List<Wffzjq> getList(Map<String, Object> params);
	
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer count(Map<String, Object> params);
	
	/**
	 * 
	 * 根据接警单编号查询
	 * @return
	 */
	Wffzjq get(String jjdbh);
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助  按照案发地点统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getAfdz(Map<String, Object> params);
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助 案发地点分页统计
	 * @param params
	 * @return
	 */
	int getAfdzCount(Map<String, Object> params);
	
	/**
	 *  '交通事故' '噪音扰民' 按照案发地点统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getAfdzByLx(Map<String, Object> params);
	
	/**
	 * '交通事故' '噪音扰民'  案发地点分页统计
	 * @param params
	 * @return
	 */
	int getAfdzByLxCount(Map<String, Object> params);
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助  按照关键字统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getGjz(Map<String, Object> params);
	

	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助 关键字分页统计
	 * @param params
	 * @return
	 */
	int getGjzCount(Map<String, Object> params);
	
	/**
	 *  '交通事故' '噪音扰民' 按照关键字统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getGjzByLx(Map<String, Object> params);
	
	/**
	 * '交通事故' '噪音扰民'  关键字分页统计
	 * @param params
	 * @return
	 */
	int getGjzByLxCount(Map<String, Object> params);
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助  按照报警时间统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getBjsj(Map<String, Object> params);
	
	/**
	 *  '交通事故' '噪音扰民' 按照报警时间统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getBjsjByLx(Map<String, Object> params);
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助  按照报警系类统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getBjxl(Map<String, Object> params);
	
	/**
	 *  '交通事故' '噪音扰民' 按照报警系类统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getBjxlByLx(Map<String, Object> params);
	
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助  按照报警单位统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getGxdw(Map<String, Object> params);
	
	/**
	 *  '交通事故' '噪音扰民' 按照报警单位统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getGxdwByLx(Map<String, Object> params);
	
	/**
	 * 投诉'  '骚扰误报'  '重复报警' '咨询' 求助  按照报警号码统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getBjdh(Map<String, Object> params);
	
	int getBjdhCount(Map<String, Object> params);
	/**
	 *  '交通事故' '噪音扰民' 按照报警号码统计
	 * @param params
	 * @return
	 */
	List<Wffzjq> getBjdhByLx(Map<String, Object> params);
	
	int getBjdhByLxCount(Map<String, Object> params);
	/**
	 * 警情列表
	 * 
	 * @return
	 */
	List<Wffzjq> getListGxdw(Map<String, Object> params);
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer countGxdw(Map<String, Object> params);
	
	/**
	 * 警情列表
	 * 
	 * @return
	 */
	List<Wffzjq> getListBjdh(Map<String, Object> params);
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer countBjdh(Map<String, Object> params);
	
	/**
	 * 警情列表
	 * 
	 * @return
	 */
	List<Wffzjq> getListBjxl(Map<String, Object> params);
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer countBjxl(Map<String, Object> params);
	
	/**
	 * 警情列表
	 * 
	 * @return
	 */
	List<Wffzjq> getListAfdz(Map<String, Object> params);
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer countAfdz(Map<String, Object> params);/**
	 * 警情列表
	 * 
	 * @return
	 */
	List<Wffzjq> getListGjz(Map<String, Object> params);
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer countGjz(Map<String, Object> params);
	
	Map<String, Object> getWffzCount(Map<String, Object> params);
}
