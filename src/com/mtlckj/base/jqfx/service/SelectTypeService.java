package com.mtlckj.base.jqfx.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Ztree;

public interface SelectTypeService {
	
	/**
	 * 获取动态数
	 * @return
	 */
	List<Ztree> getReturnTree();
	
	/**
	 * 专题概况统计
	 * @param params
	 * @return
	 * @author 王军
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> getZtgk(Map<String, Object> params) throws UnsupportedEncodingException;

	/**
	 * 高危辖区显示
	 * @param params
	 * @return
	 * @author 王军
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> getGwxq(Map<String, Object> params) throws UnsupportedEncodingException;

	/**
	 * 获得警情走势图的数据
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> getTu1(Map<String, Object> params) throws UnsupportedEncodingException;

	/**
	 * 警情类别统计图
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> getTu2(Map<String, Object> params) throws UnsupportedEncodingException;

	/**
	 * 点击柱状图获取详细信息
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getList(Map<String, Object> params);

	/**
	 * 点击柱状图--获取总记录条数
	 * @param params
	 * @return
	 */
	int getTotal(Map<String, Object> params);

	/**
	 * 高危区域柱状图
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> gwqy(Map<String, Object> params) throws UnsupportedEncodingException;
	
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
	int getTotalByOrgcode(Map<String, Object> params);

	/**
	 * 根据犯罪类型、时间段，返回数据用于标识地图，数据包括：经度、纬度、数据主键、犯罪类型
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	List<Map<String, Object>> forMap(Map<String, Object> params) throws UnsupportedEncodingException;

	/**
	 * 得到派出所名称和编码
	 * @return
	 */
	List<Map<String, Object>> getPcsList();
}
