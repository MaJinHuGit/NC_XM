package com.mtlckj.base.jqfx.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mtlckj.base.jqfx.domain.BooleanAndNum;
import com.mtlckj.base.jqfx.domain.Ztree;
import com.mtlckj.base.jqfx.service.SelectTypeService;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.R;

@RequestMapping("/jqfx/selectType")
@Controller
public class SelectTypeController {
	
	@Autowired
	private SelectTypeService selectTypeService;
	
	/**
	 * 警情走势图
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping("/getTu1")
	@ResponseBody
	public R getTu1(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		
		List<Map<String,Object>> list = selectTypeService.getTu1(params);
		return R.ok().put("list", list);
	}
	
	/**
	 * 警情类别统计图
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping("/getTu2")
	@ResponseBody
	public R getTu2(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		
		List<Map<String,Object>> list = selectTypeService.getTu2(params);
		return R.ok().put("list", list);
	}
	/**
	 * 动态获取树结构
	 * @return
	 */
	@GetMapping("/getTree")
	@ResponseBody
	public String getTree() {
		
		List<Ztree> list = selectTypeService.getReturnTree();
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * 专题概况显示
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/ztgk")
	public String getZtgk(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		
		List<Map<String,Object>> list = selectTypeService.getZtgk(params);
		return JSON.toJSONString(list);
	}
	
	/**
	 * 高危辖区显示
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/gwxq")
	public String getGwxq(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		try {
			if (params.get("startTime") != null && !"".equals(params.get("startTime"))) {
				String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
				params.put("startTime", startTime);
			} else {
				params.put("startTime", "-1");
			}
			if (params.get("endTime") != null && !"".equals(params.get("endTime"))) {
				String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
				params.put("endTime", endTime);
			} else {
				params.put("endTime", "-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8").trim();
		String[] arr1 = strArr.split(",");
		String parentArr = URLDecoder.decode((String) params.get("parentArr"), "UTF-8").trim();
		String[] arr2 = parentArr.split(",");
		List<BooleanAndNum> paramList = new ArrayList<>();
		for(int i=0;i<arr1.length;i++) {
			BooleanAndNum ban = new BooleanAndNum(arr2[i],arr1[i]);
			paramList.add(ban);
		}
		params.put("paramList", paramList);
		List<Map<String,Object>> list = selectTypeService.getGwxq(params);
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * 点击柱状图查看详情列表
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/getList")
	public PageUtils getList(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8");
		String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8");
		String num = URLDecoder.decode((String) params.get("typeNum"), "UTF-8");
		String offset = URLDecoder.decode((String) params.get("offset"), "UTF-8");
		String limit = URLDecoder.decode((String) params.get("limit"), "UTF-8");
		String isParent = URLDecoder.decode((String) params.get("isParent"), "UTF-8");
		String pcs = URLDecoder.decode((String) params.get("pcs"), "UTF-8");
		
		String[] arr = null;
		if(pcs != null && pcs != "") {
			arr = pcs.split(",");
		}
		
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("num", num);
		params.put("offset", offset);
		params.put("limit", limit);
		params.put("isParent", isParent);
		params.put("pcs", arr);
		
		List<Map<String,Object>> list = selectTypeService.getList(params);
		int total = selectTypeService.getTotal(params);
		PageUtils pu = new PageUtils(list, total);
		return pu;
	}
	
	/**
	 * 高危区域echarts数据
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("/gwqy")
	public R gwqy(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException {
		List<Map<String, Object>> list = selectTypeService.gwqy(params);
		return R.ok().put("list", list);
	}
	
	/**
	 * 根据派出所编码查询
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("/orgcode")
	public PageUtils findByOrgcode(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException {
		String startTime = URLDecoder.decode((String) params.get("startTime"), "UTF-8").trim();
		String endTime = URLDecoder.decode((String) params.get("endTime"), "UTF-8").trim();
		String orgcode = URLDecoder.decode((String) params.get("orgcode"), "UTF-8").trim();
		String strArr = URLDecoder.decode((String) params.get("strArr"), "UTF-8").trim();
		String offset = URLDecoder.decode((String) params.get("offset"), "UTF-8");
		String limit = URLDecoder.decode((String) params.get("limit"), "UTF-8");
		String parent = URLDecoder.decode((String) params.get("isParent"), "UTF-8").trim();
		String search = URLDecoder.decode((String) params.get("search"), "UTF-8").trim();
		if(search != "") {
			search = "%"+search+"%";
		}
		
		String[] arr1 = strArr.split(",");
		String[] arr2 = parent.split(",");
		List<BooleanAndNum> paramList = new ArrayList<>();
		for(int i=0;i<arr1.length;i++) {
			BooleanAndNum ban = new BooleanAndNum(arr2[i],arr1[i]);
			paramList.add(ban);
		}
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("orgcode", orgcode);
		params.put("offset", offset);
		params.put("limit", limit);
		params.put("paramList", paramList);
		params.put("search", search);
		
		List<Map<String,Object>> list = selectTypeService.findByOrgcode(params);
		int total = selectTypeService.getTotalByOrgcode(params);
		PageUtils pu = new PageUtils(list, total);
		return pu;
	}
	
	/**
	 * 根据犯罪类型、时间段，返回数据用于标识地图，数据包括：经度、纬度、数据主键、犯罪类型
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/forMap")
	public R forMap(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
		
		List<Map<String,Object>> list = selectTypeService.forMap(params);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		return R.ok().put("list", list);
	}
	
	/**
	 * 查询派出所的名称以及编码
	 * @return
	 */
	@GetMapping("/getPcsList")
	@ResponseBody
	public R getPcsList() {
		List<Map<String,Object>> list = selectTypeService.getPcsList();
		return R.ok().put("list", list);
	}
}
