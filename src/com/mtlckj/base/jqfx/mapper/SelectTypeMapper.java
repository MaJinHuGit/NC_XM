package com.mtlckj.base.jqfx.mapper;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.DateCount;
import com.mtlckj.base.jqfx.domain.Gwxq;
import com.mtlckj.base.jqfx.domain.ReturnTree;

public interface SelectTypeMapper {
	
	/**
	 * 获取动态树
	 * @return
	 */
	List<ReturnTree> getReturnTree();
	
	/**
	 * 专题概况
	 * @param params
	 * @return
	 */
	Integer getZtgk(Map<String, Object> params);
	
	/**
	 * 高危辖区
	 * @param params
	 * @return
	 */
	List<Gwxq> getGwxq(Map<String, Object> params);

	/**
	 * 获取单个犯罪类型对应的数据列表
	 * @param params
	 * @return
	 */
	List<DateCount> getTu1(Map<String, Object> params);

	/**
	 * 获得环比相关数据
	 * @param map
	 * @return
	 */
	Map<String, Object> getTu2(Map<String, Object> map);

	/**
	 * 点击柱状图获取详细信息
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getList(Map<String, Object> params);

	/**
	 * 获取总记录条数
	 * @param params
	 * @return
	 */
	Integer getTotal(Map<String, Object> params);

	/**
	 * 高危区域柱状图
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> gwqy(Map<String, Object> params);

	/**
	 * 点击高危区域柱状图获取详情
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findByOrgcode(Map<String, Object> params);

	/**
	 * 获取(点击高危区域柱状图获取详情)记录条数
	 * @param params
	 * @return
	 */
	Integer getTotalByOrgcode(Map<String, Object> params);

	/**
	 * 根据犯罪类型、时间段，返回数据用于标识地图，数据包括：经度、纬度、数据主键、犯罪类型
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> forMap(Map<String, Object> params);

	/**
	 * 得到派出所名称和编码
	 * @return
	 */
	List<Map<String, Object>> getPcsList();

	/**
	 * 得到指定的派出所高危区域柱状图
	 * @return
	 */
	List<Map<String, Object>> gwqy2(Map<String, Object> params);

	/**
	 * 得到指定的派出所文字描述
	 * @return
	 */
	List<Gwxq> getGwxq2(Map<String, Object> params);
}
