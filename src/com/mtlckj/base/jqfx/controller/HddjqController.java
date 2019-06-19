package com.mtlckj.base.jqfx.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.domain.Hddjq;
import com.mtlckj.base.jqfx.service.HddjqService;
import com.mtlckj.base.jqfx.vo.HddjqVo;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.R;


/**
 * <p>Title: HddjqController</p> 
 * <p>Package: com.mtlckj.base.jqfx.controller</p>  
 * <p>Description: 黄赌毒警情Controller</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author majinhu  
 * @date 2018年10月19日   
 * @version 1.0
 */
@RequestMapping("/jqfx/hddjq")
@Controller
public class HddjqController {
	
	@Autowired
	private HddjqService hddjqService;
	
	
	@ResponseBody
	@GetMapping("/getHddjqByBldw")
	public R getHddjqByBldw(@RequestParam Map<String, Object> params) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(params.get("pcsNames") != null && !"".equals(params.get("pcsNames"))) {
				String[] pcsNames = URLDecoder.decode((String) params.get("pcsNames"),"UTF-8").split(",");
				for (String pcsName : pcsNames) {
					params.put("bldw", pcsName);
					List<HddjqVo> list = hddjqService.getHddjqByBldw(params);
					map.put(pcsName, list);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return R.ok().put("data",map);
	}
	
	
	@ResponseBody
	@GetMapping("/getList")
	public PageUtils getList(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("bldw") != null && !"".equals(params.get("bldw"))) {
				String bldw = URLDecoder.decode((String) params.get("bldw"),"UTF-8");
	    		params.put("bldw", bldw);
	    	}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Hddjq> list = hddjqService.getList(params);
		int total = hddjqService.count(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/get")
	public String get() {
		return "SUCCESS";
	}
}
