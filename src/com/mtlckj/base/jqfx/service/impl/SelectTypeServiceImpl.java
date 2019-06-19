package com.mtlckj.base.jqfx.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtlckj.base.jqfx.domain.BooleanAndNum;
import com.mtlckj.base.jqfx.domain.DateCount;
import com.mtlckj.base.jqfx.domain.Gwxq;
import com.mtlckj.base.jqfx.domain.ReturnTree;
import com.mtlckj.base.jqfx.domain.Ztree;
import com.mtlckj.base.jqfx.mapper.SelectTypeMapper;
import com.mtlckj.base.jqfx.service.SelectTypeService;

@Service
public class SelectTypeServiceImpl implements SelectTypeService {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private SelectTypeMapper selectTypeMapper;
	
	/**
	 * 获取动态树
	 */
	@Override
	public List<Ztree> getReturnTree() {
		List<ReturnTree> list = selectTypeMapper.getReturnTree();
		List<Ztree> list2 = new ArrayList<>();
		Ztree ztree1 = new Ztree("1","0","犯罪类型",true,true);
		list2.add(ztree1);
		for (ReturnTree returnTree : list) {
			Ztree ztree = new Ztree();
			ztree.setId(returnTree.getDm());
			ztree.setName(returnTree.getCt());
			ztree.setOpen(false);
			//是否为父节点
			if(returnTree.getDm().length() == 8) {//编码最长为8位，且必定是子节点
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 5) {//编码为5位，必定是子节点
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0101") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0102") == 0 && !returnTree.getDm().equals("010205")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0103") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0104") == 0 && !returnTree.getDm().equals("010404") && !returnTree.getDm().equals("010499") && !returnTree.getDm().equals("010405") && !returnTree.getDm().equals("010406")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0105") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0201") == 0 && !returnTree.getDm().equals("020101") && !returnTree.getDm().equals("020104") && !returnTree.getDm().equals("020106") && !returnTree.getDm().equals("020107") && !returnTree.getDm().equals("020108") && !returnTree.getDm().equals("020116") && !returnTree.getDm().equals("020124") && !returnTree.getDm().equals("020125")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0202") == 0 && !returnTree.getDm().equals("020201") && !returnTree.getDm().equals("020202") && !returnTree.getDm().equals("020206") && !returnTree.getDm().equals("020212")&& !returnTree.getDm().equals("020215") && !returnTree.getDm().equals("020218") && !returnTree.getDm().equals("020225")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0203") == 0 && !returnTree.getDm().equals("020324")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0204") == 0 && !returnTree.getDm().equals("020401") && !returnTree.getDm().equals("020406") && !returnTree.getDm().equals("020414") && !returnTree.getDm().equals("020416") && !returnTree.getDm().equals("020422") && !returnTree.getDm().equals("020433") && !returnTree.getDm().equals("020435") && !returnTree.getDm().equals("020441") && !returnTree.getDm().equals("020447") && !returnTree.getDm().equals("020461") && !returnTree.getDm().equals("020462") && !returnTree.getDm().equals("020465") && !returnTree.getDm().equals("020467")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0205") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("03") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("04") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("05") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("06") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 6 && returnTree.getDm().indexOf("0701") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("07") == 0 && !returnTree.getDm().equals("0701")) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("08") == 0) {
				ztree.setParent(false);
			}else if(returnTree.getDm().length() == 4 && returnTree.getDm().indexOf("09") == 0) {
				ztree.setParent(false);
			}else {
				ztree.setParent(true);
			}
			if(returnTree.getDm().length() == 2) {
				ztree.setPid("1");
			}else if(returnTree.getDm().length() == 3){
				ztree.setPid("1");
			}else if(returnTree.getDm().length() == 5 && returnTree.getDm().indexOf("010") == 0){
				ztree.setPid("010");
			}else if(returnTree.getDm().length() == 5 && returnTree.getDm().indexOf("011") == 0){
				ztree.setPid("011");
			}else if(returnTree.getDm().length() == 5 && returnTree.getDm().indexOf("012") == 0){
				ztree.setPid("012");
			}else if(returnTree.getDm().equals("060745")){
				ztree.setPid("06");
			}else {
				ztree.setPid(returnTree.getDm().substring(0, returnTree.getDm().length()-2));
			}
			list2.add(ztree);
		}
		
		return list2;
	}

	/**
	 * 专题概况统计
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public List<Map<String, Object>> getZtgk(Map<String, Object> params) throws UnsupportedEncodingException {
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8").trim();
		String nameArr = URLDecoder.decode((String) params.get("nameArr"), "UTF-8").trim();
		String parentArr = URLDecoder.decode((String) params.get("parentArr"), "UTF-8").trim();
		String pcsArr = URLDecoder.decode((String) params.get("pcsArr"), "UTF-8").trim();
		String[] narr = nameArr.split(",");
		String[] arr1 = strArr.split(",");
		String[] arr2 = parentArr.split(",");
		String[] arr3 = null;
		if(pcsArr != null && pcsArr != "") {
			arr3 = pcsArr.split(",");
		}
		List<Map<String, Object>> list = new ArrayList<>();
		for(int i=0;i<arr1.length;i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("startTime", params.get("startTime"));
			map.put("endTime", params.get("endTime"));
			map.put("typeNum", arr1[i]);
			map.put("isParent", arr2[i]);
			map.put("pcsArr", arr3);
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("typeName", narr[i]);
			resultMap.put("count", selectTypeMapper.getZtgk(map));
			list.add(resultMap);
		}
		
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getGwxq(Map<String, Object> params) throws UnsupportedEncodingException {
		List<Map<String, Object>> listMap = new ArrayList<>();
		String pcsArr = URLDecoder.decode((String) params.get("pcsArr"), "UTF-8");
		List<Gwxq> list = null;
		if(pcsArr != null && pcsArr != "") {
			String[] arr = pcsArr.split(",");
			params.put("pcs", arr);
			list = selectTypeMapper.getGwxq2(params);
		}else {
			list = selectTypeMapper.getGwxq(params);
		}
		for (Gwxq gwxq : list) {
			Map<String,Object> map = new HashMap<>();
			map.put("pcsname", gwxq.getPcsname());
			map.put("count", gwxq.getCount());
			listMap.add(map);
		}
		return listMap;
	}

	@Override
	public List<Map<String, Object>> getTu1(Map<String, Object> params) throws UnsupportedEncodingException {
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8");
		String[] num = strArr.split(",");
		String nameArr = URLDecoder.decode((String) params.get("nameArr"), "UTF-8");
		String[] type = nameArr.split(",");
		String parentArr = URLDecoder.decode((String) params.get("parentArr"), "UTF-8");
		String[] isParent = parentArr.split(",");
		String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8").trim();
		String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8").trim();
		params.put("startTime", startTime.split(" ")[0]);
		params.put("endTime", endTime.split(" ")[0]);
		String pcsArr = URLDecoder.decode((String) params.get("pcsArr"), "UTF-8");
		String[] arr = null;
		if(pcsArr != null && pcsArr != "") {
			arr = pcsArr.split(",");
		}
		List<Map<String, Object>> listMap = new ArrayList<>();
		for(int i=0;i<num.length;i++) {
			Map<String,Object> map = new HashMap<>();
			params.put("typeno", num[i]);
			params.put("isParent", isParent[i]);
			params.put("pcs", arr);
			List<DateCount> list = selectTypeMapper.getTu1(params);
			map.put("typeName", type[i]);
			map.put("dateList", list);
			listMap.add(map);
		}
		return listMap;
	}

	@Override
	public List<Map<String, Object>> getTu2(Map<String, Object> params) throws UnsupportedEncodingException {
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8");
		String[] num = strArr.split(",");
		String nameArr = URLDecoder.decode((String) params.get("nameArr"), "UTF-8");
		String[] type = nameArr.split(",");
		String parentArr = URLDecoder.decode((String) params.get("parentArr"), "UTF-8");
		String[] isParent = parentArr.split(",");
		String pcsArr = URLDecoder.decode((String) params.get("pcsArr"), "UTF-8");
		
		String[] arr = null;
		if(pcsArr != null && pcsArr != "") {
			arr = pcsArr.split(",");
		}
		
		List<Map<String, Object>> listMap = new ArrayList<>();
		for(int i=0;i<num.length;i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("typeName", type[i]);
			params.put("num", num[i]);
			params.put("isParent", isParent[i]);
			params.put("pcs", arr);
			
			Map<String,Object> resultMap = getTu2Map(params);
			map.put("countNow", resultMap.get("COUNTNOW"));
			map.put("countLast", resultMap.get("COUNTLAST"));
			map.put("startTime", params.get("startTime"));
			map.put("endTime", params.get("endTime"));
			map.put("isParent", isParent[i]);
			map.put("typeNum", num[i]);
			map.put("hb", getTbHb(resultMap.get("COUNTLAST").toString(),resultMap.get("COUNTNOW").toString()));
			listMap.add(map);
		}
		return listMap;
	}

	private Map<String, Object> getTu2Map(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<>();
		int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
		map.put("startNowTime", ((String) params.get("startTime")).trim());
		map.put("endNowTime", ((String) params.get("endTime")).trim());
		map.put("startLastTime", getLastTimeForRingStart((String) params.get("startTime"), day).trim());
		map.put("endLastTime", getLastTimeForRingEnd((String) params.get("endTime"), day).trim());
		map.put("typeNum", params.get("num"));
		map.put("isParent", params.get("isParent"));
		map.put("pcs", params.get("pcs"));
		return selectTypeMapper.getTu2(map);
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
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, -day);
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
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, -day);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得环比
	 * @param lastNumber
	 * @param nowNumber
	 * @return
	 */
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
		return nt.format(tb);
	}

	@Override
	public List<Map<String,Object>> getList(Map<String, Object> params) {
		List<Map<String,Object>> list = selectTypeMapper.getList(params);
		List<Map<String,Object>> resultList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("jqbh", list.get(i).get("JQBH"));
			resultMap.put("bjDate", list.get(i).get("BJSJ_RQSJ"));
			resultMap.put("address", list.get(i).get("SFDZ_DZMC"));
			resultMap.put("pcsName", list.get(i).get("CJDW_GAJGMC"));
			resultMap.put("bname", list.get(i).get("BAR_XM"));
			resultMap.put("btell", list.get(i).get("BAR_LXDH"));
			resultList.add(resultMap);
		}
		return resultList;
	}

	@Override
	public int getTotal(Map<String, Object> params) {
		
		return selectTypeMapper.getTotal(params);
	}

	@Override
	public List<Map<String, Object>> gwqy(Map<String, Object> params) throws UnsupportedEncodingException {
		int day = getDifferenceDay((String) params.get("startTime"), (String) params.get("endTime"));
		String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8").trim();
		String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8").trim();
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8").trim();
		String[] arr1 = strArr.split(",");
		String parentArr = URLDecoder.decode((String) params.get("parentArr"), "UTF-8").trim();
		String[] arr2 = parentArr.split(",");
		String pcsArr = URLDecoder.decode((String) params.get("pcsArr"), "UTF-8").trim();
		List<BooleanAndNum> paramList = new ArrayList<>();
		for(int i=0;i<arr1.length;i++) {
			BooleanAndNum ban = new BooleanAndNum(arr2[i],arr1[i]);
			paramList.add(ban);
		}
		String startLastTime = getLastTimeForRingStart((String) params.get("startTime"), day).trim();
		String endLastTime = getLastTimeForRingEnd((String) params.get("endTime"), day).trim();
		params.put("paramList", paramList);
		params.put("startNowTime", startTime);
		params.put("endNowTime", endTime);
		params.put("startLastTime", startLastTime);
		params.put("endLastTime", endLastTime);
		
		
		List<Map<String, Object>> list = null;
		if(pcsArr != null && pcsArr != "") {
			String[] arr3 = pcsArr.split(",");
			params.put("pcs", arr3);
			list = selectTypeMapper.gwqy2(params);
		}else {
			list = selectTypeMapper.gwqy(params);
		}
		List<Map<String,Object>> resultList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("pcsName", list.get(i).get("PCS"));
			resultMap.put("count", list.get(i).get("COUNTNOW"));
			resultMap.put("countLast", list.get(i).get("LASTCOUNT"));
			resultMap.put("orgcode", list.get(i).get("ORGCODE"));
			resultMap.put("startTime", params.get("startTime"));
			resultMap.put("endTime", params.get("endTime"));
			resultMap.put("startLastTime", startLastTime);
			resultMap.put("endLastTime", endLastTime);
			resultMap.put("strArr", params.get("strArr"));
			resultMap.put("parentArr", params.get("parentArr"));
			resultList.add(resultMap);
		}
		
		return resultList;
	}

	@Override
	public List<Map<String, Object>> findByOrgcode(Map<String, Object> params) {
		List<Map<String, Object>> list = selectTypeMapper.findByOrgcode(params);
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("typeName", list.get(i).get("CT"));
			resultMap.put("jqbh", list.get(i).get("JQBH"));
			resultMap.put("bjDate", list.get(i).get("BJSJ_RQSJ"));
			resultMap.put("address", list.get(i).get("SFDZ_DZMC"));
			resultMap.put("bname", list.get(i).get("BAR_XM"));
			resultMap.put("btell", list.get(i).get("BAR_LXDH"));
			resultList.add(resultMap);
		}
		return resultList;
	}

	@Override
	public int getTotalByOrgcode(Map<String, Object> params) {
		return selectTypeMapper.getTotalByOrgcode(params);
	}

	@Override
	public List<Map<String, Object>> forMap(Map<String, Object> params) throws UnsupportedEncodingException {
		String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8").trim();
		String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8").trim();
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8").trim();
		String parentArr = URLDecoder.decode((String) params.get("parentArr"), "UTF-8").trim();
		String nameArr = URLDecoder.decode((String) params.get("nameArr"), "UTF-8").trim();
		String pcsArr = URLDecoder.decode((String) params.get("pcsArr"), "UTF-8").trim();
		String[] arr1 = strArr.split(",");
		String[] arr2 = nameArr.split(",");
		String[] arr3 = parentArr.split(",");
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(int i=0;i<arr1.length;i++){
			params.put("isParent", arr3[i]);
			params.put("type", arr2[i]);
			params.put("num", arr1[i]);
			if(null != pcsArr && !("").equals(pcsArr)) {
				String[] arr = pcsArr.split(",");
				params.put("pcs", arr);
			}
			List<Map<String, Object>> list = selectTypeMapper.forMap(params);
			for(int j=0;j<list.size();j++){
				Map<String, Object> resultMap = new HashMap<>();
				if(list.get(j).get("SFBZDZ_DQJD") != null && list.get(j).get("SFBZDZ_DQWD") != null){
					resultMap.put("title", params.get("type"));
					resultMap.put("id", list.get(j).get("XXZJBH"));
					resultMap.put("lng", list.get(j).get("SFBZDZ_DQJD"));
					resultMap.put("lat", list.get(j).get("SFBZDZ_DQWD"));
					resultList.add(resultMap);
				}
			}
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getPcsList() {
		
		return selectTypeMapper.getPcsList();
	}
}
