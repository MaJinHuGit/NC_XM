package com.mtlckj.base.jqfx.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSON;
import com.mtlckj.base.jqfx.mapper.AnalysisMapper;
import com.mtlckj.base.jqfx.service.AnalysisService;
import com.mtlckj.base.jqfx.vo.AnalysisParam;
import com.mtlckj.base.jqfx.vo.Filter;
import com.mtlckj.base.jqfx.vo.Zb;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	@Autowired
	private AnalysisMapper analysisMapper;
	
	List<Zb> thzbs;
	
	AnalysisParam thbAnaLysisParam;
	
	List<List<Object>> resultList;
	
	@Override
	public List<List<Object>> analysis(AnalysisParam analysisParam) {
		// TODO Auto-generated method stub
		thzbs=new ArrayList<>();
		String wdzs=analysisParam.getWdzs();
		System.out.println("维度注释:"+wdzs);
		thbAnaLysisParam=analysisParam;
		List<String> zbms=new ArrayList<String>();

		List<Object> zbs=new ArrayList<>();

		zbs.add(wdzs);
		resultList=new ArrayList<>();
		
		
		for(Zb zb:analysisParam.getZb()){
			System.out.println("获取指标名:"+zb.getZbm());
			List<Zb> thbzbs=new ArrayList<>();
			Iterator<Filter> itr=zb.getFilters().iterator();
			while(itr.hasNext()){
				Filter filter=itr.next();
				if(null!=filter.getExtraParam()&&!"".equals(filter.getExtraParam())){
					thbzbs=addTBHB(zb,filter);
					thzbs.addAll(thbzbs);	
				}
			}
			
			
			
			zbs.add(zb.getZbm());
			
			
			
			if(wdzs.equals(zb.getZbm())){
				zb.setZbm(wdzs+"1");
				zbms.add(zb.getZbm());
			}else{
				zbms.add(zb.getZbm());
			}
			for(Zb thbZb:thbzbs){
				zbs.add(thbZb.getZbm());
				zbms.add(thbZb.getZbm());
			}	
			
		}
		analysisParam.getZb().addAll(thzbs);
		resultList.add(zbs);
		
		List<Map<String,Object>> listmap= analysisMapper.analysis(analysisParam);
		System.out.println("zbs:"+JSON.toJSONString(zbs));
		System.out.println("zbms:"+JSON.toJSONString(zbms));
		System.out.println("listMap:"+JSON.toJSONString(listmap));
		for(Map<String,Object> mapobj:listmap){
			List<Object> rows=new ArrayList<>();
			if(null!=mapobj){
				String wd=String.valueOf(mapobj.get(wdzs));
				if(null!=mapobj.get(wdzs)&&!"null".equals(wd)){
					rows.add(wd);
				}else{
					rows.add("其他");
				}
			}else{
				rows.add("其他");
			}
			
			for(String zbm:zbms){
				if(zbm.contains("同比率")){
					String originZbm=zbm.substring(0, zbm.indexOf("(同比率)"));
					System.out.println("原始指标:"+zbm);
					System.out.println("同比指标名:"+originZbm);
					BigDecimal bq=(BigDecimal) mapobj.get(originZbm);
					BigDecimal tq=(BigDecimal) mapobj.get(zbm);
					BigDecimal tbl=new BigDecimal(0);
					if(tq.compareTo(BigDecimal.ZERO)==0 && bq.compareTo(BigDecimal.ZERO)!=0){
						tbl=new BigDecimal(100);
					}else if(bq.compareTo(BigDecimal.ZERO)==0 && bq.compareTo(BigDecimal.ZERO)==0){
						tbl=new BigDecimal(0);;
					}else{
						tbl=(bq.subtract(tq).divide(tq.abs())).multiply(new BigDecimal(100));
					}
					rows.add(tbl+"%");
				}else if(zbm.contains("环比率")){
					String originZbm=zbm.substring(0, zbm.indexOf("(环比率)"));
					BigDecimal bq=(BigDecimal) mapobj.get(originZbm);
					BigDecimal tq=(BigDecimal) mapobj.get(zbm);
					BigDecimal hbl=new BigDecimal(0);
					if(tq.compareTo(BigDecimal.ZERO)==0&&bq.compareTo(BigDecimal.ZERO)!=0){
						hbl=BigDecimal.valueOf(100);
					}else if(bq.compareTo(BigDecimal.ZERO)==0&&bq.compareTo(BigDecimal.ZERO)==0){
						hbl=BigDecimal.valueOf(0);
					}else{
						hbl=(bq.subtract(tq).divide(tq.abs())).multiply(new BigDecimal(100));
					}
					rows.add(hbl+"%");
				}else{
					rows.add(mapobj.get(zbm));
				}
			}
			resultList.add(rows);
		}
		
		System.out.println(JSON.toJSONString(resultList));
		return resultList;
	}
	
	private List<Zb> addTBHB(Zb zb,Filter filter){
		List<Zb> zbs=new ArrayList<>();
		boolean isDate=false;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(filter.getCondition().contains("date")){
			isDate=true;
		}
		String thbColName=filter.getColName();
		String originParam=filter.getOriginData();
		String[] datatimes=originParam.split("~");
		Date startTime = null;
		Date endTime=null;
		
		List<Filter> commonFilters=new ArrayList<>();
		commonFilters.addAll(zb.getFilters());
		commonFilters.remove(filter);
		
		try {
			startTime = format.parse(datatimes[0].trim());
			endTime = format.parse(datatimes[1].trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Calendar calendar=Calendar.getInstance();
		
		
		for(String extraParam:filter.getExtraParam()){
			switch(extraParam){
			case "tb":
				List<Filter> tbFilters=new ArrayList<>();
				Zb tbzb=new Zb();
				tbzb.setZbm(zb.getZbm()+"(同比)");
				calendar.setTime(startTime);
				calendar.add(Calendar.YEAR, -1);
				String tbStart=format.format(calendar.getTime());
				calendar.setTime(endTime);
				calendar.add(Calendar.YEAR, -1);
				String tbEnd=format.format(calendar.getTime());
				Filter tbFilter=new Filter();
				String tbcondition=null;
				if(isDate){
					tbcondition=thbColName+" <= "+"to_date('"+tbEnd+"','yyyy-mm-dd hh24:mi:ss') and "
							+thbColName+" >= "+"to_date('"+tbStart+"','yyyy-mm-dd hh24:mi:ss')";
				}else{
					tbcondition=thbColName+" <= "+"'"+tbEnd+"' and "
							+thbColName+" >= "+"'"+tbStart+"' ";
				}
				tbFilter.setCondition(tbcondition);
				tbFilter.setConditionName(thbColName+" 之间 "+tbStart+" ~ "+tbEnd);
				tbFilter.setLogicOp("and");
				tbFilter.setOriginData(tbStart+"~"+tbEnd);
				tbFilter.setColName(thbColName);
				tbFilters.add(tbFilter);
				tbFilters.addAll(commonFilters);
				tbzb.setFilters(tbFilters);
				zbs.add(tbzb);
				break;
			case "tbl":
				List<Filter> tblFilters=new ArrayList<>();
				Zb tblzb=new Zb();
				tblzb.setZbm(zb.getZbm()+"(同比率)");
				tblzb.setZbbm(zb.getZbbm());
				calendar.setTime(startTime);
				calendar.add(Calendar.YEAR, -1);
				String tblStart=format.format(calendar.getTime());
				calendar.setTime(endTime);
				calendar.add(Calendar.YEAR, -1);
				String tblEnd=format.format(calendar.getTime());
				Filter tblFilter=new Filter();
				String tblcondition=null;
				if(isDate){
					tblcondition=thbColName+" <= "+"to_date('"+tblEnd+"','yyyy-mm-dd hh24:mi:ss') and "
							+thbColName+" >= "+"to_date('"+tblStart+"','yyyy-mm-dd hh24:mi:ss')";
				}else{
					tblcondition=thbColName+" <= "+"'"+tblEnd+"' and "
							+thbColName+" >= "+"'"+tblStart+"' ";
				}
				tblFilter.setCondition(tblcondition);
				tblFilter.setConditionName(thbColName+" 之间 "+tblStart+" ~ "+tblEnd);
				tblFilter.setLogicOp("and");
				tblFilter.setOriginData(tblStart+"~"+tblEnd);
				tblFilters.add(tblFilter);
				tblFilters.addAll(commonFilters);
				tblzb.setFilters(tblFilters);
				zbs.add(tblzb);
				break;
			case "hb":
				List<Filter> hbFilters=new ArrayList<>();
				Zb hbzb=new Zb();
				hbzb.setZbm(zb.getZbm()+"(环比)");
				hbzb.setZbbm(zb.getZbbm());
				calendar.setTime(startTime);
				calendar.add(Calendar.MONTH, -1);
				String hbStart=format.format(calendar.getTime());
				calendar.setTime(endTime);
				calendar.add(Calendar.MONTH, -1);
				String hbEnd=format.format(calendar.getTime());
				Filter hbFilter=new Filter();
				String hbcondition=null;
				if(isDate){
					hbcondition=thbColName+" <= "+"to_date('"+hbEnd+"','yyyy-mm-dd hh24:mi:ss') and "
							+thbColName+" >= "+"to_date('"+hbStart+"','yyyy-mm-dd hh24:mi:ss')";
				}else{
					hbcondition=thbColName+" <= "+"'"+hbEnd+"' and "
							+thbColName+" >= "+"'"+hbStart+"' ";
				}
				hbFilter.setCondition(hbcondition);
				hbFilter.setConditionName(thbColName+" 之间 "+hbStart+" ~ "+hbEnd);
				hbFilter.setLogicOp("and");
				hbFilter.setOriginData(hbStart+"~"+hbEnd);
				hbFilters.add(hbFilter);
				hbFilters.addAll(commonFilters);
				hbzb.setFilters(hbFilters);
				zbs.add(hbzb);
				break;
			case "hbl":
				List<Filter> hblFilters=new ArrayList<>();
				Zb hblzb=new Zb();
				hblzb.setZbm(zb.getZbm()+"(环比率)");
				hblzb.setZbbm(zb.getZbbm());
				calendar.setTime(startTime);
				calendar.add(Calendar.MONTH, -1);
				String hblStart=format.format(calendar.getTime());
				calendar.setTime(endTime);
				calendar.add(Calendar.MONTH, -1);
				String hblEnd=format.format(calendar.getTime());
				Filter hblFilter=new Filter();
				String hblcondition=null;
				if(isDate){
					hblcondition=thbColName+" <= "+"to_date('"+hblEnd+"','yyyy-mm-dd hh24:mi:ss') and "
							+thbColName+" >= "+"to_date('"+hblStart+"','yyyy-mm-dd hh24:mi:ss')";
				}else{
					hblcondition=thbColName+" <= "+"'"+hblEnd+"' and "
							+thbColName+" >= "+"'"+hblStart+"' ";
				}
				hblFilter.setCondition(hblcondition);
				hblFilter.setConditionName(thbColName+" 之间 "+hblStart+" ~ "+hblEnd);
				hblFilter.setLogicOp("and");
				hblFilter.setOriginData(hblStart+"~"+hblEnd);
				hblFilters.add(hblFilter);
				hblFilters.addAll(commonFilters);
				hblzb.setFilters(hblFilters);
				zbs.add(hblzb);
				break;
			}
		}
		return zbs;
	}
	
	private void getTb(AnalysisParam analysisParam){
		List<Map<String,Object>> listmap= analysisMapper.analysis(analysisParam);
		String wdzs=analysisParam.getWdzs();
		List<String> zbms=new ArrayList<String>();
		for(Map<String,Object> mapobj:listmap){
			List<Object> rows=new ArrayList<>();
			String wd=String.valueOf(mapobj.get(wdzs));
			if(null!=wd&&!"null".equals(wd)){
				rows.add(wd);
			}else{
				rows.add("其他");
			}
			
			for(String zbm:zbms){
				System.out.println("zbm:"+zbm);
				rows.add(mapobj.get(zbm));
			}
			resultList.add(rows);
		}
	}

}
