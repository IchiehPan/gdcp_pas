package com.gdcp.pas.manage.action;

/**
 * @author 张俊杰
 * 15/4/23
 */
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.bo.AddAndSubBO;
import com.gdcp.pas.manage.vo.AddAndSubVO;
import com.opensymphony.xwork2.ActionContext;

public class AddAndSubAction {
	private static Logger logger = Logger.getLogger(AddAndSubAction.class);
	private AddAndSubBO aasBO = new AddAndSubBO();
	private String objectId; // 加减分对象ID
	private int score; // 分数
	private String describe; // 描述
	private String userName; // 加减分对象名称
	private int status; // 加分或减分
	private int oldstatus; // 原加分或减分
	private String id; // 纪录Id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String queryPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Page page = new Page(request);
		List<AddAndSubVO> addAndSubList = null;
		try {
			addAndSubList = aasBO.queryPage(page);
			request.setAttribute("addAndSubList", addAndSubList);
			request.setAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询页面出错", e);
		}

		return "queryPage_success";
	}

	/**
	 * 点击添加后查询数据到添加页面
	 * 
	 * @return
	 */
	public String AddAndSubTo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<AddAndSubVO> addAndSubList = null;
		try {
			addAndSubList = aasBO.AddAndSubTO();
			request.setAttribute("addAndSubList", addAndSubList);
		} catch (Exception e) {
			logger.error("点击添加后查询数据到添加页面出错", e);
		}
		return "insertTo_seccess";
	}

	/**
	 * 向数据库中添加一条加减分项
	 * 
	 * @return
	 */
	public String AddOrSub() {
		AddAndSubVO aasVO = new AddAndSubVO();
		aasVO.setUserName(userName);
		aasVO.setScore(score);
		aasVO.setStatus(status);
		aasVO.setDescribe(describe);
		try {
			aasBO.AddOrSub(aasVO);
		} catch (Exception e) {
			logger.error("向数据库中添加一条加减分项出错", e);
		}
		return "insert_success";
	}

	/**
	 * 删除一条加减分项
	 * 
	 * @return
	 */
	public String deleteScore() {
		try {
			aasBO.deleteScore(id);
		} catch (Exception e) {
			logger.error("删除一条加减分项", e);
		}
		return "delete_success";
	}

	/**
	 * 根据对象名称在加减分表中查询
	 * 
	 * @return
	 */
	public String queryByObjectId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<AddAndSubVO> addAndSubList = null;
		Page page = new Page(request);
		try {
			addAndSubList = aasBO.queryByObjectId(userName);
			request.setAttribute("addAndSubList", addAndSubList);
			request.setAttribute("page", page);
		} catch (Exception e) {
			logger.error("根据对象名称在加减分表中查询出错", e);
		}
		return "query_success";
	}

	/**
	 * 根据对象名称在用户表中查询
	 * 
	 * @return
	 */
	public void queryName() {

		List<AddAndSubVO> NameList = null;
		try {
			String userName1 = new String(userName.getBytes("ISO-8859-1"), "GBK");
			NameList = aasBO.queryName(userName1);
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
					.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
			String str = "";
			for (int i = 0; i < NameList.size(); i++) {
				if (i == (NameList.size() - 1)) {
					str = str + NameList.get(i).getUserName();
				} else {
					str = str + NameList.get(i).getUserName() + "@#";
				}
			}
			String CONTENT_TYPE = "text/html; charset=UTF-8";
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.print(str);
		} catch (Exception e) {
			logger.error("根据对象名称在用户表中查询", e);
		}
	}

	/**
	 * 根据对象ID将数据带到更新页面
	 * 
	 * @return
	 */
	public String queryByObjectIdTo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<AddAndSubVO> addAndSubList = null;
		List<AddAndSubVO> addAndSubList1 = null;
		try {
			objectId = id + "@";
			addAndSubList1 = aasBO.queryByObjectId(objectId);
			addAndSubList = aasBO.AddAndSubTO();
			request.setAttribute("addAndSubList", addAndSubList);
			request.setAttribute("addAndSubList1", addAndSubList1);
		} catch (Exception e) {
			logger.error("根据对象ID将数据带到更新页面错误", e);
		}
		return "queryTo_success";

	}

	/**
	 * 更新加减分项
	 * 
	 * @return
	 */
	public String AddOrSubUpdate() {
		AddAndSubVO aasVO = new AddAndSubVO();
		aasVO.setId(Integer.valueOf(id));
		aasVO.setObjectId(objectId);
		aasVO.setUserName(userName);
		aasVO.setScore(score);
		aasVO.setStatus(status);
		aasVO.setOldstatus(oldstatus);
		aasVO.setDescribe(describe);
		try {
			aasBO.AddOrSubUpdate(aasVO);
		} catch (Exception e) {
			logger.error("更新加减分项出错", e);
		}
		return "update_success";
	}

	public int getOldstatus() {
		return oldstatus;
	}

	public void setOldstatus(int oldstatus) {
		this.oldstatus = oldstatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		AddAndSubAction.logger = logger;
	}

	public AddAndSubBO getAasBO() {
		return aasBO;
	}

	public void setAasBO(AddAndSubBO aasBO) {
		this.aasBO = aasBO;
	}
}
