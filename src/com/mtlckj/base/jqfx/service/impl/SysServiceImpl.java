package com.mtlckj.base.jqfx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.Fields;
import com.mtlckj.base.jqfx.domain.Sys;
import com.mtlckj.base.jqfx.domain.TableResource;
import com.mtlckj.base.jqfx.mapper.SysMapper;
import com.mtlckj.base.jqfx.service.SysService;
@Service
public class SysServiceImpl implements SysService{
	@Autowired
	SysMapper sysMapper ; 
	/**
	 * 创建表和字段
	 */
	@Override
	public int createNewTable(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		//String fiele
		return sysMapper.createNewTable(params);
	}
	
	@Override
	public int updateTableComment(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.updateTableComment(params);
	}

	@Override
	public int updateFieldComment(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.updateFieldComment(params);
	}
	
	/**
	 * 获取自建表的数量
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getZjTableCount() throws Exception{
		return sysMapper.getZjTableCount();
	}
	/**
	 * 查询所有的表名和注释
	 */
	@Override
	public List<Sys> getTable(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		List<Sys> list = sysMapper.getTable(params);
		for (int i = 0; i < list.size(); i++) {
			String tablename = list.get(i).getTablename();
			String count = String.valueOf(sysMapper.countSj(tablename));
			list.get(i).setCount(count);
		}
		return list;
	}

	/**
	 * 数据库中所有表的数目
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return sysMapper.count();
	}

	/**
	 * 查询表中的字段，类型和 注释
	 */
	@Override
	public List<Fields> getFields(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		List<Fields> list =  sysMapper.getFields(params);
		for (int i = 0; i < list.size(); i++) {
			//把字段类型转为中文描述
			String type = getType(list.get(i).getDatatype());
			list.get(i).setDatatype(type);
		}
		return list;
	}
	@Override
	public List<Fields> getFieldNoId(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		List<Fields> list =  sysMapper.getFieldNoId(params);
		for (int i = 0; i < list.size(); i++) {
			//把字段类型转为中文描述
			String type = getType(list.get(i).getDatatype());
			list.get(i).setDatatype(type);
		}
		return list;
	}
	/**
	 * 表中的所以的字段总数
	 */
	@Override
	public int countFields(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.countFields(params);
	}

	@Override
	public int save(Map<String, Object> params)throws Exception{
		return sysMapper.save(params);
	}
	/**
	 * 把数据库的字段类型转换为中文表达
	 * <p>TODO</p>
	 * <p>@author </p>
	 * <p>@date 2019年1月10日 上午10:32:26</p>
	 * @param str
	 * @return String
	 */
	private String getType(String str){
		str = str.toUpperCase();
		String type = null;
		if("VARCHAR2".equals(str)){
			type = "字符串";
		}else if("NUMBER".equals(str)){
			type = "数据型";
		}else if("DATE".equals(str)){
			type = "时间";
		}
		return type;
	}

	/**
	 * 修改字段类型
	 */
	@Override
	public int updateFieldType(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.updateFieldType(params);
	}

	/**
	 * 添加字段
	 */
	@Override
	public int addField(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.addField(params);
	}

	/**
	 * 删除字段
	 */
	@Override
	public int deleteField(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.deleteField(params);
	}

	/**
	 * 自定义字段名称后缀数字的最大值
	 */
	@Override
	public String getZjFieldCount(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.getZjFieldCount(params);
	}

	/**
	 * 判断字段中文名是否有重复
	 */
	@Override
	public int countComments(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sysMapper.countComments(params);
	}

	@Override
	public int saveTable(TableResource tableResource) {
		// TODO Auto-generated method stub
		return sysMapper.saveTable(tableResource);
	}

	

}
