package com.mtlckj.base.system.mapper;


import java.util.List;
import java.util.Map;

import com.mtlckj.base.system.domain.RoleDO;


/**
 * 角色
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-02 20:24:47
 */
public interface RoleMapper {

	RoleDO get(Long roleId);
	Map<Integer, Integer> getRoles(Long id);
	List<RoleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);
}
