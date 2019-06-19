package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Hddjq;
import com.mtlckj.base.jqfx.vo.HddjqVo;

/**
 * <p>Title: HddjqMapper</p> 
 * <p>Package: com.mtlckj.base.jqfx.mapper</p>  
 * <p>Description: 黄赌毒警情mapper</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author majinhu  
 * @date 2018年10月19日   
 * @version 1.0
 */
public interface HddjqMapper {
	
	

	/**
	 * 获取警情列表
	 * <p>Title: getList</p>   
	 * <p>Description: </p>   
	 * @param params
	 * @return
	 */
	List<Hddjq> getList(Map<String, Object> params);
	
	
	/**
	 * 分页统计
	 * @return
	 */
	Integer count(Map<String, Object> params);
	
	
	/**
	 * 根据办理单位获得警情 
	 * <p>Title: getJqList</p>   
	 * <p>Description: </p>   
	 * @param params
	 * @return
	 */
	List<HddjqVo> getHddjqByBldw(Map<String, Object> params);
	

}
