package com.gdcp.pas.manage.action;

/**
 * @author �ſ���
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
	private String objectId; // �Ӽ��ֶ���ID
	private int score; // ����
	private String describe; // ����
	private String userName; // �Ӽ��ֶ�������
	private int status; // �ӷֻ����
	private int oldstatus; // ԭ�ӷֻ����
	private String id; // ��¼Id

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
			logger.error("��ѯҳ�����", e);
		}

		return "queryPage_success";
	}

	/**
	 * �����Ӻ��ѯ���ݵ����ҳ��
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
			logger.error("�����Ӻ��ѯ���ݵ����ҳ�����", e);
		}
		return "insertTo_seccess";
	}

	/**
	 * �����ݿ������һ���Ӽ�����
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
			logger.error("�����ݿ������һ���Ӽ��������", e);
		}
		return "insert_success";
	}

	/**
	 * ɾ��һ���Ӽ�����
	 * 
	 * @return
	 */
	public String deleteScore() {
		try {
			aasBO.deleteScore(id);
		} catch (Exception e) {
			logger.error("ɾ��һ���Ӽ�����", e);
		}
		return "delete_success";
	}

	/**
	 * ���ݶ��������ڼӼ��ֱ��в�ѯ
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
			logger.error("���ݶ��������ڼӼ��ֱ��в�ѯ����", e);
		}
		return "query_success";
	}

	/**
	 * ���ݶ����������û����в�ѯ
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
			logger.error("���ݶ����������û����в�ѯ", e);
		}
	}

	/**
	 * ���ݶ���ID�����ݴ�������ҳ��
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
			logger.error("���ݶ���ID�����ݴ�������ҳ�����", e);
		}
		return "queryTo_success";

	}

	/**
	 * ���¼Ӽ�����
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
			logger.error("���¼Ӽ��������", e);
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
