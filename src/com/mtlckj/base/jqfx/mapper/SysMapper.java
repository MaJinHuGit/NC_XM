package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mtlckj.base.jqfx.domain.Fields;
import com.mtlckj.base.jqfx.domain.Sys;
import com.mtlckj.base.jqfx.domain.TableResource;
import com.mtlckj.base.jqfx.domain.Wffzjq;


/**
 * 本周警情mapper
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */
public interface SysMapper {
	
	/**
	 * 创建表和字段
	 * @param params  tableName  表名     field 字段
	 * @return
	 */
	int createNewTable(Map<String, Object> params);
	/**
	 * 获取自建表的数量
	 * @return
	 */
	String getZjTableCount();
	/**
	 * 表注释
	 * @param params
	 * @return
	 */
	int updateTableComment(Map<String, Object> params);
	/**
	 * 字段注释
	 * @param params
	 * @return
	 */
	int updateFieldComment(Map<String, Object> params);
	/**
	 * 查询所有的表名和注释
	 * @return
	 */
	List<Sys> getTable(Map<String, Object> params);
	/**
	 * 数据库中所有表的数目
	 * @return
	 */
	int count();
	
	int countSj(@Param("tablename")String tablename);
	/**
	 * 查询表中的字段，类型和 注释
	 * @return
	 */
	List<Fields> getFields(Map<String, Object> params);
	
	List<Fields> getFieldNoId(Map<String, Object> params);
	/**
	 * 表中的所以的字段总数
	 * <p>TODO</p>
	 * <p>@author 唐青</p>
	 * <p>@date 2019年1月10日 上午10:28:33</p>
	 * @param params tablename 表名
	 * @return int
	 */
	int countFields(Map<String, Object> params);
	/**
	 * 导入数据
	 * @param params
	 * @return
	 */
	int save(Map<String, Object> params);
	/**
	 * 修改字段类型
	 * @param params
	 * @return
	 */
	int updateFieldType(Map<String, Object> params);
	/**
	 * 添加字段 
	 * @param params
	 * @return
	 */
	int addField(Map<String, Object> params);
	/**
	 *  删除字段 
	 * @param params
	 * @return
	 */
	int deleteField(Map<String, Object> params);
	/**
	 * 自定义字段名称后缀数字的最大值 
	 * @param params
	 * @return
	 */
	String getZjFieldCount(Map<String, Object> params);
	/**
	 * 判断字段中文名是否有重复
	 * @param params
	 * @return
	 */
	int countComments(Map<String, Object> params);
	
	
	int saveTable(TableResource tableResource);
}
