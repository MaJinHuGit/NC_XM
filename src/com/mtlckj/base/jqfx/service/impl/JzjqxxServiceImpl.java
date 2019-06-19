package com.mtlckj.base.jqfx.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.Jzjqxx;
import com.mtlckj.base.jqfx.domain.JzjqxxVo;
import com.mtlckj.base.jqfx.mapper.JzjqxxMapper;
import com.mtlckj.base.jqfx.mapper.BzjqMapper;
import com.mtlckj.base.jqfx.service.JzjqxxService;
import com.mtlckj.base.jqfx.service.BzjqService;
import com.mtlckj.base.jqfx.utils.DateUtils;

/**
 * 违法犯罪警情impl
 * 
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */

@Service
public class JzjqxxServiceImpl implements JzjqxxService {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private JzjqxxMapper zjqxxDao;

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月18日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getNowWeekList(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisYearStart()));
				map.put("endTime", sdf.format(DateUtils.getThisYearEnd()));
			} else if ("bj".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisQuarterStart()));
				map.put("endTime", sdf.format(DateUtils.getThisQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisMonthStart()));
				map.put("endTime", sdf.format(DateUtils.getThisMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
				map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
			} else {
				map.put("startTime", params.get("startTime"));
				map.put("endTime", params.get("endTime"));
			}
		}

		return zjqxxDao.getWeekList(map);
	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月18日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getLastWeekList(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastYearStartTime()));
				map.put("endTime", sdf.format(DateUtils.getLastYearEndTime()));
			} else if ("bj".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastQuarterStart()));
				map.put("endTime", sdf.format(DateUtils.getLastQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastMonthStart()));
				map.put("endTime", sdf.format(DateUtils.getLastMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
				map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				map.put("startTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				map.put("endTime", getLastTimeForRingEnd((String) params.get("endTime"), day));
			}
		}
		return zjqxxDao.getWeekList(map);
	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月18日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getNowWeekDs(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisYearStart()));
				map.put("endTime", sdf.format(DateUtils.getThisYearEnd()));
			} else if ("bj".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisQuarterStart()));
				map.put("endTime", sdf.format(DateUtils.getThisQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisMonthStart()));
				map.put("endTime", sdf.format(DateUtils.getThisMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
				map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
			} else {
				map.put("startTime", params.get("startTime"));
				map.put("endTime", params.get("endTime"));
			}
		}
		return zjqxxDao.getWeekDs(map);
	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月18日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getLastWeekDs(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastYearStartTime()));
				map.put("endTime", sdf.format(DateUtils.getLastYearEndTime()));
			} else if ("bj".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastQuarterStart()));
				map.put("endTime", sdf.format(DateUtils.getLastQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastMonthStart()));
				map.put("endTime", sdf.format(DateUtils.getLastMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
				map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				map.put("startTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				map.put("endTime", getLastTimeForRingEnd((String) params.get("endTime"), day));
			}
		}
		return zjqxxDao.getWeekDs(map);
	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月22日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getLastYearWeekDs(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastYearStartTime()));
				map.put("endTime", sdf.format(DateUtils.getLastYearEndTime()));
			} else if ("bj".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(getLastYearQuarterStart()));
				map.put("endTime", sdf.format(getLastYearQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(getLastYearMonthStart()));
				map.put("endTime", sdf.format(getLastYearMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastYearWeekStartByNumber()));
				map.put("endTime", sdf.format(DateUtils.getLastYearWeekEndByNumber()));
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				map.put("startTime", getLastTimeForWithStart((String) params.get("startTime"), day));
				map.put("endTime", getLastTimeForWithEnd((String) params.get("endTime"), day));
			}
		}
		return zjqxxDao.getWeekDs(map);
	}

	/**
	 * 
	 * @Function: JzjqxxService.java
	 * @Description: 本周违法犯罪警情总和
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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月22日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public Jzjqxx getNowSum(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
				if ("-1".equals(params.get("startTime"))) {

					map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
					map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
				} else {
					if ("bn".equals(params.get("startTime"))) {
						map.put("startTime", sdf.format(DateUtils.getThisYearStart()));
						map.put("endTime", sdf.format(DateUtils.getThisYearEnd()));
					} else if ("bj".equals(params.get("startTime"))) {
						map.put("startTime", sdf.format(DateUtils.getThisQuarterStart()));
						map.put("endTime", sdf.format(DateUtils.getThisQuarterEnd()));
					} else if ("by".equals(params.get("startTime"))) {
						map.put("startTime", sdf.format(DateUtils.getThisMonthStart()));
						map.put("endTime", sdf.format(DateUtils.getThisMonthEnd()));
					} else if ("bz".equals(params.get("startTime"))) {
						map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
						map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
					} else {
						map.put("startTime", params.get("startTime"));
						map.put("endTime", params.get("endTime"));
					}
				}
		return zjqxxDao.getSum(map);
	}

	/**
	 * 
	 * @Function: JzjqxxService.java
	 * @Description: 上周违法犯罪警情总和
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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月22日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public Jzjqxx getLastSum(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastYearStartTime()));
				map.put("endTime", sdf.format(DateUtils.getLastYearEndTime()));
			} else if ("bj".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastQuarterStart()));
				map.put("endTime", sdf.format(DateUtils.getLastQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastMonthStart()));
				map.put("endTime", sdf.format(DateUtils.getLastMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				map.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
				map.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				map.put("startTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				map.put("endTime", getLastTimeForRingEnd((String) params.get("endTime"), day));
			}
		}
		return zjqxxDao.getSum(map);
	}

	/**
	 * 
	 * @see com.mtlckj.base.jqfx.service.JzjqxxService#getNowYearWeek()
	 * @Function: JzjqxxServiceImpl.java
	 * @Description: 今年每周的违法犯罪警情统计
	 *
	 * @return
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: tq
	 * @date: 2018年10月22日 下午3:35:42
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月22日
	 *        tq v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getNowYearWeek(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 今年开始时间
		String startLastYear = sdf.format(DateUtils.getThisYearStart());
		// 今年结束时间
		String endLastYear = sdf.format(DateUtils.getThisYearEnd());
		map.put("startTime", startLastYear);
		map.put("endTime", endLastYear);
		return zjqxxDao.getNowYearWeek(map);
	}

	/**
	 * 
	 * @see com.mtlckj.base.jqfx.service.JzjqxxService#getLastYearWeek()
	 * @Function: JzjqxxServiceImpl.java
	 * @Description: 上年每周的违法犯罪警情统计
	 *
	 * @return
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: tq
	 * @date: 2018年10月22日 下午3:35:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月22日
	 *        tq v1.0.0 修改原因
	 */
	@Override
	public List<Jzjqxx> getLastYearWeek(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 上年开始时间
		String startLastYear = sdf.format(DateUtils.getLastYearStartTime());
		// 上年结束时间
		String endLastYear = sdf.format(DateUtils.getLastYearWeekEndTime());
		map.put("startTime", startLastYear);
		map.put("endTime", endLastYear);
		return zjqxxDao.getLastYearWeek(map);
	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月24日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Map<String, String>> getQqjq(Map<String, Object> params) {
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		Map<String, String> mapp = null;
		// 总量统计
		List<JzjqxxVo> listSum = getQqjqSum(params);
		for (int i = 0, len = listSum.size(); i < len; i++) {
			mapp = new HashMap<String, String>();
			// 获取派出所名称
			String name = listSum.get(i).getName();
			mapp.put("name", name);
			// 上周对比上上周
			mapp.put("cuntlast", getPkInfo(listSum.get(i).getCuntlastt(), listSum.get(i).getCuntlast()));
			mapp.put("pklast", getPkNumber(listSum.get(i).getCuntlastt(), listSum.get(i).getCuntlast()));
			// 本周对比上周
			mapp.put("cuntnow", getPkInfo(listSum.get(i).getCuntlast(), listSum.get(i).getCuntnow()));
			mapp.put("pknow", getPkNumber(listSum.get(i).getCuntlast(), listSum.get(i).getCuntnow()));

			mapp.put("cuntlastt", listSum.get(i).getCuntlastt());
			mapp.put("cuntyear", listSum.get(i).getCuntyear());

			// Map<String, Object> map = new HashMap<String, Object>();
			params.put("name", name);

			// 本周派出所中下面的违法犯罪分类，
			List<JzjqxxVo> listFl = getQqjqFl(params);

			for (int j = 0, len2 = listFl.size(); j < len2; j++) {
				if ("盗窃汽车".equals(listFl.get(j).getCt())) {
					mapp.put("dqqc", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkdqqc", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				} else if ("盗窃摩托车".equals(listFl.get(j).getCt())) {
					mapp.put("dqmtc", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkdqmtc", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));

				}
				if ("抢夺".equals(listFl.get(j).getCt())) {
					mapp.put("qd", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkqd", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("诈骗".equals(listFl.get(j).getCt())) {
					mapp.put("zp", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkzp", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("抢劫".equals(listFl.get(j).getCt())) {
					mapp.put("qj", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkqj", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("入室盗窃".equals(listFl.get(j).getCt())) {
					mapp.put("rsdq", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkrsdq", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("扒窃".equals(listFl.get(j).getCt())) {
					mapp.put("pq", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkpq", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("侵财".equals(listFl.get(j).getCt())) {
					mapp.put("qc", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkqc", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("非侵财".equals(listFl.get(j).getCt())) {
					mapp.put("fqc", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkfqc", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("盗窃车内财物".equals(listFl.get(j).getCt())) {
					mapp.put("dqcncw", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkdqcncw", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				if ("其他盗窃".equals(listFl.get(j).getCt())) {
					mapp.put("qtdq", getPkInfo(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
					mapp.put("pkqtdq", getPkNumber(listFl.get(j).getCuntlast(), listFl.get(j).getCuntnow()));
				}
				// listSum.get(i).s
			}
			listMap.add(mapp);

		}
		return listMap;
	}

	// 对比上次上升还是下降
	private String getPkInfo(String last, String now) {
		String strPk = null;
		if (Integer.parseInt(last) > Integer.parseInt(now)) {
			strPk = now + "↓";
		} else if (Integer.parseInt(last) < Integer.parseInt(now)) {
			strPk = now + "↑";
		} else {
			strPk = now;
		}
		return strPk;
	}

	// 对比上升或下降的条数
	private String getPkNumber(String last, String now) {
		String strPkNum = null;
		if (Integer.parseInt(last) > Integer.parseInt(now)) {
			strPkNum = "下降" + (Integer.parseInt(last) - Integer.parseInt(now));
		} else if (Integer.parseInt(last) < Integer.parseInt(now)) {
			strPkNum = "上升" + (Integer.parseInt(now) - Integer.parseInt(last));
		} else {
			strPkNum = "持平";
		}
		return strPkNum;
	}

	// 合计本周，上周，上年统计的违法犯罪的数据
	private List<JzjqxxVo> getQqjqSum(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if ("-1".equals(params.get("startTime"))) {
			// 本周开始时间
			map.put("starNowTime", sdf.format(DateUtils.getThisWeekStart()));
			// 本周结束时间
			map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()));
			// 上周开始时间
			map.put("starLastTime", sdf.format(DateUtils.getLastWeekStart()));
			// 上周结束时间
			map.put("endLastTime", sdf.format(DateUtils.getLastWeekEnd()));
			// 上上周开始时间
			map.put("starLasttTime", sdf.format(DateUtils.getBeforeLastWeekStart()));
			// 上上周结束时间
			map.put("endLasttTime", sdf.format(DateUtils.getBeforeLastWeekEnd()));
			// 上年同周开始时间
			map.put("startYearTime", sdf.format(DateUtils.getLastYearWeekStartByNumber()));
			// 上年同周结束时间
			map.put("endYearTime", sdf.format(DateUtils.getLastYearWeekEndByNumber()));
		} else {
			if ("bn".equals(params.get("startTime"))) {
				// 本年开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisYearStart()));
				// 本年结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisYearEnd()));
				// 上年开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastYearStartTime()));
				// 上年结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastYearEndTime()));
				// 上上年开始时间
				map.put("starLasttTime", sdf.format(getBeforeLastYearStartTime()));
				// 上上年结束时间
				map.put("endLasttTime", sdf.format(getBeforeLastYearEndTime()));
				// 上年同周开始时间
				map.put("startYearTime", sdf.format(DateUtils.getLastYearStartTime()));
				// 上年同周结束时间
				map.put("endYearTime", sdf.format(DateUtils.getLastYearEndTime()));
			
				
			} else if ("bj".equals(params.get("startTime"))) {
				
				// 本季度开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisQuarterStart()));
				// 本季度结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisQuarterEnd()));
				// 上季度开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastQuarterStart()));
				// 上季度结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastQuarterEnd()));
				// 上上季度开始时间
				map.put("starLasttTime", sdf.format(getBeforeLastQuarterStart()));
				// 上上季度结束时间
				map.put("endLasttTime", sdf.format(getBeforeLastQuarterEnd()));
				// 上年同季度开始时间
				map.put("startYearTime", sdf.format(getLastYearQuarterStart()));
				// 上年同季度结束时间
				map.put("endYearTime", sdf.format(getLastYearQuarterEnd()));
			} else if ("by".equals(params.get("startTime"))) {
				
				// 本月开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisMonthStart()));
				// 本月结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisMonthEnd()));
				// 上月开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastMonthStart()));
				// 上月结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastMonthEnd()));
				// 上上月开始时间
				map.put("starLasttTime", sdf.format(getBeforeLastMonthStart()));
				// 上上月结束时间
				map.put("endLasttTime", sdf.format(getBeforeLastMonthEnd()));
				// 上年同月开始时间
				map.put("startYearTime", sdf.format(getLastYearMonthStart()));
				// 上年同月结束时间
				map.put("endYearTime", sdf.format(getLastYearMonthEnd()));
			} else if ("bz".equals(params.get("startTime"))) {
				// 本周开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisWeekStart()));
				// 本周结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()));
				// 上周开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastWeekStart()));
				// 上周结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastWeekEnd()));
				// 上上周开始时间
				map.put("starLasttTime", sdf.format(DateUtils.getBeforeLastWeekStart()));
				// 上上周结束时间
				map.put("endLasttTime", sdf.format(DateUtils.getBeforeLastWeekEnd()));
				// 上年同周开始时间
				map.put("startYearTime", sdf.format(DateUtils.getLastYearWeekStartByNumber()));
				// 上年同周结束时间
				map.put("endYearTime", sdf.format(DateUtils.getLastYearWeekEndByNumber()));
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
			
				map.put("starNowTime", (String) params.get("startTime"));
			
				map.put("endNowTime", (String) params.get("endTime"));
				
				map.put("starLastTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				
				map.put("endLastTime", getLastTimeForRingEnd((String) params.get("endTime"), day));
				
			
				map.put("starLasttTime",  getLastTimeForRingStart((String) map.get("starLastTime"), day));
				
				map.put("endLasttTime",  getLastTimeForRingEnd((String) map.get("endLastTime"), day));
			
				map.put("startYearTime", getLastTimeForWithStart( (String) params.get("startTime"),day));
				
				map.put("endYearTime", getLastTimeForWithStart( (String) params.get("endTime"),day));
				
			}
		}
		
/*		// 本周开始时间
		String startNowWeek = sdf.format(DateUtils.getThisWeekStart());
		// 本周结束时间
		String endNowWeek = sdf.format(DateUtils.getThisWeekEnd());
		// 上周开始时间
		String startLastWeek = sdf.format(DateUtils.getLastWeekStart());
		// 上周结束时间
		String endLastWeek = sdf.format(DateUtils.getLastWeekEnd());
		// 上上周开始时间
		String startLasttWeek = sdf.format(DateUtils.getBeforeLastWeekStart());
		// 上上周结束时间
		String endLasttWeek = sdf.format(DateUtils.getBeforeLastWeekEnd());
		// 上年同周开始时间
		String startLastYearWeek = sdf.format(DateUtils.getLastYearWeekStartByNumber());
		// 上年同周结束时间
		String endLastYearWeek = sdf.format(DateUtils.getLastYearWeekEndByNumber());

	
		map.put("starNowTime", startNowWeek);
		map.put("endNowTime", endNowWeek);
		map.put("starLastTime", startLastWeek);
		map.put("endLastTime", endLastWeek);
		map.put("starLasttTime", startLasttWeek);
		map.put("endLasttTime", endLasttWeek);
		map.put("startYearTime", startLastYearWeek);
		map.put("endYearTime", endLastYearWeek);*/

		return zjqxxDao.getQqjqSum(map);
	}

	// 本周，上周 分类统计违法犯罪的数据
	private List<JzjqxxVo> getQqjqFl(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", params.get("name"));
		if ("-1".equals(params.get("startTime"))) {
			// 本周开始时间
			map.put("starNowTime", sdf.format(DateUtils.getThisWeekStart()));
			// 本周结束时间
			map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()));
			// 上周开始时间
			map.put("starLastTime", sdf.format(DateUtils.getLastWeekStart()));
			// 上周结束时间
			map.put("endLastTime", sdf.format(DateUtils.getLastWeekEnd()));
			
		} else {
			if ("bn".equals(params.get("startTime"))) {
				// 本年开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisYearStart()));
				// 本年结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisYearEnd()));
				// 上年开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastYearStartTime()));
				// 上年结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastYearEndTime()));
				
			
				
			} else if ("bj".equals(params.get("startTime"))) {
				
				// 本季度开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisQuarterStart()));
				// 本季度结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisQuarterEnd()));
				// 上季度开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastQuarterStart()));
				// 上季度结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastQuarterEnd()));
				
			} else if ("by".equals(params.get("startTime"))) {
				
				// 本月开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisMonthStart()));
				// 本月结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisMonthEnd()));
				// 上月开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastMonthStart()));
				// 上月结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastMonthEnd()));
				
			} else if ("bz".equals(params.get("startTime"))) {
				// 本周开始时间
				map.put("starNowTime", sdf.format(DateUtils.getThisWeekStart()));
				// 本周结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()));
				// 上周开始时间
				map.put("starLastTime", sdf.format(DateUtils.getLastWeekStart()));
				// 上周结束时间
				map.put("endLastTime", sdf.format(DateUtils.getLastWeekEnd()));
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				// 本周开始时间
				map.put("starNowTime", (String) params.get("startTime"));
				// 本周结束时间
				map.put("endNowTime", (String) params.get("endTime"));
				// 上周开始时间
				map.put("starLastTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				// 上周结束时间
				map.put("endLastTime", getLastTimeForRingEnd((String) params.get("endTime"), day));

			}
		}
		
		/*// 本周开始时间
		String startNowWeek = sdf.format(DateUtils.getThisWeekStart());
		// 本周结束时间
		String endNowWeek = sdf.format(DateUtils.getThisWeekEnd());
		// 上周开始时间
		String startLastWeek = sdf.format(DateUtils.getLastWeekStart());
		// 上周结束时间
		String endLastWeek = sdf.format(DateUtils.getLastWeekEnd());
		// Map<String, Object> mapNowWeek = new HashMap<String, Object>();
		map.put("starNowTime", startNowWeek);
		map.put("endNowTime", endNowWeek);
		map.put("starLastTime", startLastWeek);
		map.put("endLastTime", endLastWeek);*/

		return zjqxxDao.getQqjq(map);

	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月26日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Map<String, String>> getQqjqTb(Map<String, Object> map) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 本年开始时间
		String startNow = sdf.format(DateUtils.getThisYearStart());
		// 至今结束时间
		String endNow = sdf.format(new Date());
		// 上年开始时间
		String startLast = sdf.format(DateUtils.getLastYearStartTime());
		// 上年至今结束时间
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) - 1);
		Date date = curr.getTime();
		String endLast = sdf.format(date);

		map.put("starNowTime", startNow);
		map.put("endNowTime", endNow);
		map.put("starLastTime", startLast);
		map.put("endLastTime", endLast);
		List<JzjqxxVo> jzjqxx = zjqxxDao.getQqjqTb(map);
		Map<String, String> mapp = null;
		for (int i = 0; i < jzjqxx.size(); i++) {
			mapp = new HashMap<String, String>();
			mapp.put("name", jzjqxx.get(i).getName());
			mapp.put("cuntnow", jzjqxx.get(i).getCuntnow());
			mapp.put("cuntlast", jzjqxx.get(i).getCuntlast());
			mapp.put("tb", getTbHb(jzjqxx.get(i).getCuntlast(), jzjqxx.get(i).getCuntnow()));
			list.add(mapp);
		}
		return list;
	}

	/**
	 * 
	 * @Function: JzjqxxService.java
	 * @Description: “三盗两抢一诈骗“警情及其他警情情况
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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月26日
	 *        唐青 v1.0.0 修改原因
	 */
	public List<Map<String, String>> getDqzTbHb(Map<String, Object> map) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> mapp = null;
		// 本周和上周的三盗两抢一诈骗
		List<JzjqxxVo> jzjqxxWeek = getgetDqzTbHbWeek(map);
		// 今年和上年的三盗两抢一诈骗
		List<JzjqxxVo> jzjqxxYear = getgetDqzTbHbYear(map);
		for (int i = 0; i < jzjqxxWeek.size(); i++) {
			// 类型相同的存放到一个map中
			mapp = new HashMap<String, String>();
			for (int j = 0; j < jzjqxxYear.size(); j++) {
				if (jzjqxxWeek.get(i).getCt().equals(jzjqxxYear.get(j).getCt())) {
					// 类型名称
					mapp.put("name", jzjqxxWeek.get(i).getCt());
					// 本周统计
					mapp.put("cuntnowweek", jzjqxxWeek.get(i).getCuntnow());
					// 上周统计
					mapp.put("cuntlastweek", jzjqxxWeek.get(i).getCuntlast());
					// 环比
					mapp.put("hb", getTbHb(jzjqxxWeek.get(i).getCuntlast(), jzjqxxWeek.get(i).getCuntnow()));
					// 今年统计
					mapp.put("cuntnowyear", jzjqxxYear.get(j).getCuntnow());
					// 去年统计
					mapp.put("cuntlastyear", jzjqxxYear.get(j).getCuntlast());
					// 同比
					mapp.put("tb", getTbHb(jzjqxxYear.get(j).getCuntlast(), jzjqxxYear.get(j).getCuntnow()));
					list.add(mapp);
				}
			}
		}
		return list;
	}

	// 本周和上周 “三盗两抢一诈骗“警情及其他警情情况
	private List<JzjqxxVo> getgetDqzTbHbWeek(Map<String, Object> map) {
		// 本周开始时间
		String startNowWeek = sdf.format(DateUtils.getThisWeekStart());
		// 本周结束时间
		String endNowWeek = sdf.format(DateUtils.getThisWeekEnd());
		// 上周开始时间
		String startLastWeek = sdf.format(DateUtils.getLastWeekStart());
		// 上周结束时间
		String endLastWeek = sdf.format(DateUtils.getLastWeekEnd());
		// Map<String, Object> mapNowWeek = new HashMap<String, Object>();
		map.put("starNowTime", startNowWeek);
		map.put("endNowTime", endNowWeek);
		map.put("starLastTime", startLastWeek);
		map.put("endLastTime", endLastWeek);
		return zjqxxDao.getDqzTbHb(map);
	}

	// 今年和上年 “三盗两抢一诈骗“警情及其他警情情况
	private List<JzjqxxVo> getgetDqzTbHbYear(Map<String, Object> map) {
		// 本年开始时间
		String startNow = sdf.format(DateUtils.getThisYearStart());
		// 至今结束时间
		String endNow = sdf.format(new Date());
		// 上年开始时间
		String startLast = sdf.format(DateUtils.getLastYearStartTime());
		// 上年至今结束时间
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) - 1);
		Date date = curr.getTime();
		String endLast = sdf.format(date);

		map.put("starNowTime", startNow);
		map.put("endNowTime", endNow);
		map.put("starLastTime", startLast);
		map.put("endLastTime", endLast);
		return zjqxxDao.getDqzTbHb(map);
	}

	// 同比和环比
	private String getTbHb(String lastNumber, String nowNumber) {
		int last = Integer.parseInt(lastNumber);
		int now = Integer.parseInt(nowNumber);
		float tb = now;
		if (last != 0) {
			double i = now - last;
			tb = (float) (i / last);

		}

		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数保留两位小数
		nt.setMinimumFractionDigits(2);
		// System.out.println("==="+nt.format(tb));
		return nt.format(tb);
	}


	/**
	 * 根据传入时间获得时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDifferenceDay(String startTime, String endTime) {
		int day = 0;
		try {
			long from = sdf.parse(startTime).getTime();
			long to = sdf.parse(endTime).getTime();
			day = (int) ((to - from) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (day + 1);

	}

	/*public static void main(String[] args) {
		System.out.println("根据传入时间获得上周开始时间：" + getDifferenceDay("2018-10-08 00:00:00", "2018-10-20 23:59:59"));
		System.out.println("根据传入时间获得上周开始时间：" + getLastTimeForRingStart("2018-10-08 00:00:00", 13));
		System.out.println("根据传入时间获得上周开始时间：" + getLastTimeForRingEnd("2018-10-20 23:59:59", 13));
		System.out.println("根据传入时间获得上周开始时间：" + getLastTimeForWithStart("2018-10-08 00:00:00", 13));
		System.out.println("根据传入时间获得上周开始时间：" + getLastTimeForWithEnd("2018-10-20 23:59:59", 13));

		System.out.println("上上年开始：" + sdf.format(getBeforeLastYearStartTime()));
		System.out.println("上上年结束：" + sdf.format(getBeforeLastYearEndTime()));
		System.out.println("上上季度开始：" + sdf.format(getBeforeLastQuarterStart()));
		System.out.println("上上季度结束：" + sdf.format(getBeforeLastQuarterEnd()));
		System.out.println("上上月开始：" + sdf.format(getBeforeLastMonthStart()));
		System.out.println("上上月结束：" + sdf.format(getBeforeLastMonthEnd()));
	}*/

	/**
	 * 根据传入时间和时间差返回之前时间（环比）(开始时间)
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getLastTimeForRingStart(String date, int day) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, -day);// 日期加10天
		// cal.add(Calendar.SECOND, -1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 根据传入时间和时间差返回之前时间（环比）（结束时间）
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getLastTimeForRingEnd(String date, int day) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, -day);// 日期加10天
		// cal.add(Calendar.SECOND, -1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 根据传入时间和时间差返回之前时间（同比）
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getLastTimeForWithStart(String date, int day) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.DAY_OF_YEAR, -day);
		return sdf.format(cal.getTime());
	}

	/**
	 * 根据传入时间和时间差返回之前时间（同比）
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getLastTimeForWithEnd(String date, int day) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.DAY_OF_YEAR, -day);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得上上一年开始时间
	 * 
	 * @return
	 */
	public static Date getBeforeLastYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getLastYearStartTime());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 获得上上一年结束时间
	 * 
	 * @return
	 */
	public static Date getBeforeLastYearEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeforeLastYearStartTime());
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}

	/**
	 * 获得上上季度开始时间
	 * 
	 * @return
	 */
	public static Date getBeforeLastQuarterStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getLastQuarterStart());
		cal.add(Calendar.MONTH, -3);
		return cal.getTime();
	}

	/**
	 * 获得上上季度结束时间
	 * 
	 * @return
	 */
	public static Date getBeforeLastQuarterEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeforeLastQuarterStart());
		cal.add(Calendar.MONTH, 3);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}

	/**
	 * 获得上年的本季度结束时间
	 * 
	 * @return
	 */
	public static Date getLastYearQuarterStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getThisQuarterStart());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 获得上年的本季度结束时间
	 * 
	 * @return
	 */
	public static Date getLastYearQuarterEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getThisQuarterEnd());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 获得上上月开始时间
	 * 
	 * @return
	 */
	public static Date getBeforeLastMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getLastMonthStart());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获得上上月结束时间
	 * 
	 * @return
	 */
	public static Date getBeforeLastMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeforeLastMonthStart());
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}

	/**
	 * 获得上年本月开始时间
	 * 
	 * @return
	 */
	public static Date getLastYearMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getThisMonthStart());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 获得上年本月结束时间
	 * 
	 * @return
	 */
	public static Date getLastYearMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getThisMonthEnd());
		cal.add(Calendar.YEAR, 1);
		return cal.getTime();
	}

	

}
