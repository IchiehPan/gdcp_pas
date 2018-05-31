package com.gdcp.pas.permission;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.pas.permission.PermissionRead;
import com.gdcp.pas.manage.vo.UserVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {
	private Logger logger = Logger.getLogger(AuthorityInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		invocation.getInvocationContext();
		// 获取user是否存在
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();
		UserVO user = (UserVO) session.get("userVO");
		// 获取请求地址
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext()
				.get(ServletActionContext.HTTP_REQUEST);
		String uri = request.getRequestURI();
		logger.info("uri: " + uri);

		String[] arr = uri.split("_");
		String temp[] = arr[0].replaceAll("\\\\", "/").split("/");
		String arr1 = null;
		if (temp.length > 1) {
			arr1 = temp[temp.length - 1];
		}
		logger.info("actionName: " + arr1); // actionName
		int index = arr[1].lastIndexOf("action");
		logger.info("arr[1]: " + arr[1]);

		String arr2 = arr[1].substring(0, index - 1); // method
		logger.info("method:" + arr2);

		if (arr1.equals("login") && arr2.equals("login")) {
			return invocation.invoke();
		}

		List<Integer> rightIds = PermissionRead.getFunctionId(arr1, arr2);

		boolean flag = false;
		for (Integer rightId : rightIds) {
			if (!flag) {
				flag = PermissionUtil.isPermission(user, rightId + "");
			} else {
				break;
			}
		}

		if (flag || uri.indexOf("login.action") != -1) {
			String a = invocation.invoke();// 将控制交给后续拦截器或action
			return a;
		} else {
			logger.error("无权限：" + arr1 + "_" + arr2);
			return "login";
		}
	}

}
