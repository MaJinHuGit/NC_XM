package com.mtlckj.base.jqfx.controller;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.domain.Wffzjq;
import com.mtlckj.base.jqfx.domain.Ztyp;
import com.mtlckj.base.jqfx.service.ZtypService;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.R;


/**
 * 违法犯罪警情Controller
 * 
 * @author liangxiao
 * @date 2018年10月18日 上午10:02:20
 */
@RequestMapping("/jqfx/ztyp")
@Controller
public class ZtypController {

	@Autowired
	private ZtypService ztypService;
	
	/**
	 * 
	 * @Function: JzjqxxController.java
	 * @Description: 本周违法犯罪警情对比图
	 *
	 * @param:@return
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: 唐青
	 * @date: 2018年10月19日 下午5:53:56
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月19日
	 *        唐青 v1.0.0 修改原因
	 */

	@ResponseBody
	@GetMapping("/getJqzst")
	public R getJqzst(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Ztyp> nowWeek = ztypService.getJqzst(params);
		List<Ztyp> times = ztypService.getJqzstTime(params);
		return R.ok().put("nowWeek", nowWeek).put("times", times);
	}
	/**
	 * 
	* @Function: ZtypController.java
	* @Description: 警情类别统计
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月30日 下午4:42:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月30日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getJqlbtj")
	public R getJqlbtj(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Map<String,String>> lqlbtj = ztypService.getJqlbtj(params);
		return R.ok().put("lqlbtj", lqlbtj);
	}

	/**
	 * 
	* @Function: ZtypController.java
	* @Description:  高危辖区 数量较多的前三条派出所数据
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午9:10:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getGwqy")
	public R getGwqy(@RequestParam Map<String, Object> params) {
		try {
  			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
			if (params.get("fstype") != null && !"".equals(params.get("fstype"))) {
				String fstype = URLDecoder.decode((String) params.get("fstype"), "UTF-8");
				params.put("fstype", fstype);
			} else {
				params.put("fstype", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Ztyp> gwqy = ztypService.getGwqy(params);
		return R.ok().put("gwqy", gwqy);
	}
	
	/**
	 * 
	* @Function: ZtypController.java
	* @Description: 高危区域统计图
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午10:31:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getGwqyTjt")
	public R getGwqyTjt(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
			if (params.get("fstype") != null && !"".equals(params.get("fstype"))) {
				String fstype = URLDecoder.decode((String) params.get("fstype"), "UTF-8");
				params.put("fstype", fstype);
			} else {
				params.put("fstype", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Ztyp> gwqyTjt = ztypService.getGwqyTjt(params);
		return R.ok().put("gwqyTjt", gwqyTjt);
	}
	/**
	 * 
	* @Function: ZtypController.java
	* @Description: 高危区域列表
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午9:10:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getGwqyList")
	public PageUtils getList(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
			
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
	    		params.put("search", search);
	    	}
			if (params.get("fstype") != null && !"".equals(params.get("fstype"))) {
				String fstype = URLDecoder.decode((String) params.get("fstype"), "UTF-8");
				params.put("fstype", fstype);
			} else {
				params.put("fstype", "-1");
			}
			if(params.get("jgmc") != null && !"".equals(params.get("jgmc"))) {
				String jgmc = URLDecoder.decode((String) params.get("jgmc"),"UTF-8");
	    		params.put("jgmc", jgmc);
	    	}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Ztyp> list = ztypService.getGwqyList(params);
		int total = ztypService.getGwqyListCunt(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	/**
	 * 
	* @Function: ZtypController.java
	* @Description:  高危辖区 数量较多的前三条派出所数据
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午9:10:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getGwsd")
	public R getGwsd(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
			if (params.get("fstype") != null && !"".equals(params.get("fstype"))) {
				String fstype = URLDecoder.decode((String) params.get("fstype"), "UTF-8");
				params.put("fstype", fstype);
			} else {
				params.put("fstype", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Ztyp> gwsd = ztypService.getGwsd(params);
		return R.ok().put("gwsd", gwsd);
	}
	
	/**
	 * 
	* @Function: ZtypController.java
	* @Description: 高危区域统计图
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午10:31:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getGwsdTjt")
	public R getGwsdTjt(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
			if (params.get("fstype") != null && !"".equals(params.get("fstype"))) {
				String fstype = URLDecoder.decode((String) params.get("fstype"), "UTF-8");
				params.put("fstype", fstype);
			} else {
				params.put("fstype", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Ztyp> gwsdTjt = ztypService.getGwsdTjt(params);
		return R.ok().put("gwsdTjt", gwsdTjt);
	}
	/**
	 * 
	* @Function: ZtypController.java
	* @Description: 高危区域列表
	*
	* @param:@param params
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年11月1日 上午9:10:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年11月1日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getGwsdList")
	public PageUtils getGwsdList(@RequestParam Map<String, Object> params) {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
			
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
	    		params.put("search", search);
	    	}
			if(params.get("jgmc") != null && !"".equals(params.get("jgmc"))) {
				String jgmc = URLDecoder.decode((String) params.get("jgmc"),"UTF-8");
	    		params.put("jgmc", jgmc);
	    	}
			if (params.get("fstype") != null && !"".equals(params.get("fstype"))) {
				String fstype = URLDecoder.decode((String) params.get("fstype"), "UTF-8");
				params.put("fstype", fstype);
			} else {
				params.put("fstype", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Ztyp> list = ztypService.getGwsdList(params);
		int total = ztypService.getGwsdListCunt(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
}

