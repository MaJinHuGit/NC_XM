package com.mtlckj.base.jqfx.service.impl;

import java.text.NumberFormat;
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

import com.mtlckj.base.jqfx.domain.Ztyp;
import com.mtlckj.base.jqfx.mapper.ZtypMapper;
import com.mtlckj.base.jqfx.service.ZtypService;

import com.mtlckj.base.jqfx.utils.DateUtils;

/**
 * 违法犯罪警情impl
 * 
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */

@Service
public class ZtypServiceImpl implements ZtypService {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private ZtypMapper ztypDao;

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
	 *            开始时间
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
	 *            结束时间
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

	@Override
	public List<Ztyp> getJqzst(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getThisWeekStart()).trim());
			map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()).trim());
		} else {
			map.put("startTime", ((String) params.get("startTime")).trim());
			map.put("endTime", ((String) params.get("endTime")).trim());

		}
		return ztypDao.getJqzst(map);
	}

	@Override
	public List<Ztyp> getJqzstTime(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			map.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			map.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			map.put("startTime", params.get("startTime"));
			map.put("endTime", params.get("endTime"));

		}
		return ztypDao.getJqzstTime(map);
	}

	@Override
	public List<Map<String, String>> getJqlbtj(Map<String, Object> params) {
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		List<Ztyp> list = getJqlbtjInfo(params);

		for (int i = 0; i < list.size(); i++) {
			map = new HashMap<String, String>();
			map.put("ct", list.get(i).getCt());
			map.put("cuntnow", list.get(i).getCuntnow());
			map.put("cuntlast", list.get(i).getCuntlast());
			map.put("hb", getTbHb(list.get(i).getCuntlast(), list.get(i).getCuntnow()));
			listMap.add(map);
		}
		return listMap;
	}

	private List<Ztyp> getJqlbtjInfo(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			map.put("startNowTime", sdf.format(DateUtils.getThisWeekStart()).trim());
			map.put("endNowTime", sdf.format(DateUtils.getThisWeekEnd()).trim());
			map.put("startLastTime", sdf.format(DateUtils.getLastWeekStart()).trim());
			map.put("endLastTime", sdf.format(DateUtils.getLastWeekEnd()).trim());
		} else {
			int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
			map.put("startNowTime", ((String) params.get("startTime")).trim());
			map.put("endNowTime", ((String) params.get("endTime")).trim());
			map.put("startLastTime", getLastTimeForRingStart((String) params.get("startTime"), day).trim());
			map.put("endLastTime", getLastTimeForRingEnd((String) params.get("endTime"), day).trim());

		}
		return ztypDao.getJqlbtjs(map);
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
	 * 
	 * @Function: ZtypMapper.java
	 * @Description: 高危辖区
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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年10月31日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Ztyp> getGwqy(Map<String, Object> params) {

		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}

		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}

		return ztypDao.getGwqy(params);
	}

	/*
	 * private List<Ztyp> queryFzType(){
	 * 
	 * }
	 */
	/**
	 * 
	 * @see com.mtlckj.base.jqfx.service.ZtypService#getGwqyTjt(java.util.Map)
	 * @Function: ZtypServiceImpl.java
	 * @Description: 高危区域
	 *
	 * @param map
	 * @return
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: tq
	 * @date: 2018年11月1日 上午10:22:21
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年11月1日
	 *        tq v1.0.0 修改原因
	 */
	@Override
	public List<Ztyp> getGwqyTjt(Map<String, Object> params) {
		// Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}
		return ztypDao.getGwqyTjt(params);
	}

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年11月1日
	 *        唐青 v1.0.0 修改原因
	 */
	@Override
	public List<Ztyp> getGwqyList(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}
		return ztypDao.getGwqyList(params);
	}

	/**
	 * 分页统计
	 * 
	 * @return
	 */

	@Override
	public Integer getGwqyListCunt(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}
		return ztypDao.getGwqyListCunt(params);
	}

	/**
	 * 
	 * @see com.mtlckj.base.jqfx.service.ZtypService#getGwsd(java.util.Map)
	 * @Function: ZtypServiceImpl.java
	 * @Description: 高危时段数据
	 *
	 * @param map
	 * @return
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: tq
	 * @date: 2018年11月1日 下午5:30:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年11月1日
	 *        tq v1.0.0 修改原因
	 */
	@Override
	public List<Ztyp> getGwsd(Map<String, Object> params) {
	//	Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}

		return ztypDao.getGwsd(params);
	}

	/**
	 * 
	 * @see com.mtlckj.base.jqfx.service.ZtypService#getGwsdTjt(java.util.Map)
	 * @Function: ZtypServiceImpl.java
	 * @Description: 高危时段统计图
	 *
	 * @param map
	 * @return
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: tq
	 * @date: 2018年11月1日 下午5:21:41
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年11月1日
	 *        tq v1.0.0 修改原因
	 */
	@Override
	public List<Ztyp> getGwsdTjt(Map<String, Object> params) {
		//Map<String, Object> map = new HashMap<String, Object>();
		// 页面没有传时间条件默认时间为本周时间
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}
		return ztypDao.getGwsdTjt(params);
	}

	/**
	 * 
	 * @see com.mtlckj.base.jqfx.service.ZtypService#getGwsdList(java.util.Map)
	 * @Function: ZtypServiceImpl.java
	 * @Description: 高危时段列表
	 *
	 * @param map
	 * @return
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: tq
	 * @date: 2018年11月1日 下午5:22:03
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年11月1日
	 *        tq v1.0.0 修改原因
	 */
	@Override
	public List<Ztyp> getGwsdList(Map<String, Object> params) {
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}
		return ztypDao.getGwsdList(params);
	}

	/**
	 * 分页统计
	 * 
	 * @return
	 */
	@Override
	public Integer getGwsdListCunt(Map<String, Object> params) {
		if ("-1".equals(params.get("startTime"))) {

			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		} else {
			params.put("startTime", params.get("startTime"));
			params.put("endTime", params.get("endTime"));

		}
		if (!"-1".equals(params.get("fstype"))) {
			String[] typeArry = queryFzType(params.get("fstype").toString());

			params.put("fstype", typeArry);
		}
		return ztypDao.getGwsdListCunt(params);
	}

	private String[] queryFzType(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] arry = str.split("@");
		List<Ztyp> ztyp = new ArrayList<Ztyp>();
		StringBuilder sb = new StringBuilder();
		//int len = 0;
		for (int i = 0; i < arry.length; i++) {
			if ("盗窃".equals(arry[i])) {
				map.put("type", "1");
				ztyp = ztypDao.getFzType(map);
			//	len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");	
				}
			} else if ("抢劫".equals(arry[i])) {
				map.put("type", "2");
				ztyp = ztypDao.getFzType(map);
				//len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
			} else if ("诈骗".equals(arry[i])) {
				map.put("type", "3");
				ztyp = ztypDao.getFzType(map);
				//len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
			} else if ("摩、电".equals(arry[i])) {
				map.put("type", "4");
				ztyp = ztypDao.getFzType(map);
				//len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
			} else if ("入室盗窃".equals(arry[i])) {
				map.put("type", "5");
				ztyp = ztypDao.getFzType(map);
			//	len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
			} else if ("涉车盗窃".equals(arry[i])) {
				map.put("type", "6");
				ztyp = ztypDao.getFzType(map);
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
				
			} else if ("两抢".equals(arry[i])) {
				map.put("type", "7");
				ztyp = ztypDao.getFzType(map);
				//len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
			} else if ("侵财类".equals(arry[i])) {
				map.put("type", "8");
				ztyp = ztypDao.getFzType(map);
			//	len = typeArry.length;
				for (int j = 0; j < ztyp.size(); j++) {
					sb.append(ztyp.get(j).getJqlb());	
					sb.append(",");
				}
			}
		}
		String strType = sb.toString();
		strType = strType.substring(0,strType.length() - 1);
		return  strType.split(",");
	}

}
