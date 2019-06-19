package com.mtlckj.base.jqfx.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.domain.Jzjqxx;
import com.mtlckj.base.jqfx.domain.JzjqxxVo;
import com.mtlckj.base.jqfx.service.JzjqxxService;
import com.mtlckj.base.system.utils.R;

/**
 * 违法犯罪警情Controller
 * 
 * @author liangxiao
 * @date 2018年10月18日 上午10:02:20
 */
@RequestMapping("/jqfx/jzjqxx")
@Controller
public class JzjqxxController {

	@Autowired
	private JzjqxxService jzjqxxService;
	
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
	@GetMapping("/listByWeek")
	public R getWeekList(@RequestParam Map<String, Object> params) {
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
		// 本周数据统计
		List<Jzjqxx> nowWeek = jzjqxxService.getNowWeekList(params);
		// 上周数据统计
		List<Jzjqxx> lastWeek = jzjqxxService.getLastWeekList(params);
		return R.ok().put("nowWeek", nowWeek).put("lastWeek", lastWeek);
	}

	/**
	 * 
	 * @Function: JzjqxxController.java
	 * @Description: 文字描述本周违法犯罪警情
	 *
	 * @param:@return
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: 唐青
	 * @date: 2018年10月19日 下午5:54:18
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月19日
	 *        唐青 v1.0.0 修改原因
	 */
	@ResponseBody
	@GetMapping("/dsByWeek")
	public R getWeekds(@RequestParam Map<String, Object> params) {
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
		// startTime,endTime
		// 本周数据统计
		List<Jzjqxx> nowWeek = jzjqxxService.getNowWeekDs(params);
		// 上周数据统计
		List<Jzjqxx> lastWeek = jzjqxxService.getLastWeekDs(params);

		List<Jzjqxx> lastYearWeek = jzjqxxService.getLastYearWeekDs(params);
		// 本周违法犯罪警情总和
		Jzjqxx nowSum = jzjqxxService.getNowSum(params);
		// 上周违法犯罪警情总和
		Jzjqxx latSum = jzjqxxService.getLastSum(params);

		return R.ok().put("nowWeek", nowWeek).put("lastWeek", lastWeek).put("lastYearWeek", lastYearWeek)
				.put("nowSum", nowSum).put("latSum", latSum);
	}

	/**
	 * 
	 * @Function: JzjqxxController.java
	 * @Description:今年和去年每周的 违法犯罪警情趋势对比
	 *
	 * @param:@return
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: 唐青
	 * @date: 2018年10月22日 下午4:10:35
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月22日
	 *        唐青 v1.0.0 修改原因
	 */
	@ResponseBody
	@GetMapping("/getYearWeek")
	public R getYearWeek(@RequestParam Map<String, Object> params) {
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
		// 今年每周违法犯罪警情数据统计
		List<Jzjqxx> nowYearWeek = jzjqxxService.getNowYearWeek(params);
		// 上年每周违法犯罪警情数据统计
		List<Jzjqxx> lastYearWeek = jzjqxxService.getLastYearWeek(params);
		return R.ok().put("nowYearWeek", nowYearWeek).put("lastYearWeek", lastYearWeek);
	}

	/**
	 * 
	 * @Function: JzjqxxController.java
	 * @Description: 全区各所辖区警情分类统计
	 *
	 * @param:@return
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: 唐青
	 * @date: 2018年10月24日 上午11:14:39
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月24日
	 *        唐青 v1.0.0 修改原因
	 */
	@ResponseBody
	@GetMapping("/getQqjq")
	public R getQqjq(@RequestParam Map<String, Object> params) {
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
		// 警情分类统计
		List<Map<String, String>> list = jzjqxxService.getQqjq(params);
		return R.ok().put("list", list);
	}
	/**
	 * 
	* @Function: JzjqxxController.java
	* @Description: 全区各辖区违法警情累计同比统计表
	*
	* @param:@param map
	* @param:@return
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: 唐青
	* @date: 2018年10月26日 下午3:19:13 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年10月26日      唐青                                        v1.0.0               修改原因
	 */
	@ResponseBody
	@GetMapping("/getQqjqTb")
	public  R getQqjqTb(@RequestParam Map<String, Object> params) {
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
		// 全区各辖区违法警情累计同比
		List<Map<String, String>> list = jzjqxxService.getQqjqTb(params);
		return R.ok().put("list", list);
	}
	
	@ResponseBody
	@GetMapping("/getDqzTbHb")
	public R getDqzTbHb(Map<String, Object> params) {
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
		// ”三盗两抢一诈骗“警情及其他警情情况
		List<Map<String, String>> list = jzjqxxService.getDqzTbHb(params);
		
		return R.ok().put("list", list);
	}
}

