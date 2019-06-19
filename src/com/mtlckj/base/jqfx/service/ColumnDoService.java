package com.mtlckj.base.jqfx.service;

import java.util.List;

import com.mtlckj.base.jqfx.domain.ColumnDO;
import com.mtlckj.base.jqfx.vo.DocTreeVo;

public interface ColumnDoService {
	public List<ColumnDO> listConlumnByTableName(String tableName);
	
	public List<DocTreeVo> getZhibiaoByTableName(String tableName);
}
