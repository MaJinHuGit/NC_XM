package com.mtlckj.base.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.common.domain.DzfxDO;
import com.mtlckj.base.common.mapper.DzfxMapper;
import com.mtlckj.base.common.service.DzfxService;

@Service
public class DzfxServiceImpl implements DzfxService{
	
	@Autowired
	private DzfxMapper dzfxMapper;

	@Override
	public int save(DzfxDO dzfx) {
		// TODO Auto-generated method stub
		return dzfxMapper.save(dzfx);
	}

	@Override
	public List<DzfxDO> list(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dzfxMapper.list(params);
	}

	@Override
	public int count(Map<String, Object> params) {
		
		return dzfxMapper.count(params);
	}

	@Override
	public DzfxDO getById(Long dzfxId) {
		// TODO Auto-generated method stub
		return dzfxMapper.getById(dzfxId);
	}

	@Override
	public int update(DzfxDO dzfx) {
		return dzfxMapper.update(dzfx);
	}

	
	@Override
	public int countForHistory(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dzfxMapper.countForHistory(params);
	}

	@Override
	public List<DzfxDO> search(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dzfxMapper.search(params);
	}

	@Override
	public int remove(Long dzfxId) {
		// TODO Auto-generated method stub
		return dzfxMapper.remove(dzfxId);
	}


}
