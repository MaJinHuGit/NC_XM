package com.mtlckj.base.jqfx.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Fields;
import com.mtlckj.base.jqfx.domain.Sys;
import com.mtlckj.base.jqfx.domain.TableResource;

public interface SysService {
	/**
	 * 创建表和字段
	 * @param params  tableName  表名     field 字段
	 * @return
	 */
	int createNewTable(Map<String, Object> params) throws Exception;
	
	/**
	 * 表注释
	 * @param params
	 * @return
	 */
	int updateTableComment(Map<String, Object> params) throws Exception;
	/**
	 * 字段注释
	 * @param params
	 * @return
	 */
	int updateFieldComment(Map<String, Object> params) throws Exception;
	
	/**
	 * 获取自建表的数量
	 * @return
	 * @throws Exception
	 */
	String getZjTableCount() throws Exception;
	
	/**
	 * 查询所有的表名和注释
	 * @return
	 */
	List<Sys> getTable(Map<String, Object> params) throws Exception;
	
	/**
	 * 数据库中所有表的数目
	 * @return
	 */
	int count();
	
	/**
	 * 查询表中的字段，类型和 注释
	 * @return
	 */
	List<Fields> getFields(Map<String, Object> params)throws Exception;
	
	List<Fields> getFieldNoId(Map<String, Object> params)throws Exception;
	/**
	 * 表中的所以的字段总数
	 * <p>TODO</p>
	 * <p>@author 唐青</p>
	 * <p>@date 2019年1月10日 上午10:28:33</p>
	 * @param params tablename 表名
	 * @return int
	 */
	int countFields(Map<String, Object> params)throws Exception;
	
	int save(Map<String, Object> params)throws Exception;
	
	
	/**
	 * 修改字段类型
	 * @param params
	 * @return
	 */
	int updateFieldType(Map<String, Object> params)throws Exception;
	/**
	 * 添加字段 
	 * @param params
	 * @return
	 */
	int addField(Map<String, Object> params)throws Exception;
	/**
	 *  删除字段 
	 * @param params
	 * @return
	 */
	int deleteField(Map<String, Object> params)throws Exception;
	/**
	 * 自定义字段名称后缀数字的最大值 
	 * @param params
	 * @return
	 */
	String getZjFieldCount(Map<String, Object> params)throws Exception;
	/**
	 * 判断字段中文名是否有重复
	 * @param params
	 * @return
	 */
	int countComments(Map<String, Object> params)throws Exception;
	
	int saveTable(TableResource tableResource);
}
