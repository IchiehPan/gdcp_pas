package com.gdcp.pas.manage.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.bo.DeptBO;
import com.gdcp.pas.manage.vo.DeptVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author ����:���Ľ�
 * @version ����ʱ�䣺2015-3-22 ����1:37:23 ��˵�� �ṩ���Ź���Ľ�����Ӧ����
 */
public class DeptAction {
	private static Logger logger = Logger.getLogger(DeptAction.class);
	private DeptBO deptBO = new DeptBO();
	private int deptId; // ����id
	private String deptName; // ��������
	private int deptType; // ��������
	private String remark; // ��ע
	List<String> deletelist; // Ҫ����ɾ����Id��
	String checkDeptName; // ���Ĳ�������

	/**
	 * @return ��ѯ���еĲ���
	 */
	public String query() {

		List<DeptVO> deptVOlist;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Page page = new Page(request);
			DeptVO qDeptVO = new DeptVO();
			String deptName = request.getParameter("Conditions_deptName");
			if (deptName != null) {
				qDeptVO.setDeptName(deptName.trim());
			}
			deptVOlist = deptBO.queryPage(page, qDeptVO);

			request.setAttribute("deptVO", deptVOlist);
			request.setAttribute("page", page);
			request.setAttribute("Conditions_deptName", deptName);

		} catch (Exception e) {
			logger.error("��ѯ���еĲ��ų���", e);
		}
		return "query_success";
	}

	/**
	 * @return ����һ������
	 */
	public String insertRec() {
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptName(deptName.trim());
		deptVO.setDeptType(deptType);
		deptVO.setRemark(remark.trim());
		try {
			deptBO.insertRec(deptVO);
		} catch (Exception e) {
			logger.error("����һ�����ų���", e);
		}
		return "insert_success";
	}

	/**
	 * @return ɾ��һ������
	 */
	public String deleteRec() {
		try {
			deptBO.deleteRec(deptId);
		} catch (Exception e) {
			logger.error("ɾ��һ�����ų���", e);
		}
		return "delete_success";
	}

	/**
	 * @return ����ɾ������
	 * @throws Exception
	 */
	public String deleteBatchRec() throws Exception {
		String[] StringArr = null;
		if (deletelist.size() > 0) {
			String Them = deletelist.get(0);
			StringArr = Them.split(",");
		}
		deptBO.deleteBatchRec(StringArr);

		return "delete_batch_success";
	}

	/**
	 * @return ��õ�ǰҪ�޸ĵĲ�������
	 */
	public String update() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("deptId", deptId);
		session.put("deptType", deptType);
		try {
			session.put("deptName", new String(deptName.getBytes("ISO-8859-1"), "utf-8"));
			session.put("remark", new String(remark.getBytes("ISO-8859-1"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("��õ�ǰҪ�޸ĵĲ������ݳ���", e);
		}
		return "get_update_success";
	}

	/**
	 * @return �޸Ĳ���
	 */
	public String updateRec() {
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptId(deptId);
		deptVO.setDeptName(deptName);
		deptVO.setDeptType(deptType);
		deptVO.setRemark(remark);
		try {
			deptBO.updateRec(deptVO);
		} catch (Exception e) {
			logger.error("�޸Ĳ���", e);
		}
		return "update_success";
	}

	/**
	 * ��鲿�����Ƿ����
	 * 
	 * @throws Exception
	 */
	public void checkName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String str = URLDecoder.decode(checkDeptName, "UTF-8");
		boolean test = false;
		DeptBO deptBO = new DeptBO();
		test = deptBO.checkName(str);
		PrintWriter out = response.getWriter();
		out.print(test);
		out.flush();
		out.close();
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getDeletelist() {
		return deletelist;
	}

	public void setDeletelist(List<String> deletelist) {
		this.deletelist = deletelist;
	}

	public String getCheckDeptName() {
		return checkDeptName;
	}

	public void setCheckDeptName(String checkDeptName) {
		this.checkDeptName = checkDeptName;
	}

	public int getDeptType() {
		return deptType;
	}

	public void setDeptType(int deptType) {
		this.deptType = deptType;
	}

}
