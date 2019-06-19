package com.mtlckj.base.jqfx.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.domain.Details;
import com.mtlckj.base.jqfx.service.DetailService;
import com.mtlckj.base.jqfx.utils.DateUtils;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.R;

/**
 * 点击数字显示详细信息
 * @author 王军
 *
 */
@Controller
@RequestMapping("/jqfx/showDetail")
public class DetailController {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private DetailService detailService;
	/**
	 * 详细列表中获取单条数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/one")
	public R getOne(String id) {
		Details detail = detailService.getOne(id);
		return R.ok().put("data", detail);
	}
	
	/**
	 * 获取派出所、案件类型对应的数据列表
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@GetMapping("/detil")
	public PageUtils showDetil(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		String name = URLDecoder.decode((String) params.get("name"), "UTF-8");
		String type = URLDecoder.decode((String) params.get("type"), "UTF-8");
		String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
		String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
		String search = URLDecoder.decode((String) params.get("search"), "UTF-8");
		if(search != null && search != "") {
			search = "%"+search+"%";
		}
		System.out.println(search);
		params.put("search", search);
		params.put("name", name);
		params.put("type", type);
		if(params.get("startTime").equals("lastYear")) {
			//去年合计--lastYear
			params.put("startTime", sdf.format(DateUtils.getLastYearStartTime()));
			params.put("endTime", sdf.format(DateUtils.getLastYearEndTime()));
		}else if(params.get("startTime").equals("year") || params.get("startTime").equals("bn")) {
			//本年合计--year
			params.put("startTime", sdf.format(DateUtils.getThisYearStart()));
			params.put("endTime", sdf.format(DateUtils.getThisYearEnd()));
		}else if(params.get("startTime").equals("lastJD")) {
			//上季度合计--lastJD
			params.put("startTime", sdf.format(DateUtils.getLastQuarterStart()));
			params.put("endTime", sdf.format(DateUtils.getLastQuarterEnd()));
		}else if(params.get("startTime").equals("JD") || params.get("startTime").equals("bj")) {
			//本季度合计--JD
			params.put("startTime", sdf.format(DateUtils.getThisQuarterStart()));
			params.put("endTime", sdf.format(DateUtils.getThisQuarterEnd()));
		}else if(params.get("startTime").equals("lastYearJD")) {
			//去年同季度常量--lastYearJD
			params.put("startTime", sdf.format(getLastYearQuarterStart()));
			params.put("endTime", sdf.format(getLastYearQuarterEnd()));
		}else if(params.get("startTime").equals("lastMonth")) {
			//上月合计--lastMonth
			params.put("startTime", sdf.format(DateUtils.getLastMonthStart()));
			params.put("endTime", sdf.format(DateUtils.getLastMonthEnd()));
		}else if(params.get("startTime").equals("month") || params.get("startTime").equals("by")) {
			//本月合计--month'
			params.put("startTime", sdf.format(DateUtils.getThisMonthStart()));
			params.put("endTime", sdf.format(DateUtils.getThisMonthEnd()));
		}else if(params.get("startTime").equals("lastYearMonth")) {
			//去年同月常量--lastYearMonth
			params.put("startTime", sdf.format(getLastYearMonthStart()));
			params.put("endTime", sdf.format(getLastYearMonthEnd()));
		}else if(params.get("startTime").equals("lastWeek")) {
			//上周合计--lastWeek
			params.put("startTime", sdf.format(DateUtils.getLastWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getLastWeekEnd()));
		}else if(params.get("startTime").equals("week") || params.get("startTime").equals("bz") || params.get("startTime") == null) {
			//本周合计--week'
			params.put("startTime", sdf.format(DateUtils.getThisWeekStart()));
			params.put("endTime", sdf.format(DateUtils.getThisWeekEnd()));
		}else if(params.get("startTime").equals("lastYearWeek")) {
			//去年同周常量--lastYearWeek
			params.put("startTime", sdf.format(DateUtils.getLastYearWeekStartByNumber()));
			params.put("endTime", sdf.format(DateUtils.getLastYearWeekEndByNumber()));
		}else if(startTime.contains("oneYear")){
			params.put("startTime", sdf.format(getLastYear(startTime)));
			params.put("endTime", sdf.format(getLastYear(endTime)));
		}
		List<Details> list = null;
		int total = 0;
		if(type.contains("aa")) {
			list = detailService.showDetilNoType(params);
			total = getTotalNoType(params);
		}else {
			list = detailService.showDetil(params);
			total = getTotal(params);
		}
		PageUtils pu = new PageUtils(list, total);
		
		return pu;
	}
	
	

	/**
	 * 获取传入时间的前一年
	 */
	public static Date getLastYear(String str) {
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
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
	 * 获得上年的本季度结束时间
	 * 
	 * @return
	 */
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
	public static Date getLastYearQuarterStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getThisQuarterStart());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}
	
	/**
	 * 指定犯罪类型的分页统计
	 * @param params
	 * @return
	 */
	private int getTotal(Map<String, Object> params) {
		return detailService.getTotal(params);
	}

	/**
	 * 无犯罪类型的分页统计
	 * @param params
	 * @return
	 */
	private int getTotalNoType(Map<String, Object> params) {
		return detailService.getTotalNoType(params);
	}
}
