package com.mtlckj.base.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mtlckj.base.system.domain.MenuDO;
import com.mtlckj.base.system.domain.Tree;
import com.mtlckj.base.system.domain.UserDO;
import com.mtlckj.base.system.service.MenuService;
import com.mtlckj.base.system.service.UserService;
import com.mtlckj.base.system.utils.R;

@Controller
public class LoginController  {
	String prefix = "";
	@Autowired
	MenuService menuService;
	 @Autowired 
	HttpServletRequest request;
	 
	@Autowired
	UserService userService;
	@GetMapping({ "/", "" })
	String welcome(Model model ,HttpServletResponse response ) throws ServletException, IOException {
		//跳转到服务器内部的一个页面
        //request.getRequestDispatcher("/userlogin.html").forward(request,response);         
		
		/*if(null != request.getAttribute("user")) {
			response.sendRedirect("index.html");
		}else {
			 //重定向一个功能方法
	        
		}*/
		response.sendRedirect("userlogin.html");
		//return "redirect:/login.html";
        return null;
	}

	@GetMapping({ "/getMenus" })
	@ResponseBody
	R gteMenus(Model model) {
		UserDO user = (UserDO) request.getSession().getAttribute("user");
		List<Tree<MenuDO>> menus = menuService.listMenuTree(user.getUserId());
		System.out.println("菜单："+JSONArray.toJSON(menus));
		return R.ok().put("menus", menus);
	}
	
	
	//@GetMapping({ "/index" })
	String index(Model model) {
		UserDO user = (UserDO) request.getSession().getAttribute("user");
		List<Tree<MenuDO>> menus = menuService.listMenuTree(user.getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name");
		model.addAttribute("username" );
		return prefix+"/index.html";
	}

	//@GetMapping("/login")
	String login() {
		return prefix+"/login.html";
	}

	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		HttpSession httpSession =request.getSession();
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
	    List<UserDO> users= userService.list(map);
		try {
			if (users.size()>0)
			{
				//登录成功
				httpSession.setAttribute("user", users.get(0));
				return R.ok();
			}else {
				return R.error().put("msg", "登录名或密码错误");
			}
			
			
		} catch (Exception e) {
			return R.error("用户或密码错误");
		}
	}

	@PostMapping("/cklogin")
	@ResponseBody
	R ckLogin() {
		HttpSession httpSession =request.getSession();
		if( httpSession.getAttribute("user") != null ) {
			return R.ok().put("user", httpSession.getAttribute("user"));
		}else {
			return R.error();
		}

	}
	
	@GetMapping("/logout")
	String logout(HttpServletResponse response) throws IOException{
		HttpSession	 httpSession =request.getSession();
		httpSession.invalidate();
		response.sendRedirect("userlogin.html");
		return null;
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
