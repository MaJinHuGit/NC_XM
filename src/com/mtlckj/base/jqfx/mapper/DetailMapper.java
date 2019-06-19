package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mtlckj.base.jqfx.domain.Details;

/**
 * 点击数字显示详细信息
 * @author 王军
 *
 */
public interface DetailMapper {

	/**
	 * 获取单条详细信息记录
	 * @param id
	 * @return
	 */
	Details getOne(@Param("id") String id);

	/**
	 * 获取无犯罪类型的详细列表
	 * @param params
	 * @return
	 */
	List<Details> showDetilNoType(Map<String, Object> params);

	/**
	 * 获取指定犯罪类型的详细列表
	 * @param params
	 * @return
	 */
	List<Details> showDetil(Map<String, Object> params);

	/**
	 * 无犯罪类型的分页统计
	 * @param params
	 * @return
	 */
	Integer getTotal(Map<String, Object> params);

	/**
	 * 指定犯罪类型的分页统计
	 * @param params
	 * @return
	 */
	Integer getTotalNoType(Map<String, Object> params);

}
