package com.mtlckj.base.jqfx.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.mapper.XqjqflMapper;
import com.mtlckj.base.jqfx.service.XqjqflService;
import com.mtlckj.base.jqfx.utils.DateUtils;

@Service
public class XqjqflServiceImpl implements XqjqflService {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private XqjqflMapper xqjqflMapper;

	@Override
	public List<Map<String, String>> getTable(Map<String, Object> params) {
		
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		// 总量统计
		List<Map<String, Object>> returnList = setParams(params);
		for (int i = 0; i < returnList.size(); i++) {
			Map<String, String> resultMap = new HashMap<String, String>();
			// 获取派出所名称
			resultMap.put("name", (String) returnList.get(i).get("NAME"));
			
			// 本周对比上周、本月对比上月
			resultMap.put("nowCompareLastInfo", getPkInfo(returnList.get(i).get("LASTCOUNT").toString(),returnList.get(i).get("NOWCOUNT").toString()));
			resultMap.put("nowCompareLastNum", getPkNumber(returnList.get(i).get("LASTCOUNT").toString(),returnList.get(i).get("NOWCOUNT").toString()));
			
			// 上周对比上上周、上月对比上上月
			resultMap.put("lastCompareLastLastInfo", getPkInfo(returnList.get(i).get("LASTYEARCOUNT").toString(),returnList.get(i).get("LASTCOUNT").toString()));
			resultMap.put("lastCompareLastLastNum", getPkNumber(returnList.get(i).get("LASTYEARCOUNT").toString(),returnList.get(i).get("LASTCOUNT").toString()));
			
			if(returnList.get(i).get("LASTYEARCOUNT") != null && !returnList.get(i).get("LASTYEARCOUNT").equals("")) {
				resultMap.put("nowCompareLastYearInfo", getPkInfo(returnList.get(i).get("LASTYEARCOUNT").toString(),returnList.get(i).get("NOWCOUNT").toString()));
				resultMap.put("nowCompareLastYearNum", getPkNumber(returnList.get(i).get("LASTYEARCOUNT").toString(),returnList.get(i).get("NOWCOUNT").toString()));
			}

			params.put("name", (String) returnList.get(i).get("NAME"));
			//得到三盗两抢一诈骗的具体数据
			//抢劫
			params.put("type", "抢劫");
			Map<String, Object> map1 = setParams2(params);
			resultMap.put("robCompareInfo", getPkInfo(map1.get("LAST").toString(), map1.get("NOW").toString()));
			resultMap.put("robCompareNum", getPkNumber(map1.get("LAST").toString(), map1.get("NOW").toString()));
			
			//抢夺
			params.put("type", "抢夺");
			Map<String, Object> map2 = setParams2(params);
			resultMap.put("lootCompareInfo", getPkInfo(map2.get("LAST").toString(), map2.get("NOW").toString()));
			resultMap.put("lootCompareNum", getPkNumber(map2.get("LAST").toString(), map2.get("NOW").toString()));
			
			//入室盗窃
			params.put("type", "入室盗窃");
			Map<String, Object> map3 = setParams2(params);
			resultMap.put("burglaryCompareInfo", getPkInfo(map3.get("LAST").toString(), map3.get("NOW").toString()));
			resultMap.put("burglaryCompareNum", getPkNumber(map3.get("LAST").toString(), map3.get("NOW").toString()));
			
			//盗窃车内财物
			params.put("type", "盗窃车内财物");
			Map<String, Object> map4 = setParams2(params);
			resultMap.put("theftOfCarPropertyCompareInfo", getPkInfo(map4.get("LAST").toString(), map4.get("NOW").toString()));
			resultMap.put("theftOfCarPropertyCompareNum", getPkNumber(map4.get("LAST").toString(), map4.get("NOW").toString()));
			
			//盗窃机动车
			params.put("type", "盗窃机动车");
			Map<String, Object> map5 = setParams2(params);
			resultMap.put("stolenMotorVehicleCompareInfo", getPkInfo(map5.get("LAST").toString(), map5.get("NOW").toString()));
			resultMap.put("stolenMotorVehicleCompareNum", getPkNumber(map5.get("LAST").toString(), map5.get("NOW").toString()));
			
			//诈骗
			params.put("type", "诈骗");
			Map<String, Object> map6 = setParams2(params);
			resultMap.put("fraudCompareInfo", getPkInfo(map6.get("LAST").toString(), map6.get("NOW").toString()));
			resultMap.put("fraudCompareNum", getPkNumber(map6.get("LAST").toString(), map6.get("NOW").toString()));
			
			listMap.add(resultMap);
		}
		return listMap;
	}

	// 本周，上周 分类统计违法犯罪的数据
	private Map<String, Object> setParams2(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", params.get("name"));
		map.put("type", params.get("type"));
		if ("-1".equals(params.get("startTime")) || "bz".equals(params.get("startTime"))) {
			// 本周开始时间
			map.put("startNowTime", sdf.format(DateUtils.getThisWeekStart()));
			// 本周结束时间
			map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()));
			// 上周开始时间
			map.put("lastStartTime", sdf.format(DateUtils.getLastWeekStart()));
			// 上周结束时间
			map.put("lastEndTime", sdf.format(DateUtils.getLastWeekEnd()));

		} else {
			if ("bn".equals(params.get("startTime"))) {
				// 本年开始时间
				map.put("startNowTime", sdf.format(DateUtils.getThisYearStart()));
				// 本年结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisYearEnd()));
				// 上年开始时间
				map.put("lastStartTime", sdf.format(DateUtils.getLastYearStartTime()));
				// 上年结束时间
				map.put("lastEndTime", sdf.format(DateUtils.getLastYearEndTime()));

			} else if ("bj".equals(params.get("startTime"))) {

				// 本季度开始时间
				map.put("startNowTime", sdf.format(DateUtils.getThisQuarterStart()));
				// 本季度结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisQuarterEnd()));
				// 上季度开始时间
				map.put("lastStartTime", sdf.format(DateUtils.getLastQuarterStart()));
				// 上季度结束时间
				map.put("lastEndTime", sdf.format(DateUtils.getLastQuarterEnd()));

			} else if ("by".equals(params.get("startTime"))) {

				// 本月开始时间
				map.put("startNowTime", sdf.format(DateUtils.getThisMonthStart()));
				// 本月结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisMonthEnd()));
				// 上月开始时间
				map.put("lastStartTime", sdf.format(DateUtils.getLastMonthStart()));
				// 上月结束时间
				map.put("lastEndTime", sdf.format(DateUtils.getLastMonthEnd()));

			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				// 本期开始时间
				map.put("startNowTime", (String) params.get("startTime"));
				// 本期结束时间
				map.put("endNowTime", (String) params.get("endTime"));
				// 上期开始时间
				map.put("lastStartTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				// 上期结束时间
				map.put("lastEndTime", getLastTimeForRingEnd((String) params.get("endTime"), day));

			}
		}
		return xqjqflMapper.getTable2(map);
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
	private List<Map<String, Object>> setParams(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();

		if ("-1".equals(params.get("startTime")) || "bz".equals(params.get("startTime"))) {
			// 本周开始时间
			map.put("startNowTime", sdf.format(DateUtils.getThisWeekStart()));
			// 本周结束时间
			map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()));
			// 上周开始时间
			map.put("lastStartTime", sdf.format(DateUtils.getLastWeekStart()));
			// 上周结束时间
			map.put("lastEndTime", sdf.format(DateUtils.getLastWeekEnd()));
			// 上上周开始时间
			map.put("lastLastStartTime", sdf.format(DateUtils.getBeforeLastWeekStart()));
			// 上上周结束时间
			map.put("lastLastEndTime", sdf.format(DateUtils.getBeforeLastWeekEnd()));
			// 上年同周开始时间
			map.put("lastYearStartTime", sdf.format(DateUtils.getLastYearWeekStartByNumber()));
			// 上年同周结束时间
			map.put("lastYearEndTime", sdf.format(DateUtils.getLastYearWeekEndByNumber()));
			
			map.put("state", "4");
		} else if ("bn".equals(params.get("startTime"))) {
			
				// 本年开始时间
				map.put("startNowTime", sdf.format(DateUtils.getThisYearStart()));
				// 本年结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisYearEnd()));
				// 上年开始时间
				map.put("lastStartTime", sdf.format(DateUtils.getLastYearStartTime()));
				// 上年结束时间
				map.put("lastEndTime", sdf.format(DateUtils.getLastYearEndTime()));
				// 上上年开始时间
				map.put("lastYearStartTime", sdf.format(getBeforeLastYearStartTime()));
				// 上上年结束时间
				map.put("lastYearEndTime", sdf.format(getBeforeLastYearEndTime()));
				
				map.put("state", "3");
			} else if ("bj".equals(params.get("startTime"))) {

				// 本季度开始时间
				map.put("startNowTime", sdf.format(DateUtils.getThisQuarterStart()));
				// 本季度结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisQuarterEnd()));
				// 上季度开始时间
				map.put("lastStartTime", sdf.format(DateUtils.getLastQuarterStart()));
				// 上季度结束时间
				map.put("lastEndTime", sdf.format(DateUtils.getLastQuarterEnd()));
				// 上上季度开始时间
				map.put("lastLastStartTime", sdf.format(getBeforeLastQuarterStart()));
				// 上上季度结束时间
				map.put("lastLastEndTime", sdf.format(getBeforeLastQuarterEnd()));
				// 上年同季度开始时间
				map.put("lastYearStartTime", sdf.format(getLastYearQuarterStart()));
				// 上年同季度结束时间
				map.put("lastYearEndTime", sdf.format(getLastYearQuarterEnd()));
				
				map.put("state", "4");
			} else if ("by".equals(params.get("startTime"))) {

				// 本月开始时间
				map.put("startNowTime", sdf.format(DateUtils.getThisMonthStart()));
				// 本月结束时间
				map.put("endNowTime", sdf.format(DateUtils.getThisMonthEnd()));
				// 上月开始时间
				map.put("lastStartTime", sdf.format(DateUtils.getLastMonthStart()));
				// 上月结束时间
				map.put("lastEndTime", sdf.format(DateUtils.getLastMonthEnd()));
				// 上上月开始时间
				map.put("lastLastStartTime", sdf.format(getBeforeLastMonthStart()));
				// 上上月结束时间
				map.put("lastLastEndTime", sdf.format(getBeforeLastMonthEnd()));
				// 上年同月开始时间
				map.put("lastYearStartTime", sdf.format(getLastYearMonthStart()));
				// 上年同月结束时间
				map.put("lastYearEndTime", sdf.format(getLastYearMonthEnd()));
				
				map.put("state", "4");
			} else {
				int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
				//本期开始时间
				map.put("startNowTime", (String) params.get("startTime"));
				//本期结束时间
				map.put("endNowTime", (String) params.get("endTime"));
				//上个时期开始时间
				map.put("lastStartTime", getLastTimeForRingStart((String) params.get("startTime"), day));
				//上个时期结束时间
				map.put("lastEndTime", getLastTimeForRingEnd((String) params.get("endTime"), day));
				//去年同时期开始时间
				map.put("lastYearStartTime", getLastTimeForRingStart((String) params.get("startTime"), 0));
				//去年同时期结束时间
				map.put("lastYearLastEndTime", getLastTimeForRingEnd((String) params.get("endTime"), 0));
				
				map.put("state", "3");
			}
		return xqjqflMapper.getTable(map);
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
