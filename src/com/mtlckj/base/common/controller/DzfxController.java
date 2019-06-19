package com.mtlckj.base.common.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mtlckj.base.common.domain.DzfxDO;
import com.mtlckj.base.common.service.DzfxService;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.Query;
import com.mtlckj.base.system.utils.R;

@Controller
@RequestMapping("/dzfx")
public class DzfxController {
	
	@Autowired
	private DzfxService dzfxService;
	
	@PostMapping(value="/save")
	@ResponseBody
	public R saveDzfx(DzfxDO dzfx) {
		
		if(null!=dzfx.getDzfxId()){
			dzfx.setCreateTime(null);
			if(dzfxService.update(dzfx)>0){
				return R.ok();
			}
		}else {
			if(dzfxService.save(dzfx)>0) {
				return R.ok();
			}
		}		
		return R.error("保存分析失败");
	}
	
	@GetMapping(value="/getById")
	@ResponseBody
	public DzfxDO get(@RequestParam(name="dzfxId") Long id) {
		
		DzfxDO dzfx=dzfxService.getById(id);
		if(null!=dzfx){
			return dzfx;
		}
		return null;
	}
	
	@RequestMapping(value="/search")
	@ResponseBody
	public PageUtils search(@RequestBody Map<String, Object> params ){
		
		Query query=new Query(params);
		System.out.println("query:"+JSON.toJSONString(query));
		List<DzfxDO> modules=dzfxService.search(query);
		int total=dzfxService.countForHistory(query);
		PageUtils pageUtil=new PageUtils(modules, total);
		return pageUtil;
//		return null;
	}
	
	@GetMapping(value="/del")
	@ResponseBody
	public R remove(@RequestParam(name="dzfxId") Long dzfxId){
		if(dzfxService.remove(dzfxId)>0){
			return R.ok();
		}
		return R.error("删除失败");
//		return R.ok();
	}
	
}
