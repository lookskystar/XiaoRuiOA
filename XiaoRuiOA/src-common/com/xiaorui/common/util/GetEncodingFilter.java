package com.xiaorui.common.util;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEncodingFilter implements Filter {

	public void destroy() {
		
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest) arg0;
		HttpServletResponse response =(HttpServletResponse) arg1;
		String Method=request.getMethod();
		if(Method.equalsIgnoreCase("get")){
			Enumeration enu = request.getParameterNames();
			while(enu.hasMoreElements()){
				String key = (String)enu.nextElement();
				String[] values = request.getParameterValues(key);
				for(int i=0;i<values.length;i++){
					values[i]=new String(values[i].getBytes("iso8859-1"),"utf-8");
				}
			}
		}
		arg2.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
