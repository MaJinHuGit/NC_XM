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

import com.mtlckj.base.system.domain.DeptDO;
import com.mtlckj.base.system.domain.RoleDO;
import com.mtlckj.base.system.domain.Tree;
import com.mtlckj.base.system.domain.UserDO;
import com.mtlckj.base.system.service.RoleService;
import com.mtlckj.base.system.service.UserService;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.Query;
import com.mtlckj.base.system.utils.R;
import com.mtlckj.base.system.vo.UserVO;

@RequestMapping("/system/user/")
@Controller
public class UserController  {
	private String prefix="/pages/user"  ;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user.html";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}
	@ResponseBody
	@GetMapping("/add")
	R add(Model model) {
		R r= new R();
		List<RoleDO> roles = roleService.list();
		r.put("roles", roles);
		return r;
	}

	@GetMapping("/edit/{id}")
	@ResponseBody
	R edit(@PathVariable("id") Long id) {
		R r= new R();
		UserDO userDO = userService.get(id);
		r.put("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		r.put("roles", roles);
		return r;
	}

	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user) {
		user.setPassword( user.getPassword());
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}
	@ResponseBody
	@GetMapping("/resetPwd/{id}")
	R resetPwd(@PathVariable("id") Long userId, Model model) {
		R r= new R();
		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		r.put("user", userDO);
		return r;
	}
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		try{
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree.html";
	}

}
