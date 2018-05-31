package com.gdcp.pas.manage.action;

/**
 * @author 陈永鑫   2015-03-13  
 * @see 提供用户身份管理的界面响应请求
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.base.BaseAction;
import com.gdcp.pas.manage.bo.UserLevelBO;
import com.gdcp.pas.manage.dao.impl.ScoreResultbak;
import com.gdcp.pas.manage.vo.UserLevelVO;
import com.opensymphony.xwork2.ActionContext;

public class UserlevelAction extends BaseAction {

	private static Logger logger = Logger.getLogger(RoleAction.class);
	private String userLevelName;// 用户身份名
	private String remark;// 备注

	private int userLevelId;// 用户身份ID

	private List<String> UserlevelList;// 批量删除的List
	private String userName;// 用户输入Ajax检测名字

	/**
	 * @see 更新
	 */
	public String updata() throws UnsupportedEncodingException {
		Map<String, Object> session = ActionContext.getContext().getSession();

		session.put("userLevelId", userLevelId);
		session.put("userLevelName", new String(userLevelName.getBytes("ISO-8859-1"), "utf-8"));
		session.put("remark", new String(remark.getBytes("ISO-8859-1"), "utf-8"));
		/*
		 * session.put("userLevelId", userLevelId);
		 * session.put("userLevelName",userLevelName);
		 * session.put("remark",remark);
		 */

		return "Userlevel_Update_success";
	}

	public String trueupdate() throws UnsupportedEncodingException {
		UserLevelBO userlevelBO = new UserLevelBO();
		UserLevelVO userlevelVO = new UserLevelVO();
		userlevelVO.setUserLevelName(userLevelName);
		userlevelVO.setRemark(remark.trim());

		try {
			userlevelBO.updata(userLevelId, userlevelVO);
		} catch (Exception e) {
			logger.error("", e);
		}

		return "Update_success";
	}

	/**
	 * @see 批量删除
	 */

	public String delUserlevelList() {
		String[] StringArr = null;
		if (UserlevelList.size() > 0) {
			String Them = UserlevelList.get(0);
			StringArr = Them.split(",");
		}

		UserLevelBO userlevelBO = new UserLevelBO();
		int a;
		try {
			a = userlevelBO.executeBatchDelete(StringArr);
			if (a > 0) {
				return "Delete_List_success";
			}

		} catch (Exception e) {
			logger.error("批量删除", e);
		}

		return "Delete_List_failed";
	}

	/**
	 * @see 删除
	 */
	public String delete() {

		UserLevelBO userlevelbo = new UserLevelBO();
		int a = 0;
		try {
			a = userlevelbo.delete(userLevelId);
		} catch (Exception e) {
			logger.error("删除", e);
		}

		if (a > 0) {
			return "Delete_List_success";
		} else {
			return "Delete_userlevel_failed";
		}

	}

	/**
	 * @see 检查名字
	 */
	public void Check() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String str = URLDecoder.decode(userName, "UTF-8");
		boolean test = false;
		UserLevelBO userlevelBO = new UserLevelBO();
		try {
			test = userlevelBO.checkName(str);
		} catch (Exception e) {
			logger.error("检查名字", e);
		}
		PrintWriter out = response.getWriter();
		out.print(test);
		out.flush();
		out.close();

	}

	/**
	 * @see 查询
	 */
	public String qurery() {
		try {
			// userlevelList = userlevelBO.queryAll();
			// session.put("userlevlet", userlevelList);

			new ScoreResultbak();
		} catch (Exception e) {
			logger.error("查询失败", e);
		}
		return "Userlevel_qurery_success";
	}

	/**
	 * @see 增加
	 */

	public String addUserlevel() {
		UserLevelVO userlevelVO = new UserLevelVO();
		userlevelVO.setUserLevelName(userLevelName.trim());
		userlevelVO.setRemark(remark.trim());
		UserLevelBO userlevelBO = new UserLevelBO();
		int a = 0;
		try {
			a = userlevelBO.save(userlevelVO);
		} catch (Exception e) {
			logger.error("增加", e);
		}
		if (a > 0) {

			return "Userlevel_Add_success";
		} else {
			return "Userlevel_Add_failed";
		}
	}

	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(int userLevelId) {
		this.userLevelId = userLevelId;
	}

	public List<String> getUserlevelList() {
		return UserlevelList;
	}

	public void setUserlevelList(List<String> userlevelList) {
		UserlevelList = userlevelList;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		UserlevelAction.logger = logger;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
