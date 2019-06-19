package com.mtlckj.base.system.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.system.domain.MenuDO;
import com.mtlckj.base.system.domain.Tree;
import com.mtlckj.base.system.service.MenuService;
import com.mtlckj.base.system.utils.R;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/system/menu")
@Controller
public class MenuController {
	String prefix = "/pages/system/menu";;
	@Autowired
	MenuService menuService;

	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu.html";
	}

	@RequestMapping("/list")
	@ResponseBody
	List<MenuDO> list(@RequestParam Map<String, Object> params) {
		List<MenuDO> menus = menuService.list(params);
		return menus;
	}
	/**
	 * 获取根目录
	 * @param model
	 * @param pId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/add/{pId}")
	R add(Model model, @PathVariable("pId") Long pId) {
		R r= new R();
		r.put("pId", pId);
		if (pId == 0) {
			r.put("pName", "根目录");
		} else {
			r.put("pName", menuService.get(pId).getName());
		}
		return r;
	}
	@ResponseBody
	@GetMapping("/edit/{id}")
	R edit(@PathVariable("id") Long id) {
		R r= new R();
		MenuDO mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		r.put("pId", pId);
		if (pId == 0) {
			r.put("pName", "根目录");
		} else {
			r.put("pName", menuService.get(pId).getName());
		}
		r.put("menu", mdo);
		return r;
	}

	@PostMapping("/save")
	@ResponseBody
	R save(MenuDO menu) {
		if (menuService.save(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@PostMapping("/update")
	@ResponseBody
	R update(MenuDO menu) {
		if (menuService.update(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}

	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (menuService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree() {
		Tree<MenuDO>  tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		Tree<MenuDO> tree = menuService.getTree(roleId);
		return tree;
	}
}
