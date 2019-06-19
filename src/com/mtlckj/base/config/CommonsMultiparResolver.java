package com.mtlckj.base.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultiparResolver extends CommonsMultipartResolver{
	
	@Override
	public boolean isMultipart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI();
	  if (uri.indexOf("ue") > 0) {     //此处拦截路径即为上面编写的controller路径
	        return false;  
	       }  
		return super.isMultipart(request);
	}
}
