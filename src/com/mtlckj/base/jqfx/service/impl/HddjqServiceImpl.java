package com.mtlckj.base.jqfx.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.Hddjq;
import com.mtlckj.base.jqfx.mapper.HddjqMapper;
import com.mtlckj.base.jqfx.service.HddjqService;
import com.mtlckj.base.jqfx.utils.DateUtils;
import com.mtlckj.base.jqfx.vo.HddjqVo;

/**
 * <p>Title: HddjqServiceImpl</p> 
 * <p>Package: com.mtlckj.base.jqfx.service.impl</p>  
 * <p>Description: 黄赌毒警情impl实现</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author majinhu  
 * @date 2018年10月19日   
 * @version 1.0
 */
@Service
public class HddjqServiceImpl implements HddjqService {
    @Autowired
    private HddjqMapper hddjqDao;
	
	@Override
	public List<Hddjq> getList(Map<String, Object> params) {
		String zxsj = (String) params.get("zxsj");
		String ksms = (String) params.get("ksms");
		String startTime = "";
		String endTime = "";

		if(StringUtils.isNotEmpty(zxsj)){
			String[] str = zxsj.split("-");
			startTime = str[0]+"-"+str[1]+"-"+str[2]+"00:00:00";
			endTime = str[3]+"-"+str[4]+"-"+str[5]+" 23:59:59";
		}else{
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
		}
		params.put("start", startTime);
		params.put("end", endTime);
		return hddjqDao.getList(params);
	}


	@Override
	public List<HddjqVo> getHddjqByBldw(Map<String, Object> params) {
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");
		
		List<HddjqVo> hddjqList = new ArrayList<>();
		if(StringUtils.isNotEmpty(ksms)||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
			//默认和快速模式
			hddjqList = countByKsms(params);
		}else{
			//自选时间
			hddjqList = countByZxsj(params);

		}
		return hddjqList;
	}
	
	
	/**
	 * 默认查询和快速模式查询
	 * @param params
	 * @return
	 */
	private List<HddjqVo> countByKsms(Map<String, Object> map){
		Map<String,Object> params = new HashMap<String,Object>();
		params.putAll(map);
		Map<String,Object> hbparams = new HashMap<String,Object>();//环比时间
		Map<String,Object> tbparams = new HashMap<String,Object>();//同比时间
		hbparams.putAll(map);
		tbparams.putAll(map);

		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String ksms = (String) params.get("ksms");
		String pcsName = (String)params.get("bldw");

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
		
		List<HddjqVo> bqList = hddjqDao.getHddjqByBldw(params); //本期
		List<HddjqVo> sqList = hddjqDao.getHddjqByBldw(hbparams); //上期
		List<HddjqVo> tbList = hddjqDao.getHddjqByBldw(tbparams); //同比
		
		bqList = addEmpty(pcsName,bqList);
		sqList = addEmpty(pcsName,sqList);
		tbList = addEmpty(pcsName,tbList);
		
		bqList = contrastdb(bqList,tbList);
		bqList = contrasthz(bqList,sqList);

		return bqList;
	}
	
	
	/**
	 * 自选时间
	 * @param params
	 * @return
	 */
	private List<HddjqVo> countByZxsj(Map<String, Object> map){
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> hbparams = new HashMap<String,Object>();//环比时间
		Map<String,Object> tbparams = new HashMap<String,Object>();//同比时间
		params.putAll(map);
		hbparams.putAll(map);
		tbparams.putAll(map);
		String startTime = (String) params.get("start");
		String endTime = (String) params.get("end");
		String pcsName = (String)params.get("bldw");
		int day = DateUtils.getDifferenceDay(startTime,endTime);
		
		//环比
		hbparams.put("start",DateUtils.getLastTimeForRing(startTime,day));
		hbparams.put("end", DateUtils.getLastTimeForRing(endTime,day));
		//同比
		tbparams.put("start",DateUtils.getLastTimeForWith(startTime,day));
		tbparams.put("end", DateUtils.getLastTimeForWith(endTime,day));
		
		List<HddjqVo> bqList = hddjqDao.getHddjqByBldw(params); //本期
		List<HddjqVo> sqList = hddjqDao.getHddjqByBldw(hbparams); //上期
		List<HddjqVo> tbList = hddjqDao.getHddjqByBldw(tbparams); //同比
		
		bqList = addEmpty(pcsName,bqList);
		sqList = addEmpty(pcsName,sqList);
		tbList = addEmpty(pcsName,tbList);
		
		bqList = contrastdb(bqList,tbList);
		bqList = contrasthz(bqList,sqList);
		return bqList;
	}
	
	
	/**
	 * 无数据补全
	 * @param params
	 * @return
	 */
	private List<HddjqVo> addEmpty(String pcsName,List<HddjqVo> list){
		int size = list.size(); 
		if(size>0&&size<3){
			HddjqVo shjq = null;
			HddjqVo dbjq = null;
			HddjqVo sdjq = null;
			for (int i = 0; i < size; i++) {
				HddjqVo hddjqVo = list.get(i);
				if(hddjqVo.getLb().equals("01")) {
					shjq = hddjqVo;
				}
				if(hddjqVo.getLb().equals("02")) {
					dbjq = hddjqVo;
				}
				if(hddjqVo.getLb().equals("03")) {
					sdjq = hddjqVo;
				}
			}
			if(shjq==null) {
				shjq = new HddjqVo();
				shjq.setPcsName(pcsName);
				shjq.setJqs("0");
				shjq.setLb("01");
				list.add(shjq);
			}
			if(dbjq==null) {
				dbjq = new HddjqVo();
				dbjq.setPcsName(pcsName);
				dbjq.setJqs("0");
				dbjq.setLb("02");
				list.add(dbjq);
			}
			if(sdjq==null) {
				sdjq = new HddjqVo();
				sdjq.setPcsName(pcsName);
				sdjq.setJqs("0");
				sdjq.setLb("03");
				list.add(sdjq);
			}
		}
		if(size==0){
			HddjqVo hddjq1= new HddjqVo();
			hddjq1.setPcsName(pcsName);
			hddjq1.setJqs("0");
			hddjq1.setLb("01");
			list.add(hddjq1);
			HddjqVo hddjq2 = new HddjqVo();
			hddjq2.setPcsName(pcsName);
			hddjq2.setJqs("0");
			hddjq2.setLb("02");
			list.add(hddjq2);
			HddjqVo hddjq3 = new HddjqVo();
			hddjq3.setPcsName(pcsName);
			hddjq3.setJqs("0");
			hddjq3.setLb("03");
			list.add(hddjq3);
		}
		return list;
	}
	
	/**
	 * 汇总表格计算
	 * @param list
	 * @return
	 */
	private List<HddjqVo> contrasthz(List<HddjqVo> bqList,List<HddjqVo> sqList){
		int bqhj = Integer.valueOf(bqList.get(0).getJqs())+Integer.valueOf(bqList.get(1).getJqs())+Integer.valueOf(bqList.get(2).getJqs());
		int sqhj = Integer.valueOf(sqList.get(0).getJqs())+Integer.valueOf(sqList.get(1).getJqs())+Integer.valueOf(sqList.get(2).getJqs());
		HddjqVo hddjqVo = new HddjqVo();
		hddjqVo.setJqs(String.valueOf(bqhj));
		hddjqVo.setLb("hj");

		if(bqhj>sqhj) {
			hddjqVo.setJqs(String.valueOf(bqhj)+"↑");
		}
		if(bqhj<sqhj) {
			hddjqVo.setJqs(String.valueOf(bqhj)+"↓");
		}
		
		for (HddjqVo bqHddjq : bqList) {
			for (HddjqVo sqHddjq : sqList) {
				if(bqHddjq.getLb().equals(sqHddjq.getLb())){
					int bqjqs = Integer.valueOf(bqHddjq.getJqs());
					int sqjqs = Integer.valueOf(sqHddjq.getJqs());
					if(bqjqs>sqjqs) {
						bqHddjq.setJqs(bqHddjq.getJqs()+"↑");
					}
					if(bqjqs<sqjqs) {
						bqHddjq.setJqs(bqHddjq.getJqs()+"↓");
					}
				}
			}
		}
		bqList.add(hddjqVo);
		return bqList;
	}
	
	
	
	/**
	 * 对比表格计算
	 * @param list
	 * @return
	 */
	private List<HddjqVo> contrastdb(List<HddjqVo> bqList,List<HddjqVo> tbList){
		for (HddjqVo bqHddjq : bqList) {
			for (HddjqVo tbHddjq : tbList) {
				if(bqHddjq.getLb().equals(tbHddjq.getLb())){
					bqHddjq.setTqjqs(tbHddjq.getJqs());
					int bqjq = Integer.valueOf(bqHddjq.getJqs());
					int tbjq = Integer.valueOf(tbHddjq.getJqs());

					int tb = bqjq*100;
					if(tbjq!=0){
						double i = bqjq-tbjq;
						i = i/tbjq;
						tb = (int) (i * 100);
					}
					bqHddjq.setTb(String.valueOf(tb));
				}
			}
		}
		return bqList;
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return hddjqDao.count(params);
	}

}
