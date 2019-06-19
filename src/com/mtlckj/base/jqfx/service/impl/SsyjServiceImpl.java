package com.mtlckj.base.jqfx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.Lbtj;
import com.mtlckj.base.jqfx.domain.Ssyj;
import com.mtlckj.base.jqfx.mapper.SsyjMapper;
import com.mtlckj.base.jqfx.service.SsyjService;
import com.mtlckj.base.jqfx.utils.DateUtils;
import com.mtlckj.base.jqfx.vo.SsyjHotMark;
import com.mtlckj.base.jqfx.vo.Xqajlb;
import com.mtlckj.base.jqfx.vo.ZsFourMOY;

/**
 * <p>
 * Title: HddjqServiceImpl
 * </p>
 * <p>
 * Package: com.mtlckj.base.jqfx.service.impl
 * </p>
 * <p>
 * Description: 四色预警impl实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author liangxiao
 * @date 2018年10月24日
 * @version 1.0
 */
@Service
public class SsyjServiceImpl implements SsyjService {
	
	
	
	@Autowired
	private SsyjMapper ssyjDao;

	@Override
	public Ssyj countByWeek(Map<String, Object> map) {
		return ssyjDao.countByWeek(map);
	}

	@Override
	public List<SsyjHotMark> listZpByWeek(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ssyjDao.listZpByWeek(map);
	}

	@Override
	public List<SsyjHotMark> listQcByWeek(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ssyjDao.listQcByWeek(map);
	}

	@Override
	public List<SsyjHotMark> listLqByWeek(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ssyjDao.listLqByWeek(map);
	}

	@Override
	public List<SsyjHotMark> listRsdqByWeek(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ssyjDao.listRsdqByWeek(map);
	}

	@Override
	public List<SsyjHotMark> listScdqByWeek(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ssyjDao.listScdqByWeek(map);
	}

	@Override
	public List<SsyjHotMark> listMdByWeek(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ssyjDao.listMdByWeek(map);
	}

	@Override
	public int countZsByWeek(Map<String, Object> map) {
		
		return ssyjDao.countZsByWeek(map);
	}

	@Override
	public Xqajlb xqajlbTj(Map<String, Object> map) {
		Xqajlb xqajlb=new Xqajlb();
		String start=(String)map.get("start");
		Map<String,Object> lastyearMap=DateUtils.getLastFirstYearMonth(start);
		map.put("qnStart", lastyearMap.get("startTime"));
		map.put("qnEnd", lastyearMap.get("endTime"));
		Map<String,Object> lastSecondMap=DateUtils.getLastSecendYearMonth(start);
		map.put("qqnStart", lastSecondMap.get("startTime"));
		map.put("qqnEnd", lastSecondMap.get("endTime"));
		Map<String,Object> lastThirdMap=DateUtils.getLastThirdYearMonth(start);
		map.put("sqnStart", lastThirdMap.get("startTime"));
		map.put("sqnEnd", lastThirdMap.get("endTime"));
		/**
		 * 获取三年的当月的天数		
		 */
		Integer lastFirstYearDayNumOfMon = DateUtils.getDateSubtract((String)map.get("qnStart"), (String)map.get("qnEnd"));
		Integer lastSecondYearDayNumOfMon = DateUtils.getDateSubtract((String)map.get("qqnStart"), (String)map.get("qqnEnd"));
		Integer lastThirdYearDayNumOfMon = DateUtils.getDateSubtract((String)map.get("sqnStart"), (String)map.get("sqnEnd"));
		
		Lbtj zplbtj=ssyjDao.zpZsFourMOY(map);
		ZsFourMOY zpZsFourMOY = new ZsFourMOY(zplbtj.getZzs(),zplbtj.getLastFirstYearMonthzs(),
				zplbtj.getLastSecondYearMonthzs(),zplbtj.getLastThirdYearMonthzs());
		zpZsFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		zpZsFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		zpZsFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		zpZsFourMOY.setThreeYearZcl();
		zpZsFourMOY.setFdbvAndKfdbv();
		xqajlb.setZp(zpZsFourMOY);
		
		Lbtj qclbtj=ssyjDao.qcZsFourMOY(map);
		ZsFourMOY qcZsFourMOY = new ZsFourMOY(qclbtj.getZzs(),qclbtj.getLastFirstYearMonthzs(),
				qclbtj.getLastSecondYearMonthzs(),qclbtj.getLastThirdYearMonthzs());
		qcZsFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		qcZsFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		qcZsFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		qcZsFourMOY.setThreeYearZcl();
		qcZsFourMOY.setFdbvAndKfdbv();
		xqajlb.setQc(qcZsFourMOY);
		
		Lbtj lqlbtj=ssyjDao.lqZsFourMOY(map);
		ZsFourMOY lqZsFourMOY =  new ZsFourMOY(lqlbtj.getZzs(),lqlbtj.getLastFirstYearMonthzs(),
				lqlbtj.getLastSecondYearMonthzs(),lqlbtj.getLastThirdYearMonthzs());
		lqZsFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		lqZsFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		lqZsFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		lqZsFourMOY.setThreeYearZcl();
		lqZsFourMOY.setFdbvAndKfdbv();
		xqajlb.setLq(lqZsFourMOY);
		
		
		Lbtj rsdqlbtj=ssyjDao.rsdqZsFourMOY(map);
		ZsFourMOY rsdqZsFourMOY = new ZsFourMOY(rsdqlbtj.getZzs(),rsdqlbtj.getLastFirstYearMonthzs(),
				rsdqlbtj.getLastSecondYearMonthzs(),rsdqlbtj.getLastThirdYearMonthzs());
		rsdqZsFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		rsdqZsFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		rsdqZsFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		rsdqZsFourMOY.setThreeYearZcl();
		rsdqZsFourMOY.setFdbvAndKfdbv();
		xqajlb.setRsdq(rsdqZsFourMOY);
		
		
		Lbtj scdqlbtj = ssyjDao.scdqZsFourMOY(map);
		ZsFourMOY scdqFourMOY = new ZsFourMOY(scdqlbtj.getZzs(),scdqlbtj.getLastFirstYearMonthzs(),
				scdqlbtj.getLastSecondYearMonthzs(),scdqlbtj.getLastThirdYearMonthzs());
		scdqFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		scdqFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		scdqFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		scdqFourMOY.setThreeYearZcl();
		scdqFourMOY.setFdbvAndKfdbv();
		xqajlb.setScdq(scdqFourMOY);
		
		Lbtj mdlbtj = ssyjDao.mdZsFourMOY(map);
		ZsFourMOY mdFourMOY = new ZsFourMOY(mdlbtj.getZzs(),mdlbtj.getLastFirstYearMonthzs(),
				mdlbtj.getLastSecondYearMonthzs(),mdlbtj.getLastThirdYearMonthzs());
		mdFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		mdFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		mdFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		mdFourMOY.setThreeYearZcl();
		mdFourMOY.setFdbvAndKfdbv();
		xqajlb.setMd(mdFourMOY);
		
		Lbtj zslbtj = ssyjDao.wfjqZsFourMOY(map);
		ZsFourMOY wfjqZsFourMOY = new ZsFourMOY(zslbtj.getZzs(),zslbtj.getLastFirstYearMonthzs(),
				zslbtj.getLastSecondYearMonthzs(),zslbtj.getLastThirdYearMonthzs());
		wfjqZsFourMOY.setLastFirstYearDays(lastFirstYearDayNumOfMon);
		wfjqZsFourMOY.setLastSecondYearDays(lastSecondYearDayNumOfMon);
		wfjqZsFourMOY.setLastThirdYearDays(lastThirdYearDayNumOfMon);
		wfjqZsFourMOY.setThreeYearZcl();
		wfjqZsFourMOY.setFdbvAndKfdbv();
		xqajlb.setWfjq(wfjqZsFourMOY);
		
		
		xqajlb.setXqMapColor();
		
		return xqajlb;
	}

}
