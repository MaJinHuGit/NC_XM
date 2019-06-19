package com.mtlckj.base.jqfx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.service.XqjqflService;
import com.mtlckj.base.system.utils.R;

/**
 * 全区各所辖区警情分类统计表controller
 * @author Administrator
 *
 */

@RequestMapping("/jqfx/xqjqfl")
@Controller
public class XqjqflController {
	@Autowired
	private XqjqflService xqjqflService;
	
	@ResponseBody
	@GetMapping("/getTable")
	public R getTable(String startTime, String endTime) {
		if(startTime == null || startTime == "") {
			startTime = "-1";
		}
		
		if(endTime == null || endTime == "") {
			endTime = "-1";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		// 警情分类统计
		List<Map<String, String>> list = xqjqflService.getTable(map);
		return R.ok().put("list", list);
	}
}
