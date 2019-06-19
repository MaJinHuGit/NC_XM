package com.mtlckj.base.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.system.domain.RoleDO;
import com.mtlckj.base.system.service.RoleService;
import com.mtlckj.base.system.utils.R;

@RequestMapping("/system/role")
@Controller
public class RoleController {
	String prefix = "/pages/system/role";
	@Autowired
	RoleService roleService;

	@GetMapping()
	String role() {
		return prefix + "/role.html";
	}

	@GetMapping("/list")
	@ResponseBody()
	List<RoleDO> list() {
		List<RoleDO> roles = roleService.list();
		return roles;
	}

	@GetMapping("/add")
	String add() {
		return prefix + "/add.html";
	}
	@ResponseBody()
	@GetMapping("/edit/{id}")
	R edit(@PathVariable("id") Long id) {
		R r=new R();
		RoleDO roleDO = roleService.get(id);
		r.put("role", roleDO);
		return r;
	}

	@PostMapping("/save")
	@ResponseBody()
	R save(RoleDO role) {
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@PostMapping("/update")
	@ResponseBody()
	R update(RoleDO role) {
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
