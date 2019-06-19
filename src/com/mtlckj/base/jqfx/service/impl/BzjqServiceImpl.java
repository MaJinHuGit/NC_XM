package com.mtlckj.base.jqfx.service.impl;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.Wffzjq;
import com.mtlckj.base.jqfx.mapper.BzjqMapper;
import com.mtlckj.base.jqfx.service.BzjqService;
import com.mtlckj.base.jqfx.utils.DateUtils;

/**
 * 违法犯罪警情impl
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */

@Service
public class BzjqServiceImpl implements BzjqService {
    @Autowired
    private BzjqMapper bzjqDao;

	/**
	 * 警情列表
	 * 
	 * @return
	 */
	@Override
	public List<Wffzjq> getList(Map<String, Object> params) {
		String zxsj = (String) params.get("zxsj");
		String ksms = (String) params.get("ksms");
		String startTime = "";
		String endTime = "";

		if(StringUtils.isNotEmpty(zxsj)){
			String[] str = zxsj.split("-");
			startTime = str[0]+"-"+str[1]+"-"+str[2]+" 00:00:00";
			endTime = str[3]+"-"+str[4]+"-"+str[5]+" 23:59:59";
		}else{
			if(StringUtils.isEmpty(zxsj)){
				if(ksms.equals("bz")){//本周
					startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
					endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				}
				if(ksms.equals("by")){//本月
					startTime = DateUtils.DateToString(DateUtils.getThisMonthStart());
					endTime = DateUtils.DateToString(DateUtils.getThisMonthEnd());
				}
				if(ksms.equals("bj")){//本季度
					startTime = DateUtils.DateToString(DateUtils.getThisQuarterStart());
					endTime = DateUtils.DateToString(DateUtils.getThisQuarterEnd());
				}
				if(ksms.equals("bn")){//本年
					startTime = DateUtils.DateToString(DateUtils.getThisYearStart());
					endTime = DateUtils.DateToString(DateUtils.getThisYearEnd());
				}
			}else {
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
			}
			
		}
		params.put("start", startTime);
		params.put("end", endTime);
		return bzjqDao.getList(params);
	}
	
	
	
	/**
	 * 根据接警单编号查询
	 */
	@Override
	public Wffzjq get(String jjdbh) {
		return bzjqDao.get(jjdbh);
	}
	
	
	
    
	/**
	 * 本周警情
	 */
	@Override
	public Map<String, Object> countByWeek(Map<String, Object> params) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");
		
		if(StringUtils.isNotEmpty(ksms)||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
			resultMap.put("zjq", countByKsms(params));//总警情
			//交通事故
			params.put("lx", "交通事故");
			resultMap.put("jtsg", countByKsms(params));
			//火灾
			params.put("lx", "火灾");
			resultMap.put("hz", countByKsms(params));
			//咨询
			params.put("lx", "咨询");
			resultMap.put("zx", countByKsms(params));
			//求助
			params.put("lx", "求助");
			resultMap.put("qz", countByKsms(params));
			//骚扰误报
			params.put("lx", "骚扰误报");
			resultMap.put("srwb", countByKsms(params));
			//噪音扰民
			params.put("lx", "噪音扰民");
			resultMap.put("zyrm", countByKsms(params));
			//投诉
			params.put("lx", "投诉");
			resultMap.put("ts", countByKsms(params));
			//重复报警
			params.put("lx", "重复报警");
			resultMap.put("cfbj", countByKsms(params));
		}else{
			resultMap.put("zjq", countByZxsj(params));//总警情
			//交通事故
			params.put("lx", "交通事故");
			resultMap.put("jtsg", countByZxsj(params));
			//火灾
			params.put("lx", "火灾");
			resultMap.put("hz", countByZxsj(params));
			//咨询
			params.put("lx", "咨询");
			resultMap.put("zx", countByZxsj(params));
			//求助
			params.put("lx", "求助");
			resultMap.put("qz", countByZxsj(params));
			//骚扰误报
			params.put("lx", "骚扰误报");
			resultMap.put("srwb", countByZxsj(params));
			//噪音扰民
			params.put("lx", "噪音扰民");
			resultMap.put("zyrm", countByZxsj(params));
			//投诉
			params.put("lx", "投诉");
			resultMap.put("ts", countByZxsj(params));
			//重复报警
			params.put("lx", "重复报警");
			resultMap.put("cfbj", countByZxsj(params));
		}
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> countByHours(Map<String, Object> params) {
Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");
		
		if(StringUtils.isNotEmpty(ksms)||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
			resultMap.put("zjq", countByKsmsHours(params));//总警情
			//交通事故
			params.put("lx", "交通事故");
			resultMap.put("jtsg", countByKsmsHours(params));
			//火灾
			params.put("lx", "火灾");
			resultMap.put("hz", countByKsmsHours(params));
			//咨询
			params.put("lx", "咨询");
			resultMap.put("zx", countByKsmsHours(params));
			//求助
			params.put("lx", "求助");
			resultMap.put("qz", countByKsmsHours(params));
			//骚扰误报
			params.put("lx", "骚扰误报");
			resultMap.put("srwb", countByKsmsHours(params));
			//噪音扰民
			params.put("lx", "噪音扰民");
			resultMap.put("zyrm", countByKsmsHours(params));
			//投诉
			params.put("lx", "投诉");
			resultMap.put("ts", countByKsmsHours(params));
			//重复报警
			params.put("lx", "重复报警");
			resultMap.put("cfbj", countByKsmsHours(params));
		}else{
			resultMap.put("zjq", countByZxsjHours(params));//总警情
			//交通事故
			params.put("lx", "交通事故");
			resultMap.put("jtsg", countByZxsjHours(params));
			//火灾
			params.put("lx", "火灾");
			resultMap.put("hz", countByZxsjHours(params));
			//咨询
			params.put("lx", "咨询");
			resultMap.put("zx", countByZxsjHours(params));
			//求助
			params.put("lx", "求助");
			resultMap.put("qz", countByZxsjHours(params));
			//骚扰误报
			params.put("lx", "骚扰误报");
			resultMap.put("srwb", countByZxsjHours(params));
			//噪音扰民
			params.put("lx", "噪音扰民");
			resultMap.put("zyrm", countByZxsjHours(params));
			//投诉
			params.put("lx", "投诉");
			resultMap.put("ts", countByZxsjHours(params));
			//重复报警
			params.put("lx", "重复报警");
			resultMap.put("cfbj", countByZxsjHours(params));
		}
		
		return resultMap;
	}


	@Override
	public Integer count(Map<String, Object> params) {
		return bzjqDao.count(params);
	}
	
	
	/**
	 * 默认查询和快速模式查询
	 * @param params
	 * @return
	 */
	private Map<String, Object> countByKsms(Map<String, Object> map){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> hbparams = new HashMap<String,Object>();//环比时间
		Map<String,Object> tbparams = new HashMap<String,Object>();//同比时间
		params.putAll(map);
		hbparams.putAll(map);
		tbparams.putAll(map);

		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");

		if(StringUtils.isEmpty(ksms)){
			if(StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastWeekStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastWeekEnd()));
				int year = DateUtils.getNowYear();//获得今年年份
				int weeknumLastYear = DateUtils.getMaxWeekNumOfYear(year-1);//去年最大周数
				int weeknum = DateUtils.getWeekOfYear(new Date());//当前时间是今年的第几周
				if(weeknum>=weeknumLastYear){
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}else{
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}

			}
		}else{
			if(ksms.equals("bz")){//本周
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastWeekStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastWeekEnd()));
				int year = DateUtils.getNowYear();//获得今年年份
				int weeknumLastYear = DateUtils.getMaxWeekNumOfYear(year-1);//去年最大周数
				int weeknum = DateUtils.getWeekOfYear(new Date());//当前时间是今年的第几周
				if(weeknum>=weeknumLastYear){
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}else{
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}
			}
			if(ksms.equals("by")){//本月
				startTime = DateUtils.DateToString(DateUtils.getThisMonthStart());
				endTime = DateUtils.DateToString(DateUtils.getThisMonthEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastMonthStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastMonthEnd()));
				tbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearWeekStartTime()));
				tbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearWeekEndTime()));
			}
			if(ksms.equals("bj")){//本季度
				startTime = DateUtils.DateToString(DateUtils.getThisQuarterStart());
				endTime = DateUtils.DateToString(DateUtils.getThisQuarterEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastQuarterStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastQuarterEnd()));
				tbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearQuarterStart()));
				tbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearQuarterEnd()));
			}
			if(ksms.equals("bn")){//本年
				startTime = DateUtils.DateToString(DateUtils.getThisYearStart());
				endTime = DateUtils.DateToString(DateUtils.getThisYearEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearStartTime()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearEndTime()));
				tbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearStartTime()));
				tbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearEndTime()));
			}
		}
		
		int bqCount = bzjqDao.countByWeek(params); //本期
		int sqCount = bzjqDao.countByWeek(hbparams); //上期
		int tbCount = bzjqDao.countByWeek(tbparams); //同比
		
		result.put("bq", bqCount);//本期
		result.put("sq", sqCount);//上期
		
	/*	double hb = bqCount-sqCount;
		if(sqCount!=0){
			hb = hb/sqCount;
		}
		hb = hb * 100;
		BigDecimal hbb = new BigDecimal(hb);  
		hb = hbb.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); */
		result.put("hb", getTbHb(sqCount,bqCount));//环比
		result.put("tq", tbCount);//同期
		/*
		double tb = bqCount-tbCount;
		if(tbCount!=0){
			tb = tb/tbCount;
		}
		tb = tb * 100;
		BigDecimal tbb = new BigDecimal(tb);  
		tb = tbb.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); */
		result.put("tb",getTbHb(tbCount,bqCount));//计算同比
		return result;
	}
	
	
	
	
	/**
	 * 自选时间模式
	 * @param params
	 * @return
	 */
	private Map<String, Object> countByZxsj(Map<String, Object> map){
		
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> hbparams = new HashMap<String,Object>();//环比时间
		Map<String,Object> tbparams = new HashMap<String,Object>();//同比时间
		params.putAll(map);
		hbparams.putAll(map);
		tbparams.putAll(map);
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		int day =0;

		//环比
		hbparams.put("start",DateUtils.getLastTimeForRing(startTime,day));
		hbparams.put("end", DateUtils.getLastTimeForRing(endTime,day));
		//同比
		tbparams.put("start",DateUtils.getLastTimeForWith(startTime,day));
		tbparams.put("end", DateUtils.getLastTimeForWith(endTime,day));
				
		int bqCount = bzjqDao.countByWeek(params);//本期警情
		int sqCount = bzjqDao.countByWeek(hbparams);//上期警情
		int tbCount = bzjqDao.countByWeek(tbparams);//同比警情
		
		result.put("bq", bqCount);//本期
		result.put("sq", sqCount);//上期
	/*	
		double hb = bqCount-sqCount;
		if(sqCount!=0){
			hb = hb/sqCount;
		}
		hb = hb * 100;
		BigDecimal hbb = new BigDecimal(hb);  
		hb = hbb.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); */
		result.put("hb",getTbHb(sqCount,bqCount));//环比
		result.put("tq", tbCount);//同期
		
		/*double tb = bqCount-tbCount;
		if(tbCount!=0){
			tb = tb/tbCount;
		}
		tb = tb * 100;
		BigDecimal tbb = new BigDecimal(tb);  
		tb = tbb.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue(); */
		result.put("tb",getTbHb(tbCount,bqCount));//计算同比
		return result;
	}

	// 同比和环比
	private String getTbHb(int lastNumber, int nowNumber) {
		int last = lastNumber;
		int now = nowNumber;
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
	 * 默认查询和快速模式查询
	 * @param params
	 * @return
	 */
	private Map<String, Object> countByKsmsHours(Map<String, Object> map){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.putAll(map);
	

		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");
		int day = 0;
		if(StringUtils.isEmpty(ksms)){
			if(StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				
				day = DateUtils.getDifferenceDay(startTime,endTime);
			}
		}else{
			if(ksms.equals("bz")){//本周
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				day = DateUtils.getDifferenceDay(startTime,endTime);
				
			}
			if(ksms.equals("by")){//本月
				startTime = DateUtils.DateToString(DateUtils.getThisMonthStart());
				endTime = DateUtils.DateToString(DateUtils.getThisMonthEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				day = DateUtils.getDifferenceDay(startTime,endTime);
			}
			if(ksms.equals("bj")){//本季度
				startTime = DateUtils.DateToString(DateUtils.getThisQuarterStart());
				endTime = DateUtils.DateToString(DateUtils.getThisQuarterEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				day = DateUtils.getDifferenceDay(startTime,endTime);
			}
			if(ksms.equals("bn")){//本年
				startTime = DateUtils.DateToString(DateUtils.getThisYearStart());
				endTime = DateUtils.DateToString(DateUtils.getThisYearEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				day = DateUtils.getDifferenceDay(startTime,endTime);
			}
		}
		DecimalFormat df = new DecimalFormat("#.00");
		List<Wffzjq> list= bzjqDao.countByHours(params); //本期
		for (Wffzjq wffzjq : list) {
			wffzjq.setGjz(String.valueOf(df.format(Double.valueOf(wffzjq.getGjz())/day)));
		}
		
		result.put("bq", list);//本期
		
		return result;
	}
	
	
	
	
	/**
	 * 自选时间模式
	 * @param params
	 * @return
	 */
	private Map<String, Object> countByZxsjHours(Map<String, Object> map){
		
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
	
		params.putAll(map);
		
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		int day = DateUtils.getDifferenceDay(startTime,endTime);
	
		DecimalFormat df = new DecimalFormat("#.00");
		List<Wffzjq> list= bzjqDao.countByHours(params); //本期
		for (Wffzjq wffzjq : list) {
			wffzjq.setGjz(String.valueOf(df.format(Double.valueOf(wffzjq.getGjz())/day)));
		}
		result.put("bq", list);//本期
	
		return result;
	}



	@Override
	public List<Wffzjq> getAfdz(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getAfdz(map);
	}



	@Override
	public int getAfdzCount(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getAfdzCount(map);
	}



	@Override
	public List<Wffzjq> getAfdzByLx(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getAfdzByLx(map);
	}



	@Override
	public int getAfdzByLxCount(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getAfdzByLxCount(map);
	}



	@Override
	public List<Wffzjq> getGjz(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getGjz(map);
	}



	@Override
	public int getGjzCount(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getGjzCount(map);
	}



	@Override
	public List<Wffzjq> getGjzByLx(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getGjzByLx(map);
	}



	@Override
	public int getGjzByLxCount(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getGjzByLxCount(map);
	}



	@Override
	public List<Wffzjq> getBjsj(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjsj(map);
	}



	@Override
	public List<Wffzjq> getBjsjByLx(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjsjByLx(map);
	}



	@Override
	public List<Wffzjq> getBjxl(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjxl(map);
	}



	@Override
	public List<Wffzjq> getBjxlByLx(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjxlByLx(map);
	}



	@Override
	public List<Wffzjq> getGxdw(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getGxdw(map);
	}



	@Override
	public List<Wffzjq> getGxdwByLx(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getGxdwByLx(map);
	}



	@Override
	public List<Wffzjq> getBjdh(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjdh(map);
	}

	@Override
	public int getBjdhCount(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjdhCount(map);
	}


	@Override
	public List<Wffzjq> getBjdhByLx(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjdhByLx(map);
	}
	@Override
	public int getBjdhByLxCount(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getBjdhByLxCount(map);
	}

	private Map<String, Object> getTime(Map<String, Object> params) {
		String zxsj = (String) params.get("zxsj");
		String ksms = (String) params.get("ksms");
		String startTime = "";
		String endTime = "";

		if(StringUtils.isNotEmpty(zxsj)){
			String[] str = zxsj.split("-");
			startTime = str[0]+"-"+str[1]+"-"+str[2]+" 00:00:00";
			endTime = str[3]+"-"+str[4]+"-"+str[5]+" 23:59:59";
		}else{
			if(StringUtils.isEmpty(zxsj)){
				if(ksms.equals("bz")){//本周
					startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
					endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				}
				if(ksms.equals("by")){//本月
					startTime = DateUtils.DateToString(DateUtils.getThisMonthStart());
					endTime = DateUtils.DateToString(DateUtils.getThisMonthEnd());
				}
				if(ksms.equals("bj")){//本季度
					startTime = DateUtils.DateToString(DateUtils.getThisQuarterStart());
					endTime = DateUtils.DateToString(DateUtils.getThisQuarterEnd());
				}
				if(ksms.equals("bn")){//本年
					startTime = DateUtils.DateToString(DateUtils.getThisYearStart());
					endTime = DateUtils.DateToString(DateUtils.getThisYearEnd());
				}
			}else {
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
			}
			
		}
		params.put("start", startTime);
		params.put("end", endTime);
		return params;
	}



	@Override
	public List<Wffzjq> getListGxdw(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getListGxdw(map);
	}



	@Override
	public Integer countGxdw(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.countGxdw(map);
	}



	@Override
	public List<Wffzjq> getListBjdh(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getListBjdh(map);
	}



	@Override
	public Integer countBjdh(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.countBjdh(map);
	}



	@Override
	public List<Wffzjq> getListBjxl(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getListBjxl(map);
	}



	@Override
	public Integer countBjxl(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.countBjxl(map);
	}



	@Override
	public List<Wffzjq> getListAfdz(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getListAfdz(map);
	}



	@Override
	public Integer countAfdz(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.countAfdz(map);
	}



	@Override
	public List<Wffzjq> getListGjz(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.getListGjz(map);
	}



	@Override
	public Integer countGjz(Map<String, Object> params) {
		Map<String, Object> map = getTime(params);
		return bzjqDao.countGjz(map);
	}



	@Override
	public Map<String, Object> getWffzCount(Map<String, Object> map) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> hbparams = new HashMap<String,Object>();//环比时间
		Map<String,Object> tbparams = new HashMap<String,Object>();//同比时间
		params.putAll(map);
		hbparams.putAll(map);
		tbparams.putAll(map);
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");
		if(StringUtils.isEmpty(ksms)){
			if(StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastWeekStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastWeekEnd()));
				int year = DateUtils.getNowYear();//获得今年年份
				int weeknumLastYear = DateUtils.getMaxWeekNumOfYear(year-1);//去年最大周数
				int weeknum = DateUtils.getWeekOfYear(new Date());//当前时间是今年的第几周
				if(weeknum>=weeknumLastYear){
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}else{
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}

			}
		}else{
			if(ksms.equals("bz")){//本周
				startTime = DateUtils.DateToString(DateUtils.getThisWeekStart());
				endTime = DateUtils.DateToString(DateUtils.getThisWeekEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastWeekStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastWeekEnd()));
				int year = DateUtils.getNowYear();//获得今年年份
				int weeknumLastYear = DateUtils.getMaxWeekNumOfYear(year-1);//去年最大周数
				int weeknum = DateUtils.getWeekOfYear(new Date());//当前时间是今年的第几周
				if(weeknum>=weeknumLastYear){
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}else{
					tbparams.put("start", DateUtils.DateToString(DateUtils.getStartDayOfWeek(year-1,weeknumLastYear)));
					tbparams.put("end", DateUtils.DateToString(DateUtils.getEndDayOfWeek(year-1,weeknumLastYear)));
				}
			}
			if(ksms.equals("by")){//本月
				startTime = DateUtils.DateToString(DateUtils.getThisMonthStart());
				endTime = DateUtils.DateToString(DateUtils.getThisMonthEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastMonthStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastMonthEnd()));
				tbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearWeekStartTime()));
				tbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearWeekEndTime()));
			}
			if(ksms.equals("bj")){//本季度
				startTime = DateUtils.DateToString(DateUtils.getThisQuarterStart());
				endTime = DateUtils.DateToString(DateUtils.getThisQuarterEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastQuarterStart()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastQuarterEnd()));
				tbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearQuarterStart()));
				tbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearQuarterEnd()));
			}
			if(ksms.equals("bn")){//本年
				startTime = DateUtils.DateToString(DateUtils.getThisYearStart());
				endTime = DateUtils.DateToString(DateUtils.getThisYearEnd());
				params.put("start", startTime);
				params.put("end", endTime);
				hbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearStartTime()));
				hbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearEndTime()));
				tbparams.put("start", DateUtils.DateToString(DateUtils.getLastYearStartTime()));
				tbparams.put("end", DateUtils.DateToString(DateUtils.getLastYearEndTime()));
			}
		}
		
		int bqCount = bzjqDao.getWffzCount(params); //本期
		int sqCount = bzjqDao.getWffzCount(hbparams); //上期
		int tbCount = bzjqDao.getWffzCount(tbparams); //同比
		
		result.put("bq", bqCount);//本期
		result.put("sq", sqCount);//上期

		result.put("hb", getTbHb(sqCount,bqCount));//环比
		result.put("tq", tbCount);//同期

		result.put("tb",getTbHb(tbCount,bqCount));//计算同比
		return result;
	}


	
	
}
