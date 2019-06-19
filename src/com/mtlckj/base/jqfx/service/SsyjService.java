package com.mtlckj.base.jqfx.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Ssyj;
import com.mtlckj.base.jqfx.vo.SsyjHotMark;
import com.mtlckj.base.jqfx.vo.Xqajlb;


public interface SsyjService {
	
	Ssyj countByWeek(Map<String,Object> map);
	
	List<SsyjHotMark> listZpByWeek(Map<String,Object> map);
	List<SsyjHotMark> listQcByWeek(Map<String,Object> map);
	List<SsyjHotMark> listLqByWeek(Map<String,Object> map);
	List<SsyjHotMark> listRsdqByWeek(Map<String,Object> map);
	List<SsyjHotMark> listScdqByWeek(Map<String,Object> map);
	List<SsyjHotMark> listMdByWeek(Map<String,Object> map);
	
	int countZsByWeek(Map<String,Object> map);
	
	Xqajlb xqajlbTj(Map<String,Object> map);
}
