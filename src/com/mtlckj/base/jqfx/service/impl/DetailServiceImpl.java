package com.mtlckj.base.jqfx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.Details;
import com.mtlckj.base.jqfx.mapper.BzjqMapper;
import com.mtlckj.base.jqfx.mapper.DetailMapper;
import com.mtlckj.base.jqfx.service.DetailService;

/**
 * 点击数字显示详细信息
 * @author 王军
 *
 */
@Service
public class DetailServiceImpl implements DetailService {

	@Autowired
    private DetailMapper detailMapper;
	
	@Override
	public Details getOne(String id) {
		return detailMapper.getOne(id);
	}

	@Override
	public List<Details> showDetilNoType(Map<String, Object> params) {
		return detailMapper.showDetilNoType(params);
	}

	@Override
	public List<Details> showDetil(Map<String, Object> params) {
		return detailMapper.showDetil(params);
	
	}

	@Override
	public int getTotal(Map<String, Object> params) {
		return detailMapper.getTotal(params);
	}

	@Override
	public int getTotalNoType(Map<String, Object> params) {
		return detailMapper.getTotalNoType(params);
	}

}
