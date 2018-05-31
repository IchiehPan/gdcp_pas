package com.gdcp.pas.manage.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.gdcp.common.Page;
import com.gdcp.pas.manage.bo.DeptBO;
import com.gdcp.pas.manage.bo.FunctionBO;
import com.gdcp.pas.manage.bo.RoleBO;
import com.gdcp.pas.manage.bo.URFunctionBO;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.DeptVO;
import com.gdcp.pas.manage.vo.FunctionVO;
import com.gdcp.pas.manage.vo.RoleVO;
import com.gdcp.pas.manage.vo.UserVO;

/**
 * @author 黄岸鹏 2015-03-19
 * @see 提供用户管理的界面响应请求
 */
public class UserAction {
	private static Logger logger = Logger.getLogger(UserAction.class);
	private UserBO userBO = new UserBO();
	private UserVO userVO;
	private String teacherId;
	private String userName;
	private String usersStr; // 批量删除时的1~n个userId字符串
	private List<String> userFunctionList;
	private List<String> baseFunctionList;
	private List<String> messageFunctionList;
	private String newPassword;
	private String oldPassword;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUsersStr() {
		return usersStr;
	}

	public void setUsersStr(String usersStr) {
		this.usersStr = usersStr;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String query() {
		List<UserVO> userVoList;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Page page = new Page(request);
			UserVO quserVO = new UserVO();
			String userName = request.getParameter("Conditions_userName");
			if (userName != null) {
				quserVO.setUserName(userName.trim());
			}
			userVoList = userBO.queryPage(page, quserVO);

			request.setAttribute("userList", userVoList);
			request.setAttribute("page", page);
			request.setAttribute("Conditions_userName", userName);

		} catch (Exception e) {
			logger.error("查询失败", e);
		}
		return "query_success";
	}

	public void updatePassword() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			String tId = URLDecoder.decode(teacherId, "UTF-8");
			String uPassWord = URLDecoder.decode(newPassword, "UTF-8");
			String uOldPassWord = URLDecoder.decode(oldPassword, "UTF-8");
			boolean test = false;
			UserVO userVO = new UserVO();
			userVO.setTeacherId(tId);
			userVO.setPassword(uPassWord);

			int a = userBO.updatePassword(userVO, uOldPassWord);

			if (a > 0) {
				test = true;
			}
			PrintWriter out = response.getWriter();
			out.print(test);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("更新用户密码失败", e);
		}

	}

	/**
	 * @see 根据id查询一个用户的记录&&跟这个用户相关联的其他表记录
	 */
	public String queryById() {
		RoleBO roleBO = new RoleBO();
		DeptBO deptBO = new DeptBO();
		// UserLevelBO userLevelBO = new UserLevelBO();
		// PositionBO positionBO = new PositionBO();
		FunctionBO functionBO = new FunctionBO();
		URFunctionBO urFunctionBO = new URFunctionBO();
		try {
			UserVO userVO = userBO.getUserByTeacherId(teacherId);
			List<RoleVO> roleList = roleBO.queryRole();
			List<DeptVO> deptList = deptBO.queryAll();
			// List<UserLevelVO> userLevelList = userLevelBO.queryAll();
			// List<PositionVO> positionList = positionBO.queryPosition();
			List<FunctionVO> functionList = functionBO.queryFunction();
			List<String> functionIds = urFunctionBO.queryURFunctionByUserId(teacherId);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("userVO", userVO);
			request.setAttribute("roleList", roleList);
			request.setAttribute("deptList", deptList);
			// request.setAttribute("userLevelList", userLevelList);
			// request.setAttribute("positionList", positionList);
			request.setAttribute("functionList", functionList);
			request.setAttribute("functionIds", functionIds);
		} catch (Exception e) {
			logger.error("根据id查询一个用户的记录&&跟这个用户相关联的其他表记录失败", e);
		}
		return "queryById_success";
	}

	/**
	 * @see 查询出全部的用户记录
	 */
	public String queryUsers() {
		try {
			List<UserVO> userList = userBO.queryUsers();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("userList", userList);
		} catch (Exception e) {
			logger.error("查询出全部的用户记录失败", e);
		}
		return "queryUsers_success";
	}

	/**
	 * @see 查询与用户表相关联的其他表信息，供添加时使用
	 */
	public String queryAddInfo() {
		RoleBO roleBO = new RoleBO();
		DeptBO deptBO = new DeptBO();
		/*
		 * UserLevelBO userLevelBO = new UserLevelBO(); PositionBO positionBO =
		 * new PositionBO();
		 */
		FunctionBO functionBO = new FunctionBO();
		try {
			List<RoleVO> roleList = roleBO.queryRole();
			List<DeptVO> deptList = deptBO.queryAll();
			// List<UserLevelVO> userLevelList = userLevelBO.queryAll();
			// List<PositionVO> positionList = positionBO.queryPosition();
			List<FunctionVO> functionList = functionBO.queryFunction();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("roleList", roleList);
			request.setAttribute("deptList", deptList);
			// request.setAttribute("userLevelList", userLevelList);
			// request.setAttribute("positionList", positionList);
			request.setAttribute("functionList", functionList);
		} catch (Exception e) {
			logger.error("查询与用户表相关联的其他表信息失败", e);
		}
		return "queryAddInfo_success";
	}

	/**
	 * @see 往数据库插入一个用户角色功能对应关系的记录
	 */
	public String addURFunction() {
		URFunctionBO urFunctionBO = new URFunctionBO();
		try {
			urFunctionBO.setUserFunction(userVO.getTeacherId(), userFunctionList, baseFunctionList,
					messageFunctionList);
		} catch (Exception e) {
			logger.error("往数据库插入一个用户角色功能对应关系的记录失败", e);
		}
		queryUsers();
		return "addURFunction_success";
	}

	/**
	 * @see 往数据库插入一个用户的记录
	 */
	public String addUser() {
		try {
			userBO.insertUser(userVO);
			addURFunction();
			queryUsers();
		} catch (Exception e) {
			logger.error("往数据库插入一个用户的记录失败", e);
		}
		return "addUser_success";
	}

	/**
	 * @see 删除数据库一个用户的记录
	 */
	public String delUser() {
		URFunctionBO urFunctionBO = new URFunctionBO();
		try {
			userBO.deleteUser(teacherId);
			urFunctionBO.delURFunction(teacherId);
			queryUsers();
		} catch (Exception e) {
			logger.error("删除数据库一个用户的记录失败", e);
		}
		return "delUser_success";
	}

	/**
	 * @see 批量删除数据库用户的记录
	 */
	public String delUsers() {
		URFunctionBO urFunctionBO = new URFunctionBO();
		String[] userArry = usersStr.split(",");
		try {
			for (int i = 0; i < userArry.length; i++) {
				userBO.deleteUser(userArry[i]);
				urFunctionBO.delURFunction(userArry[i]);
			}
			queryUsers();
		} catch (Exception e) {
			logger.error("批量删除数据库用户的记录失败", e);
		}
		return "delUsers_success";
	}

	/**
	 * @see 查询指定用户名的信息
	 */
	public String queryLikeName() {
		try {
			List<UserVO> userList = userBO.queryLikeName(userName);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("userList", userList);
		} catch (Exception e) {
			logger.error("查询指定用户名的信息失败", e);
		}
		return "queryLikeName_success";
	}

	/**
	 * @see 修改数据库一个用户的记录
	 */
	public String updateUser() {
		URFunctionBO urFunctionBO = new URFunctionBO();
		try {
			urFunctionBO.delURFunction(userVO.getTeacherId());
			userBO.updateUser(userVO);
			addURFunction();
			queryUsers();
		} catch (Exception e) {
			logger.error("修改数据库一个用户的记录失败", e);
		}
		return "updateUser_success";
	}

}
