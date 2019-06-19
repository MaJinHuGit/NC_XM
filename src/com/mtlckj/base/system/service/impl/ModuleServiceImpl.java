package com.mtlckj.base.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtlckj.base.system.domain.ModuleDO;
import com.mtlckj.base.system.mapper.ModuleMapper;
import com.mtlckj.base.system.service.ModuleService;


@Service
@Transactional(rollbackFor=Exception.class)
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;
	
	@Override
	public ModuleDO findById(Long id) {
		// TODO Auto-generated method stub
		return moduleMapper.findByid(id);
	}

	@Override
	public Long save(ModuleDO module) {
		// TODO Auto-generated method stub
		return moduleMapper.save(module);
	}

	@Override
	public List<ModuleDO> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return moduleMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return moduleMapper.count(map);
	}

	@Override
	public int remove(Long moduleId) {
		return moduleMapper.remove(moduleId);
	}
	
	

}
