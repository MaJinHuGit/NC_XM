package com.mtlckj.base.jqfx.mapper;

import java.util.List;

import com.mtlckj.base.jqfx.domain.ColumnDO;

public interface ColumnDOMapper {
	List<ColumnDO> listColumnByTableName(String tableName);
}
