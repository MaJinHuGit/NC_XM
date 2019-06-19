package com.mtlckj.base.system.mapper;


import java.util.List;
import java.util.Map;

import com.mtlckj.base.system.domain.DeptDO;


/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
public interface DeptMapper {

	public DeptDO get(Long deptId);
	
	public List<DeptDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(DeptDO dept);
	
	public int update(DeptDO dept);
	
	public int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);
	
	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
}
