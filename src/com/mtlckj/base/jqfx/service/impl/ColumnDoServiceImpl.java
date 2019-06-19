package com.mtlckj.base.jqfx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.ColumnDO;
import com.mtlckj.base.jqfx.mapper.ColumnDOMapper;
import com.mtlckj.base.jqfx.service.ColumnDoService;
import com.mtlckj.base.jqfx.vo.DocTreeVo;

/**
 * ColumnDO的服务，用于对ColumnDO实体的控制
 * @author 杨智强
 *
 */
@Service
public class ColumnDoServiceImpl implements ColumnDoService {
	
	@Autowired
	private ColumnDOMapper columnDomapper;
	@Override
	public List<ColumnDO> listConlumnByTableName(String tableName) {
		return columnDomapper.listColumnByTableName(tableName);
	}
	@Override
	public List<DocTreeVo> getZhibiaoByTableName(String tableName) {
		List<ColumnDO> columns=columnDomapper.listColumnByTableName(tableName);
		List<DocTreeVo> docTrees=new ArrayList<DocTreeVo>();
		for(ColumnDO column:columns){
			DocTreeVo docTree=new DocTreeVo();
			docTree.setName(column.getColumnComments());
			docTree.setAlias(column.getColumnName());	
			docTree.setDataType(column.getDataType());
			docTrees.add(docTree);
		}
		return docTrees;
	}

}
