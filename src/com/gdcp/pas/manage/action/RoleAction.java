package com.gdcp.pas.manage.action;

/**
 * @author 刘伯睿   2015-03-13  
 * @see 提供角色管理的界面响应请求
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.base.BaseAction;
import com.gdcp.common.Page;
import com.gdcp.pas.manage.bo.FunctionBO;
import com.gdcp.pas.manage.bo.RoleBO;
import com.gdcp.pas.manage.vo.RoleVO;

public class RoleAction extends BaseAction {
	private static Logger logger = Logger.getLogger(RoleAction.class);
	private RoleBO bo = new RoleBO();

	private int roleId;
	private String roleName;
	private String remark;
	private List<String> roleList;
	private String userName;
	private List<String> userFunctionList;
	private List<String> baseFunctionList;
	private List<String> messageFunctionList;

	public void Check() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String str = URLDecoder.decode(userName, "UTF-8");
		boolean test = false;
		RoleBO roleBO = new RoleBO();
		try {
			test = roleBO.checkRoleName(str);
		} catch (Exception e) {
			logger.error("Check", e);
		}
		PrintWriter out = response.getWriter();
		out.print(test);
		out.flush();
		out.close();

	}

	/**
	 * @return 批量删除 Role
	 */
	public String delRoleList() {
		String[] StringArr = null;
		if (roleList.size() > 0) {
			String Them = roleList.get(0);
			StringArr = Them.split(",");
		}

		RoleBO roleBO = new RoleBO();
		int result;
		try {
			result = roleBO.executeBatchDelete(StringArr);
			if (result > 0) {

				FunctionBO functionBO = new FunctionBO();
				functionBO.executeBatchDelete(StringArr);
				return "Delete_List_success";
			}

		} catch (Exception e) {
			logger.error("批量删除 Role失败", e);
		}

		return "Delete_List_failed";

	}

	public String trueupdate() {
		RoleBO roleBO = new RoleBO();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleId(String.valueOf(roleId));
		roleVO.setRoleName(roleName);
		roleVO.setRemark(remark.trim());

		try {
			roleBO.updateRec(roleVO);
			FunctionBO functionBO = new FunctionBO();
			functionBO.updateRoleFunction(roleId, userFunctionList, baseFunctionList, messageFunctionList);
		} catch (Exception e) {
			logger.error("trueupdate", e);
		}

		return "Update_success";
	}

	public String updataRole() throws UnsupportedEncodingException {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roleId", roleId);
		request.setAttribute("roleName", new String(roleName.getBytes("ISO-8859-1"), "utf-8"));
		request.setAttribute("remark", new String(remark.getBytes("ISO-8859-1"), "utf-8"));
		FunctionBO functionBO = new FunctionBO();
		try {
			List<String> functionIdList = functionBO.queryRoleFunction(String.valueOf(roleId));
			request.setAttribute("functionIdList", functionIdList);
		} catch (SQLException e) {
			logger.error("sql语句错误", e);
		} catch (Exception e) {
			logger.error("更新角色错误", e);
		}

		return "role_Update";
	}

	public String query() {
		List<RoleVO> roleVOlist;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Page page = new Page(request);
			RoleVO qroleVO = new RoleVO();
			String roleName = request.getParameter("Conditions_roleName");
			if (roleName != null) {
				qroleVO.setRoleName(roleName.trim());
			}
			roleVOlist = RoleBO.queryPage(page, qroleVO);

			request.setAttribute("roleList", roleVOlist);
			request.setAttribute("page", page);
			request.setAttribute("Conditions_roleName", roleName);

		} catch (Exception e) {
			logger.error("查询失败", e);
		}
		return "query_success";
	}

	/**
	 * @see 插入一个角色
	 */
	public String addRole() {
		int result = 0;
		try {
			RoleVO roleVO = new RoleVO();
			roleVO.setRoleName(roleName);
			roleVO.setRemark(remark);

			result = bo.insertRec(roleVO);
		} catch (Exception e) {
			logger.error("插入一个角色出错", e);
		}
		if (result > 0) {
			FunctionBO functionBO = new FunctionBO();
			try {
				functionBO.setRoleFunction(result, userFunctionList, baseFunctionList, messageFunctionList);
			} catch (Exception e) {
				logger.error("增加角色方法权限失败", e);
			}
			return "addRole_success";
		} else {
			return "addRole_failed";
		}

	}

	/*
	 * @param roleVO RoleVO
	 * 
	 * @see 往数据库删除一个角色的记录
	 */
	public String deleteRole() {
		int result = 0;
		RoleBO roleBO = new RoleBO();
		try {
			result = roleBO.deleteRec(String.valueOf(roleId));
		} catch (Exception e) {
			logger.error("往数据库删除一个角色的记录出错", e);
		}

		if (result <= 0) {
			return "deleteRole_failed";
		} else {
			FunctionBO function = new FunctionBO();
			try {
				function.deleteFunctionByRoleId(roleId);
			} catch (Exception e) {
				logger.error("删除roleId下的功能Id出错", e);
			}
			return "deleteRole_success";
		}

	}

	public String queryPage() throws Exception {
		List<RoleVO> roleVOlist;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Page page = new Page(request);
			RoleVO qroleVO = new RoleVO();
			String roleName = request.getParameter("Conditions_roleName");
			if (roleName != null) {
				qroleVO.setRoleName(roleName.trim());
			}
			roleVOlist = RoleBO.queryPage(page, qroleVO);

			request.setAttribute("roleList", roleVOlist);
			request.setAttribute("page", page);
			request.setAttribute("Conditions_roleName", roleName);

		} catch (Exception e) {
			logger.error("查询页面出错", e);
		}
		return "queryPage_success";

	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		RoleAction.logger = logger;
	}

	public RoleBO getBo() {
		return bo;
	}

	public void setBo(RoleBO bo) {
		this.bo = bo;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getUserFunctionList() {
		return userFunctionList;
	}

	public void setUserFunctionList(List<String> userFunctionList) {
		this.userFunctionList = userFunctionList;
	}

	public List<String> getBaseFunctionList() {
		return baseFunctionList;
	}

	public void setBaseFunctionList(List<String> baseFunctionList) {
		this.baseFunctionList = baseFunctionList;
	}

	public List<String> getMessageFunctionList() {
		return messageFunctionList;
	}

	public void setMessageFunctionList(List<String> messageFunctionList) {
		this.messageFunctionList = messageFunctionList;
	}

}
