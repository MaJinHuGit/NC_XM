package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Lbtj;
import com.mtlckj.base.jqfx.domain.Ssyj;
import com.mtlckj.base.jqfx.vo.SsyjHotMark;
import com.mtlckj.base.jqfx.vo.ZsFourMOY;

/**
 * 四色预警mapper
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */
public interface SsyjMapper {
	
	Ssyj countByWeek(Map<String,Object> map);
	/**
	 * 获取诈骗的地图信息
	 * @param map
	 * @return
	 */
	List<SsyjHotMark> listZpByWeek(Map<String,Object> map);
	/**
	 * 获取侵财地图信息
	 * @param map
	 * @return
	 */
	List<SsyjHotMark> listQcByWeek(Map<String,Object> map); 
	/**
	 * 获取两抢地图信息
	 * @param map
	 * @return
	 */
	List<SsyjHotMark> listLqByWeek(Map<String,Object> map); 
	/**
	 * 获取入室盗窃地图信息
	 * @param map
	 * @return
	 */
	List<SsyjHotMark> listRsdqByWeek(Map<String,Object> map);
	/**
	 * 获取涉车盗窃地图信息
	 * @param map
	 * @return
	 */
	List<SsyjHotMark> listScdqByWeek(Map<String,Object> map); 
	/**
	 * 获取摩 电地图信息
	 * @param map
	 * @return
	 */
	List<SsyjHotMark> listMdByWeek(Map<String,Object> map); 
	/**
	 * 获取到辖区的总警情
	 * @param map
	 * @return
	 */
	int countZsByWeek(Map<String,Object> map);
	
	/**
	 * 诈骗四年统计
	 * @param map
	 * @return
	 */
	Lbtj zpZsFourMOY(Map<String,Object> map);
	/**
	 * 侵财四年统计
	 * @param map
	 * @return
	 */
	Lbtj qcZsFourMOY(Map<String,Object> map);
	/**
	 * 两抢四年统计
	 * @param map
	 * @return
	 */
	Lbtj lqZsFourMOY(Map<String,Object> map);
	/**
	 * 入室盗窃四年统计
	 * @param map
	 * @return
	 */
	Lbtj rsdqZsFourMOY(Map<String,Object> map);
	/**
	 * 涉车盗窃四年统计
	 * @param map
	 * @return
	 */
	Lbtj scdqZsFourMOY(Map<String,Object> map);
	/**
	 * 摩、电四年统计
	 * @param map
	 * @return
	 */
	Lbtj mdZsFourMOY(Map<String,Object> map);
	/**
	 * 违法犯罪四年统计
	 * @param map
	 * @return
	 */
	Lbtj wfjqZsFourMOY(Map<String,Object> map);
	
	
}
