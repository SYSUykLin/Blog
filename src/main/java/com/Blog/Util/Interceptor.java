package com.Blog.Util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class Interceptor implements HandlerInterceptor {

	private final String[] URI = { "/ueditor", "/jsp", "/controller" };

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("Intecepting......");
		String servletPath = request.getServletPath();
		for (String s : URI) {
			if (servletPath.contains(s)) {
				flag = true;
				System.out.println("pass...");
				break;
			}
		}
		return flag;
	}
  
}
