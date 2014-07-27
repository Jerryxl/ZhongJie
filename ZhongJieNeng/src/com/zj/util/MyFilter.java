package com.zj.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * 我的过滤器，配置不去过滤ckeditor
 * 因为Struts2和ckeditor有冲突，ckeditor被struts2的/*这种配置拦截了，导致ckeditor自己的servlet拦截不到，所以上传肯定失败
 * 更改方法：1.更改xml的配置，改为*.do或*。action 
 * 2.重写filter方法
 * */
public class MyFilter extends StrutsPrepareAndExecuteFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest request = (HttpServletRequest) req;
		  //包含了ckeditor的请求
	        if (request.getRequestURI().contains("ckeditor")) {
	           chain.doFilter(request, res);
	        }else{
	            super.doFilter(req, res, chain);
	        }
	}
}
