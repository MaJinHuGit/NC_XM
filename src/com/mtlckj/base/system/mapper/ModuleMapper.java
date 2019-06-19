package com.mtlckj.base.system.mapper;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.system.domain.ModuleDO;

public interface ModuleMapper {
	public ModuleDO findByid(Long id);
	
	public Long save(ModuleDO module);
	
	List<ModuleDO> list(Map<String,Object> map);
	int count(Map<String,Object> map);
	
	int remove(Long moduleId);
}
