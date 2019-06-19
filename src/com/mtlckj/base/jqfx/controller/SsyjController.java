package com.mtlckj.base.jqfx.controller;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.domain.Ssyj;
import com.mtlckj.base.jqfx.service.SsyjService;
import com.mtlckj.base.jqfx.utils.DateUtils;
import com.mtlckj.base.jqfx.vo.SsyjHotMark;
import com.mtlckj.base.jqfx.vo.SsyjVo;
import com.mtlckj.base.jqfx.vo.Xqajlb;
import com.mtlckj.base.system.utils.R;


/**
 * <p>Title: HddjqController</p> 
 * <p>Package: com.mtlckj.base.jqfx.controller</p>  
 * <p>Description: 四色预警Controller</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author liangxiao 
 * @date 2018年10月24日   
 * @version 1.0
 */
@RequestMapping("/jqfx/ssyj")
@Controller
public class SsyjController {
	
	@Autowired
	private SsyjService ssyjService;
	
	@ResponseBody
	@GetMapping("/countByWeek")
	public R countByWeek(@RequestParam Map<String, Object> params) {
		String start = (String) params.get("start");
		String end = (String) params.get("end");
		String orgcode = (String) params.get("orgcode");
		String weeknum = (String) params.get("weeknum");
		Map<String,Object> map = new HashMap<String,Object>();

		if(StringUtils.isEmpty(orgcode)||"0".equals(orgcode)){
			orgcode = "";
			params.put("orgcode", "");
		}else{
			//数据库查询orgname
			params.put("orgcode", orgcode);
		}
		
		//周数判断
		if(!StringUtils.isEmpty(weeknum)){
			String[] weeknums =  weeknum.split("-");
			start = DateUtils.DateToString(DateUtils.getStartDayOfWeek(Integer.valueOf(weeknums[0]),Integer.valueOf(weeknums[1])-1));
			end = DateUtils.DateToString(DateUtils.getEndDayOfWeek(Integer.valueOf(weeknums[0]),Integer.valueOf(weeknums[1])-1));
		}

		//本周
		if(StringUtils.isEmpty(start)){
			start = DateUtils.DateToString(DateUtils.getThisWeekStart());
			end = DateUtils.DateToString(DateUtils.getThisWeekEnd());
		}
		params.put("start", start);
		params.put("end", end);
		Ssyj bz = ssyjService.countByWeek(params);
		map.put("startTime", start);
		map.put("endTime", end);
		
		//前一周
		String qyzStart = DateUtils.DateToString(DateUtils.getLastWeekIntervalStart(start));
		end = DateUtils.DateToString(DateUtils.getLastWeekIntervalEnd(DateUtils.getLastWeekIntervalStart(start)));
		params.put("start", qyzStart);
		params.put("end", end);
		Ssyj qyz = ssyjService.countByWeek(params);
		
		//前两周
		String qlzStart = DateUtils.DateToString(DateUtils.getLastWeekIntervalStart(qyzStart));
		end = DateUtils.DateToString(DateUtils.getLastWeekIntervalEnd(DateUtils.getLastWeekIntervalStart(qyzStart)));
		params.put("start", qlzStart);
		params.put("end", end);
		Ssyj qlz = ssyjService.countByWeek(params);
		
		//前三周
		String qszStart = DateUtils.DateToString(DateUtils.getLastWeekIntervalStart(qlzStart));
		end = DateUtils.DateToString(DateUtils.getLastWeekIntervalEnd(DateUtils.getLastWeekIntervalStart(qlzStart)));
		params.put("start", qszStart);
		params.put("end", end);
		Ssyj qsz = ssyjService.countByWeek(params);
		DecimalFormat df=new DecimalFormat("#.##");
		Double pjz = 0d;
		Double zs = 0d;
		Double qyzwfjq = 0d;
		
		SsyjVo wfjqVo = new SsyjVo();//违法犯罪
		wfjqVo.setJqsl(bz.getWfjq());
		pjz = Double.valueOf(bz.getWfjq())+Double.valueOf(qyz.getWfjq())+Double.valueOf(qlz.getWfjq())+Double.valueOf(qsz.getWfjq());
		wfjqVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getWfjq())-Double.valueOf(qyz.getWfjq());
		qyzwfjq = Double.valueOf(qyz.getWfjq());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		
		
		wfjqVo.setZs(df.format(zs*100)+"%");
		wfjqVo.setOrgname(orgcode);
		map.put("wfjq", wfjqVo);
		
		SsyjVo qcVo = new SsyjVo(); //侵财
		qcVo.setJqsl(bz.getQc());
		pjz = Double.valueOf(bz.getQc())+Double.valueOf(qyz.getQc())+Double.valueOf(qlz.getQc())+Double.valueOf(qsz.getQc());
		qcVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getQc())-Double.valueOf(qyz.getQc());
		qyzwfjq = Double.valueOf(qyz.getQc());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		qcVo.setZs(df.format(zs*100)+"%");
		qcVo.setOrgname(orgcode);
		map.put("qc", qcVo);

		
		SsyjVo lqVo = new SsyjVo(); //两抢
		lqVo.setJqsl(bz.getLq());
		pjz = Double.valueOf(bz.getLq())+Double.valueOf(qyz.getLq())+Double.valueOf(qlz.getLq())+Double.valueOf(qsz.getLq());
		lqVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getLq())-Double.valueOf(qyz.getLq());
		qyzwfjq = Double.valueOf(qyz.getLq());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		lqVo.setZs(df.format(zs*100)+"%");
		lqVo.setOrgname(orgcode);
		map.put("lq", lqVo);
		
		SsyjVo rsdqVo = new SsyjVo(); //入室盗窃
		rsdqVo.setJqsl(bz.getRsdq());
		pjz = Double.valueOf(bz.getRsdq())+Double.valueOf(qyz.getRsdq())+Double.valueOf(qlz.getRsdq())+Double.valueOf(qsz.getRsdq());
		rsdqVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getRsdq())-Double.valueOf(qyz.getRsdq());
		qyzwfjq = Double.valueOf(qyz.getRsdq());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		rsdqVo.setZs(df.format(zs*100)+"%");
		rsdqVo.setOrgname(orgcode);
		map.put("rsdq", rsdqVo);

		
		SsyjVo scdqVo = new SsyjVo(); //涉车盗窃
		scdqVo.setJqsl(bz.getScdq());
		pjz = Double.valueOf(bz.getScdq())+Double.valueOf(qyz.getScdq())+Double.valueOf(qlz.getScdq())+Double.valueOf(qsz.getScdq());
		scdqVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getScdq())-Double.valueOf(qyz.getScdq());
		qyzwfjq = Double.valueOf(qyz.getScdq());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		scdqVo.setZs(df.format(zs*100)+"%");
		scdqVo.setOrgname(orgcode);
		map.put("scdq", scdqVo);

		SsyjVo zpVo = new SsyjVo(); //诈骗
		zpVo.setJqsl(bz.getZp());
		pjz = Double.valueOf(bz.getZp())+Double.valueOf(qyz.getZp())+Double.valueOf(qlz.getZp())+Double.valueOf(qsz.getZp());
		zpVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getZp())-Double.valueOf(qyz.getZp());
		qyzwfjq = Double.valueOf(qyz.getZp());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		zpVo.setZs(df.format(zs*100)+"%");
		zpVo.setOrgname(orgcode);
		map.put("zp", zpVo);

		SsyjVo mdVo = new SsyjVo(); //摩托车电动自行车
		mdVo.setJqsl(bz.getMd());
		pjz = Double.valueOf(bz.getMd())+Double.valueOf(qyz.getMd())+Double.valueOf(qlz.getMd())+Double.valueOf(qsz.getMd());
		mdVo.setSzjz(df.format(pjz/4));
		zs = Double.valueOf(bz.getMd())-Double.valueOf(qyz.getMd());
		qyzwfjq = Double.valueOf(qyz.getMd());
		if(qyzwfjq>0){
			zs = zs/qyzwfjq;
		}
		zs = Double.isNaN(zs)?0:zs;
		mdVo.setZs(df.format(zs*100)+"%");
		mdVo.setOrgname(orgcode);
		map.put("md", mdVo);
				
		return R.ok().put("data",map);
	}
	
	@ResponseBody
	@GetMapping("/getDate")
	public R getDate() {
		
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		//本年度
		String year = String.valueOf(DateUtils.getNowYear());
		int weeknum = DateUtils.getMaxWeekNumOfYear(Integer.valueOf(year));
		
		for(int i = 0;i<=weeknum;i++) {
			String s = String.valueOf(i+1);
			map.put(year+"-"+s, String.valueOf(year)+"年第"+s+"周 ("+DateUtils.DateToString(DateUtils.getStartDayOfWeek(Integer.valueOf(year),i),"MM/dd")+"-"+DateUtils.DateToString(DateUtils.getEndDayOfWeek(Integer.valueOf(year),i),"MM/dd")+")");
		}
		
//		//上一年
//		year = String.valueOf(DateUtils.getNowYear()-1);
//		weeknum = DateUtils.getMaxWeekNumOfYear(Integer.valueOf(year));
//		for(int i = 0;i<=weeknum;i++) {
//			String s = String.valueOf(i+1);
//			map.put(year+"-"+s, String.valueOf(year)+"年第"+s+"周 ("+DateUtils.DateToString(DateUtils.getStartDayOfWeek(Integer.valueOf(year),i),"MM/dd")+"-"+DateUtils.DateToString(DateUtils.getEndDayOfWeek(Integer.valueOf(year),i),"MM/dd")+")");
//		}
		
		return R.ok().put("data", map);
	}
	/**
	 * 获取地图热力图
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getHotMark")
	public R getHotMark(@RequestParam Map<String,Object> params){
		String start = (String) params.get("start");
		String end = (String) params.get("end");
		String orgcode = (String) params.get("orgcode");
		String weeknum = (String) params.get("weeknum");
		Map<String,Object> map = new HashMap<String,Object>();

		if(StringUtils.isEmpty(orgcode)||"0".equals(orgcode)){
			orgcode = "";
			params.put("orgcode", "");
		}else{
			//数据库查询orgname
			params.put("orgcode", orgcode);
		}
		
		//周数判断
		if(!StringUtils.isEmpty(weeknum)){
			String[] weeknums =  weeknum.split("-");
			start = DateUtils.DateToString(DateUtils.getStartDayOfWeek(Integer.valueOf(weeknums[0]),Integer.valueOf(weeknums[1])-1));
			end = DateUtils.DateToString(DateUtils.getEndDayOfWeek(Integer.valueOf(weeknums[0]),Integer.valueOf(weeknums[1])-1));
		}

		//本周
		if(StringUtils.isEmpty(start)){
			start = DateUtils.DateToString(DateUtils.getThisWeekStart());
			end = DateUtils.DateToString(DateUtils.getThisWeekEnd());
		}
		params.put("start", start);
		params.put("end", end);
		/**
		 * 获取诈骗热点信息
		 */
		List<SsyjHotMark> zpHotMarks= ssyjService.listZpByWeek(params);
		map.put("zpHotMarks",zpHotMarks);
		/**
		 * 获取侵财热点信息
		 */
		List<SsyjHotMark> qcHotMarks= ssyjService.listQcByWeek(params);
		map.put("qcHotMarks",qcHotMarks);
		/**
		 * 获取两抢热点信息
		 */
		List<SsyjHotMark> lqHotMarks= ssyjService.listLqByWeek(params);
		map.put("lqHotMarks",lqHotMarks);
		/**
		 * 获取入室盗窃热点信息
		 */
		List<SsyjHotMark> rsdqHotMarks= ssyjService.listRsdqByWeek(params);
		map.put("rsdqHotMarks",rsdqHotMarks);
		/**
		 * 获取涉车盗窃热点信息
		 */
		List<SsyjHotMark> scdqHotMarks= ssyjService.listScdqByWeek(params);
		map.put("scdqHotMarks",scdqHotMarks);
		/**
		 * 获取摩电热点信息
		 */
		List<SsyjHotMark> mdHotMarks= ssyjService.listMdByWeek(params);
		map.put("mdHotMarks",mdHotMarks);
		return R.ok().put("data",map);
	}
	
	@ResponseBody
	@GetMapping("/getXqCount")
	public R getXqCount(@RequestParam Map<String,Object> params){
		String start = (String) params.get("start");
		String end = (String) params.get("end");
		String weeknum = (String) params.get("weeknum");
		Map<String,Object> resultMap=new HashMap<>();
		//周数判断
		if(!StringUtils.isEmpty(weeknum)){
			String[] weeknums =  weeknum.split("-");
			start = DateUtils.DateToString(DateUtils.getStartDayOfWeek(Integer.valueOf(weeknums[0]),Integer.valueOf(weeknums[1])-1));
			end = DateUtils.DateToString(DateUtils.getEndDayOfWeek(Integer.valueOf(weeknums[0]),Integer.valueOf(weeknums[1])-1));
		}
		//本周
		if(StringUtils.isEmpty(start)){
			start = DateUtils.DateToString(DateUtils.getThisWeekStart());
			end = DateUtils.DateToString(DateUtils.getThisWeekEnd());
		}
		params.put("start", start);
		params.put("end", end);
		Xqajlb xqajlb=ssyjService.xqajlbTj(params);
		resultMap.put("xqajlb", xqajlb);
		resultMap.put("startTime", start);
		resultMap.put("endTime", end);

		return R.ok().put("data", resultMap);
	}
	
}
