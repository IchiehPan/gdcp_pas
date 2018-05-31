package com.gdcp.pas.score.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
/**
 * @author �ſ���  2015-03-29 
 * @see �ṩ���˲�ѯ����
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.bo.ScoreResultBO;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.DeptVO;
import com.gdcp.pas.manage.vo.ScoreResultVO;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.AssessQueryBO;
import com.gdcp.pas.score.config.ScoreResultConfig;
import com.gdcp.pas.score.vo.AssessQueryVO;
import com.opensymphony.xwork2.ActionContext;

public class AssessQueryAction {
	private static Logger logger = Logger.getLogger(AssessQueryAction.class);
	private AssessQueryBO aqBO = new AssessQueryBO();
	private ScoreResultBO srBO = new ScoreResultBO();
	private UserBO uBO = new UserBO();
	private int deptId; // ����ID

	private String objectTypeId; // ���˶�������ID

	/**
	 * @see ��ѯ���п��˶�����Ϣ
	 * @param deptList�������в�����Ϣ
	 * @param objectTypeList�������п��˶���������Ϣ
	 * @param assessList�����ѯ��Ŀ��˶�����Ϣ
	 */
	public String queryPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		AssessQueryVO vo = new AssessQueryVO();
		String deptId = request.getParameter("deptId");
		String objectTypeId = request.getParameter("objectTypeId");

		vo.setDeptId(deptId);
		vo.setObjectTypeId(objectTypeId);
		Page page = new Page(request);
		List<AssessQueryVO> assessList;
		List<DeptVO> deptList;
		List<ScoreResultVO> objectTypeList;
		try {
			assessList = aqBO.queryPage(page, vo);
			deptList = aqBO.queryDeptName();
			objectTypeList = srBO.queryObjectType();
			request.setAttribute("assessList", assessList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("objectTypeList", objectTypeList);
			request.setAttribute("page", page);
		} catch (Exception e) {
			logger.error("���˲�ѯʧ��", e);
		}

		if (session.get("message") != null) {
			request.setAttribute("message", session.get("message"));
			session.put("message", null);
		}

		return "queryPage_success";
	}

	public String queryNotSubmitScoreUser() {
		List<ScoreResultVO> notSubmitUserList = new ArrayList<>();
		List<String> teacherIdList = new ArrayList<>();
		try {
			notSubmitUserList.addAll(srBO.queryByStatus(ScoreResultConfig.STATUS_INIT));
			notSubmitUserList.addAll(srBO.queryByStatus(ScoreResultConfig.STATUS_SAVED));
		} catch (Exception e) {
			logger.error("��ѯδ�ύ����ʧ��", e);
		}

		if (notSubmitUserList.size() != 0) {
			for (ScoreResultVO vo : notSubmitUserList) {
				if (!teacherIdList.contains(vo.getScorerId())) {
					teacherIdList.add(vo.getScorerId());
				}
			}
		}

		List<UserVO> userList = new ArrayList<>();
		try {
			for (String scorerId : teacherIdList) {
				userList.add(uBO.getUserByTeacherId(scorerId));
			}
		} catch (Exception e) {
			logger.error("��ѯδ�ύ�����û�ʧ��", e);
		}

		String csvName = "δ�ύ�����û�.csv";
		StringBuffer csvContent = new StringBuffer("�̹���,����,��������,����,�Ƿ����\n");

		try {
			for (UserVO vo : userList) {
				csvContent.append(vo.getTeacherId() + ",");
				csvContent.append(vo.getUserName() + ",");
				csvContent.append(vo.getDeptName() + ",");
				csvContent.append(vo.getPassword() + ",");
				csvContent.append(vo.getIsProfessional());
				csvContent.append("\n");
			}

			outputExcel(csvName, csvContent);

		} catch (Exception e) {
			logger.error("��ѯû���ύ�������û���Ϣʧ��", e);
		}

		return null;
	}

	/**
	 * ����Excel���ļ���.csv���Ĺ��÷���
	 */
	private void outputExcel(String csvName, StringBuffer csvContent) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		csvName = new String(csvName.getBytes(), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment;filename=\"" + csvName + "\"");
		InputStream in = new ByteArrayInputStream(csvContent.toString().getBytes());
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int length = 0;

		while ((length = in.read(buffer)) != -1) {
			bos.write(buffer, 0, length);
		}

		if (in != null) {
			in.close();
		}
		if (bos != null) {
			bos.close();
		}
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		AssessQueryAction.logger = logger;
	}

	public AssessQueryBO getAqBO() {
		return aqBO;
	}

	public void setAqBO(AssessQueryBO aqBO) {
		this.aqBO = aqBO;
	}

	public ScoreResultBO getSrBO() {
		return srBO;
	}

	public void setSrBO(ScoreResultBO srBO) {
		this.srBO = srBO;
	}

	public String getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(String objectTypeId) {
		this.objectTypeId = objectTypeId;
	}
}
