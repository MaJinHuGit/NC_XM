package com.mtlckj.base.common.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.common.domain.DzfxDO;

public interface DzfxService {
	public int save(DzfxDO dzfx);
	public List<DzfxDO> list(Map<String,Object> params);
	
	public int count(Map<String,Object> params);
	
	public DzfxDO getById(Long dzfxId);
	
	public int update(DzfxDO dzfx);
	
	public List<DzfxDO> search(Map<String,Object> params);
	
	public int countForHistory(Map<String,Object> params);
	
	public int  remove(Long dzfxId);
}
