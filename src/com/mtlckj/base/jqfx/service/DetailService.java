package com.mtlckj.base.jqfx.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Details;

public interface DetailService {

	/**
	 * 获得一个详细的对象
	 * @param params
	 * @return
	 */
	Details getOne(String id);

	/**
	 * 获得无犯罪类型的详细列表
	 * @param params
	 * @return
	 */
	List<Details> showDetilNoType(Map<String, Object> params);

	/**
	 * 获得指定犯罪类型的详细列表
	 * @param params
	 * @return
	 */
	List<Details> showDetil(Map<String, Object> params);

	/**
	 * 获得指定犯罪类型的总记录条数
	 * @param params
	 * @return
	 */
	int getTotal(Map<String, Object> params);

	/**
	 * 获得无犯罪类型的总记录条数
	 * @param params
	 * @return
	 */
	int getTotalNoType(Map<String, Object> params);

}
