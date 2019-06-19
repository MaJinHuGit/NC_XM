package com.mtlckj.base.system.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/ue")
public class UeditorController {
	
	private static final Logger logger=Logger.getLogger(UeditorController.class);
	
	@RequestMapping("/upload")
	@ResponseBody
	public String fileUpload(HttpServletRequest request,HttpServletResponse response) {
		String json=null;
		try {
			request.setCharacterEncoding( "utf-8" );
			response.setHeader("Content-Type" , "text/html");
			String rootPath = request.getServletContext().getRealPath("/");
			json=new ActionEnter( request, rootPath ).exec();
			JSONObject jsonObj=JSONObject.parseObject(json);
			if("SUCCESS".equals(jsonObj.getString("state"))) {
				String url=jsonObj.getString("url");
				url="/alarmAnalysis"+url;
				jsonObj.put("url", url);
				json=jsonObj.toJSONString();
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
