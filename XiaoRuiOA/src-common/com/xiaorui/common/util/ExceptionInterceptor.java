package com.xiaorui.common.util;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 全局异常 并把异常保存到日志文件中跳转到错误页面
 *
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6094586137631997114L;
	private Logger log = Logger.getLogger(ExceptionInterceptor.class);

	public String intercept(ActionInvocation invocation) throws Exception {
			try {
				//继续执行剩余的拦截器和Action方法
				return invocation.invoke();
			} catch (Throwable e) {
				log.error(e, e);
				return "g_error";
			}
	}

}
