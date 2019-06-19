package com.mtlckj.base.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mtlckj.base.system.domain.ModuleDO;

public interface ModuleService {
	ModuleDO findById(Long id);
	
	Long save(ModuleDO module);
	
	List<ModuleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int remove(Long moduleId);
}
