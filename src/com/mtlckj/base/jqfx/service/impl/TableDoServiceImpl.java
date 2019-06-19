package com.mtlckj.base.jqfx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.TableDO;
import com.mtlckj.base.jqfx.mapper.TableMapper;
import com.mtlckj.base.jqfx.service.TableDoService;

@Service
public class TableDoServiceImpl implements TableDoService {
	
	@Autowired
	private TableMapper tableMapper;

	@Override
	public List<TableDO> listTable() {
		// TODO Auto-generated method stub
		return tableMapper.listTable();
	}

}
