package com.gdcp.pas.manage.action;

/**
 * @author �ſ���  2015-03-26 
 * @see �ṩ���۹�ϵ�����ѯ��ɾ�������Ӳ���
 */
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.common.Page;
import com.gdcp.common.extend.ExtendCodeUtil;
import com.gdcp.pas.manage.bo.DeptBO;
import com.gdcp.pas.manage.bo.ScoreResultBO;
import com.gdcp.pas.manage.vo.DeptVO;
import com.gdcp.pas.manage.vo.ScoreResultVO;
import com.gdcp.pas.score.bo.AssessQueryBO;
import com.opensymphony.xwork2.ActionContext;

public class ScoreResultAction {
    private static Logger logger = Logger.getLogger(ScoreResultAction.class);
    private ScoreResultBO srBO = new ScoreResultBO();
    private AssessQueryBO aqBO = new AssessQueryBO();

    private DeptBO deptBO = new DeptBO();
    private ScoreResultVO scoreResultVO; // ���ֹ�������
    private String ouserName; // ���ֶ�������
    private String suserName; // ������������
    private String deptName; // ������������
    private String userName; // �û������������
    private int odeptId; // ���ֶ�����ID
    private int sdeptId; // �������岿��ID
    private String objectId; // ���ֶ���ID
    private String scorerId; // ��������ID
    private String objectType; // ���ֶ�������
    private String scorerType; // ������������
    private String objectTypeId; // ���ֶ�������ID
    private String scorerTypeId; // ������������ID
    private int scoreRuleId; // ���ֹ���ID

    /**
     * @see ��ѯ�������۹�ϵ
     */
    public String queryPage() {
        HttpServletRequest request = ServletActionContext.getRequest();
        ScoreResultVO vo = new ScoreResultVO();
        String userType = request.getParameter("userType");
        String userName = request.getParameter("userName");
        vo.setUserType(userType);
        vo.setUserName(userName);
        List<ScoreResultVO> scoreResultList;

        Page page = new Page(request);
        try {
            scoreResultList = srBO.queryPage(page, vo);
            request.setAttribute("scoreResultList", scoreResultList); // �����������ֹ�ϵ��Ϣ

            request.setAttribute("userName", userName); // �����������ֹ�ϵ��Ϣ

            request.setAttribute("userType", userType);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("��ѯ�������ֹ�ϵʧ��", e);
        }
        return "queryPage_success";
    }

    /**
     * @throws UnsupportedEncodingException
     * @see ȷ��ɾ��һ�� ���۹�ϵ��¼
     */
    public String deleteRec() {
        ScoreResultVO srVO = new ScoreResultVO();
        srVO.setObjectId(objectId);
        srVO.setScorerId(scorerId);
        try {
            srBO.deleteRec(srVO);

        } catch (Exception e) {
            logger.error("ɾ�����۹�ϵ����ʧ��", e);
        }
        return "delete_success";
    }

    /**
     * @see �����û�������û�����ѯ���ֶ������������
     */
    public String query() {
        ScoreResultVO srVO = new ScoreResultVO();
        srVO.setUserName(userName); // �û����������
        // Map session=ActionContext.getContext().getSession();
        // session.put("userName", userName);
        List<ScoreResultVO> scoreResultList;
        HttpServletRequest request = ServletActionContext.getRequest();
        if (ouserName.equals(0 + "")) {
            srVO.setOuserName(ouserName);
            request.setAttribute("flag", true);
        } else {
            srVO.setOuserName(ouserName);
            request.setAttribute("flag", false);
        }

        try {
            scoreResultList = srBO.query(srVO);
            request.setAttribute("scoreResultList", scoreResultList); // �����������ֹ�ϵ��Ϣ
            request.setAttribute("userName", userName); // �����������ֹ�ϵ��Ϣ

        } catch (Exception e) {
            logger.error("��ѯ���۹�ϵ��¼ʧ��", e);
        }

        return "query_success";
    }

    /**
     * @see ��ת�����ҳ��
     */
    public String queryDept() {
        List<DeptVO> deptList;
        List<ScoreResultVO> scoreResultList;
        List<ScoreResultVO> objectTypeList;
        List<ScoreResultVO> scoreTypeList;
        try {
            deptList = deptBO.queryAll();
            scoreResultList = srBO.queryRuleName();
            objectTypeList = srBO.queryObjectType();
            scoreTypeList = srBO.queryScorerType();
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("deptList", deptList); // �������в�����Ϣ
            request.setAttribute("scoreResultList", scoreResultList); // �������й�������
            request.setAttribute("objectTypeList", objectTypeList); // �������ֶ�������
            request.setAttribute("scoreTypeList", scoreTypeList); // ����������������
        } catch (Exception e) {
            logger.error("��ѯ����ʧ��", e);
        }
        return "query_deptSuccess";
    }

    /**
     * @see �������ֶ�����ID���������岿��ID��ȡ�Ĳ����µ������û�����
     */
    public void queryName() {
        List<ScoreResultVO> NameList;
        try {
            if (sdeptId == 0 && odeptId != 0) {
                NameList = srBO.queryObjectName(odeptId);
                HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
                        .get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);

                String str = "";
                for (int i = 0; i < NameList.size(); i++) {
                    if (i == (NameList.size() - 1)) {
                        str = str + NameList.get(i).getOuserName() + "@#" + NameList.get(i).getObjectId();
                    } else {
                        str = str + NameList.get(i).getOuserName() + "@#" + NameList.get(i).getObjectId() + ",";
                    }
                }
                String CONTENT_TYPE = "text/html; charset=UTF-8";
                response.setContentType(CONTENT_TYPE);
                PrintWriter out = response.getWriter();
                out.print(str);
            }
            if (odeptId == 0 && sdeptId != 0) {
                NameList = srBO.queryScorerName(sdeptId);
                HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
                        .get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
                String str = "";
                for (int i = 0; i < NameList.size(); i++) {
                    if (i == (NameList.size() - 1)) {
                        str = str + NameList.get(i).getSuserName() + "@#" + NameList.get(i).getScorerId();
                    } else {
                        str = str + NameList.get(i).getSuserName() + "@#" + NameList.get(i).getScorerId() + ",";
                    }

                }
                String CONTENT_TYPE = "text/html; charset=UTF-8";
                response.setContentType(CONTENT_TYPE);
                PrintWriter out = response.getWriter();
                out.print(str);
            }

        } catch (Exception e) {
            logger.error("��ѯ�û�����ʧ��", e);
        }
    }

    /**
     * @param scoreResultVO
     *            ScoreResultVO
     * @see �����ֽ�����������һ�����۹�ϵ��¼
     */
    public String insertRec() {
        ScoreResultVO srVO = new ScoreResultVO();
        srVO.setObjectId(objectId);
        srVO.setScorerId(scorerId);
        srVO.setScoreruleId(scoreRuleId);
        srVO.setObjectTypeId(objectTypeId);
        srVO.setScorerTypeId(scorerTypeId);
        try {
            srBO.insertRec(srVO);
        } catch (Exception e) {
            logger.error("��ѯ�û�����ʧ��", e);
        }

        return "insert_success";
    }

    /**
     * @author ��ΰ�� ��ȡ����������ֹ�ϵʱ��Ҫʱ��Ϣ
     */
    public String getBatchAddMessage() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        HttpServletRequest request = ServletActionContext.getRequest();

        ArrayList<DeptVO> depts = new ArrayList<>();

        depts = (ArrayList<DeptVO>) deptBO.queryAll();

        request.setAttribute("depts", depts);

        ArrayList<DeptVO> deptSs = new ArrayList<>();

        DeptVO dept = new DeptVO();

        dept = new DeptVO();
        dept.setDeptId(100002);
        dept.setDeptName("�칫��");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100004);
        dept.setDeptName("����");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100010);
        dept.setDeptName("���ڻ�����");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100015);
        dept.setDeptName("������������");
        deptSs.add(dept);

        dept.setDeptId(100017);
        dept.setDeptName("����");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100018);
        dept.setDeptName("��ҵָ������");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100020);
        dept.setDeptName("�Ƽ���");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100022);
        dept.setDeptName("���´�");
        deptSs.add(dept);

        dept = new DeptVO();
        dept.setDeptId(100028);
        dept.setDeptName("ѧ����");
        deptSs.add(dept);

        request.setAttribute("deptSs", deptSs);

        // �˽�1002׷��

        ArrayList<DeptVO> deptAa = new ArrayList<>();
        deptAa = (ArrayList<DeptVO>) deptBO.queryAll();
        request.setAttribute("deptAa", deptAa);

        request.setAttribute("objectTypes", ExtendCodeUtil.getInstance().getExtendCodeListByType("objectType"));

        request.setAttribute("scorerTypes", ExtendCodeUtil.getInstance().getExtendCodeListByType("scorerType"));

        if (session.get("message") != null) {
            request.setAttribute("message", session.get("message"));
            session.put("message", null);
        }

        ArrayList<ScoreResultVO> scoreRules;
        try {
            scoreRules = (ArrayList<ScoreResultVO>) srBO.queryRuleName();
            if (scoreRules != null && scoreRules.size() > 0) {
                request.setAttribute("scoreRules", scoreRules);
            } else {
                request.setAttribute("scoreRules", null);
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        return "getBatchAddMessage_success";
    }

    public int getOdeptId() {
        return odeptId;
    }

    public void setOdeptId(int odeptId) {
        this.odeptId = odeptId;
    }

    public AssessQueryBO getAqBO() {
        return aqBO;
    }

    public void setAqBO(AssessQueryBO aqBO) {
        this.aqBO = aqBO;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ScoreResultAction.logger = logger;
    }

    public ScoreResultBO getSrBO() {
        return srBO;
    }

    public void setSrBO(ScoreResultBO srBO) {
        this.srBO = srBO;
    }

    public ScoreResultVO getScoreResultVO() {
        return scoreResultVO;
    }

    public void setScoreResultVO(ScoreResultVO scoreResultVO) {
        this.scoreResultVO = scoreResultVO;
    }

    public String getOuserName() {
        return ouserName;
    }

    public void setOuserName(String ouserName) {
        this.ouserName = ouserName;
    }

    public String getSuserName() {
        return suserName;
    }

    public void setSuserName(String suserName) {
        this.suserName = suserName;
    }

    public DeptBO getdBO() {
        return deptBO;
    }

    public void setdBO(DeptBO deptBO) {
        this.deptBO = deptBO;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getScorerId() {
        return scorerId;
    }

    public void setScorerId(String scorerId) {
        this.scorerId = scorerId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getScorerType() {
        return scorerType;
    }

    public void setScorerType(String scorerType) {
        this.scorerType = scorerType;
    }

    public int getScoreRuleId() {
        return scoreRuleId;
    }

    public void setScoreRuleId(int scoreRuleId) {
        this.scoreRuleId = scoreRuleId;
    }

    public int getSdeptId() {
        return sdeptId;
    }

    public void setSdeptId(int sdeptId) {
        this.sdeptId = sdeptId;
    }

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getScorerTypeId() {
        return scorerTypeId;
    }

    public void setScorerTypeId(String scorerTypeId) {
        this.scorerTypeId = scorerTypeId;
    }
}
