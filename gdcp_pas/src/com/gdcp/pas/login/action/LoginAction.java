package com.gdcp.pas.login.action;

/**
 * @author 刘伯睿   2015-03-13  
 * @see 提供登陆校验的方法
 */
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gdcp.base.BaseAction;
import com.gdcp.common.StringUtil;
import com.gdcp.pas.login.bo.LoginBO;
import com.gdcp.pas.login.vo.LoginVO;
import com.gdcp.pas.manage.bo.FunctionBO;
import com.gdcp.pas.manage.bo.RoleBO;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.RoleVO;
import com.gdcp.pas.manage.vo.UserVO;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {
	private static Logger logger = Logger.getLogger(LoginAction.class);
	private LoginVO loginVO;

	public String login() {
		LoginBO loginBO = new LoginBO();
		boolean flag = false;
		try {
			if (loginVO != null) {
				flag = loginBO.login(loginVO);
			}
			if (!flag) {
				return "failed";
			}
			Map<String, Object> session = ActionContext.getContext().getSession();
			String teacherId = loginVO.getTeacherId().toString();
			UserBO userBO = new UserBO();
			FunctionBO functionBO = new FunctionBO();

			UserVO userVO = userBO.getUserByTeacherId(teacherId);
			List<String> userRightList = functionBO.queryObjectFunction(teacherId, "0");
			if (!StringUtil.isNullOrBlank(userVO.getRoleId() + "")) {
				List<String> roleRightList = functionBO.queryObjectFunction(userVO.getRoleId() + "", "1");
				userRightList.addAll(roleRightList);
			}
			userVO.setRightList(userRightList);
			RoleBO roleBO = new RoleBO();
			RoleVO roleVO = roleBO.getRoleById(userVO.getRoleId() + "");

			userVO.setRoleName(roleVO.getRoleName());// 放入角色名字
			// List<String> userRightList

			session.put("userVO", userVO);
			// session.put("functionList", functionList);

		} catch (Exception e) {
			logger.error("登录出错", e);
			return "failed";
		}
		return "success";
	}

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}
}
