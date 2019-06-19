package com.mtlckj.base.jqfx.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.domain.Jzjqxx;
import com.mtlckj.base.jqfx.domain.JzjqxxVo;


public interface JzjqxxService {
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 本周警情信息统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月18日 下午6:08:29 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月18日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getNowWeekList(Map<String, Object> params);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 上周警情信息统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月18日 下午6:09:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月18日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getLastWeekList(Map<String, Object> params);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 用于页面文字描述 警情信息统计(本周)
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月18日 下午6:09:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月18日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getNowWeekDs(Map<String, Object> params);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 用于页面文字描述 警情信息统计(上周)
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月18日 下午6:09:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月18日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getLastWeekDs(Map<String, Object> params);
	/**
	 * 
	* @Function: JzjqxxService.java
	* @Description: 去年相同周数时间段的 警情信息统计
	*
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月22日 下午3:37:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月22日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getLastYearWeekDs(Map<String, Object> params);
	
	/**
	 * 
	* @Function: JzjqxxService.java
	* @Description:  本周违法犯罪警情总和
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月22日 上午11:05:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月22日      唐青                                        v1.0.0               修改原因
	 */
	Jzjqxx getNowSum(Map<String, Object> params);
	
	/**
	 * 
	* @Function: JzjqxxService.java
	* @Description:  上周违法犯罪警情总和
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月22日 上午11:05:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月22日      唐青                                        v1.0.0               修改原因
	 */
	Jzjqxx getLastSum(Map<String, Object> params);
	
	
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description:今年每周的违法犯罪警情统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月22日 下午3:30:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月22日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getNowYearWeek(Map<String, Object> params);
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 上年每周的违法犯罪警情统计
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月22日 下午3:31:20 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月22日      唐青                                        v1.0.0               修改原因
	 */
	List<Jzjqxx> getLastYearWeek(Map<String, Object> params);
	

	/**
	 * 
	* @Function: JzjqxxService.java
	* @Description: 全区警情分类统计
	*
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月24日 上午9:59:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月24日      唐青                                        v1.0.0               修改原因
	 */
	List<Map<String, String> > getQqjq(Map<String, Object> params);
	
	/**
	 * 
	* @Function: JzjqxxMapper.java
	* @Description: 全区各辖区违法警情累计同比统计表
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月26日 上午11:45:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月26日      唐青                                        v1.0.0               修改原因
	 */
	List<Map<String, String>> getQqjqTb(Map<String,Object> map  );
	/**
	 * 
	* @Function: JzjqxxService.java
	* @Description:  “三盗两抢一诈骗“警情及其他警情情况
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月26日 下午4:38:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月26日      唐青                                        v1.0.0               修改原因
	 */
	List<Map<String, String>> getDqzTbHb(Map<String,Object> map  );
	
}
