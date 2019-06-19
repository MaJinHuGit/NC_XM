package com.mtlckj.base.jqfx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mtlckj.base.common.domain.DzfxDO;
import com.mtlckj.base.common.service.DzfxService;
import com.mtlckj.base.jqfx.domain.ColumnDO;
import com.mtlckj.base.jqfx.domain.TableDO;
import com.mtlckj.base.jqfx.service.AnalysisService;
import com.mtlckj.base.jqfx.service.ColumnDoService;
import com.mtlckj.base.jqfx.service.TableDoService;
import com.mtlckj.base.jqfx.vo.AnalysisParam;
import com.mtlckj.base.jqfx.vo.DocTreeVo;
import com.mtlckj.base.jqfx.vo.Filter;
import com.mtlckj.base.jqfx.vo.Zb;
import com.mtlckj.base.system.domain.ModuleDO;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.Query;

/**
 * 定制分析用Controller
 * @author 杨智强
 *
 */

@Controller
@RequestMapping(value="analysis")
public class AnalysisController {
	@Autowired
	private TableDoService tableserivce;
	
	@Autowired
	private ColumnDoService columnDoservice;
	
	@Autowired
	private AnalysisService analysisService;
	
	@Autowired
	private DzfxService dzfxService;
	
	@GetMapping(value="selectTable")
	@ResponseBody
	public List<TableDO> listTable(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String endDate="2019-01-02 00:00:00";
		String startDate="2018-12-31 00:00:00";
		Date startTime=null;
		Date endTime=null;
		try {
			startTime=format.parse(startDate);
			endTime=format.parse(endDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.add(Calendar.YEAR,-1);
		System.out.println("StartTime+"+format.format(calendar.getTime()));
		calendar.setTime(endTime);
		calendar.add(Calendar.YEAR,-1);
		System.out.println("endTime+"+format.format(calendar.getTime()));
		return tableserivce.listTable();
	}
	
	@GetMapping(value="selectCol")
	@ResponseBody
	public List<ColumnDO> listColumn(@RequestParam(name="tableName") String tableName){
		return columnDoservice.listConlumnByTableName(tableName);
	}
	@GetMapping(value="getzhibiao")
	@ResponseBody
	public List<DocTreeVo> getZhibiao(@RequestParam(name="tableName") String tableName){
		return columnDoservice.getZhibiaoByTableName(tableName);
	}
	
	@PostMapping(value="startAnalysis")
	@ResponseBody
	public List<List<Object>> startAnalysis(@RequestParam("data") String param){
		JSONObject jsonObject=JSONObject.parseObject(param);
		AnalysisParam analysisParam=JSONObject.toJavaObject(jsonObject, AnalysisParam.class);
		
		List<List<Object>> resultList=analysisService.analysis(analysisParam);
		
		return resultList;
	}
	
	@GetMapping(value="historylist")
	@ResponseBody
	public PageUtils getHistoryList(@RequestParam Map<String, Object> params) {
		Query query=new Query(params);
		List<DzfxDO> modules=dzfxService.list(query);
		int total=dzfxService.count(query);
		PageUtils pageUtil=new PageUtils(modules, total);
		return pageUtil;
	}
}
