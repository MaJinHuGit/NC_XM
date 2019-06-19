package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Jzjqxx;
import com.mtlckj.base.jqfx.domain.JzjqxxVo;
import com.mtlckj.base.jqfx.domain.Ztyp;

/**
 * 违法犯罪警情mapper
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */
public interface ZtypMapper {
	
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 警情走势图
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月30日 下午4:08:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月30日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getJqzst(Map<String,Object> map);
	List<Ztyp> getJqzstTime(Map<String,Object> map);
	
	
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 警情类别统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月30日 下午4:08:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月30日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getJqlbtjs(Map<String,Object> map);
	
	List<Ztyp> getFzType(Map<String,Object> map);
	
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 高危辖区 数量较多的前三条派出所数据
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月31日 下午6:35:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月31日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getGwqy(Map<String,Object> map);
	
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 高危区域统计图
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午10:20:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getGwqyTjt(Map<String,Object> map);
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 高危区域列表
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午9:04:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getGwqyList(Map<String,Object> map);
	
	/**
	 * 总数
	 * 分页统计
	 * @return
	 */
	Integer getGwqyListCunt(Map<String,Object> map);
	
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 高危时段统数据
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午10:20:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getGwsd(Map<String,Object> map);
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 高危时段统计图
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午10:20:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getGwsdTjt(Map<String,Object> map);
	/**
	 * 
	* @Function: ZtypMapper.java
	* @Description: 高危时段列表
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午9:04:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	List<Ztyp> getGwsdList(Map<String,Object> map);
	
	/**
	 * 总数
	 * 分页统计
	 * @return
	 */
	Integer getGwsdListCunt(Map<String,Object> map);
	
	
	
	
	
}
