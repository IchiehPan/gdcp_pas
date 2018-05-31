package com.gdcp.pas.manage.action;
/**
 * @author �ſ���   2015-03-19  
 * @see �ṩ�û���ɫ��ϵ����Ľ�����Ӧ����
 */

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.base.BaseAction;
import com.gdcp.pas.manage.bo.UserRoleBO;
import com.gdcp.pas.manage.vo.UserRoleVO;

public class UserRoleAction extends BaseAction {
    private static Logger logger = Logger.getLogger(UserRoleAction.class);
    private UserRoleBO bo = new UserRoleBO();
    private String str; // ����ɾ��ʱ�û��ͽ�ɫ���ַ���
    private UserRoleVO userRoleVO; // �û���ɫ����
    private String userName; // �û�����
    private String roleName; // ��ɫ����
    private String oldRoleName; // �޸�ǰ��ԭ��ɫ����
    private String oldUserName; // �޸�ǰ��ԭ�û�����

    /**
     * @see ��ѯ�����û���ɫ��Ϣ
     */
    public String queryPage() {
        List<UserRoleVO> roleList;
        try {
            roleList = bo.queryPage(null);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("userRoleList", roleList);
        } catch (Exception e) {
            logger.error("��ѯ�����û���ɫ��Ϣ", e);
        }

        return "queryPage_success";

    }

    /**
     * @see ȷ��ɾ��һ����¼
     */
    public String deleteRec() {
        UserRoleVO ur = new UserRoleVO();
        ur.setUserName(userName);
        ur.setRoleName(roleName);
        try {
            bo.deleteRec(ur);
        } catch (Exception e) {
            logger.error("ȷ��ɾ��һ����¼", e);
        }
        return "delete_success";
    }

    /**
     * @see ����ɾ��
     */
    public String deleteList() {
        String[] userRoleArr = str.split(",");
        for (int i = 0; i < userRoleArr.length / 2; i++) {
            UserRoleVO ur = new UserRoleVO();
            ur.setUserName(userRoleArr[i]);
            ur.setRoleName(userRoleArr[(userRoleArr.length / 2) + i]);
            try {
                bo.deleteRec(ur);
            } catch (Exception e) {
                logger.error("����ɾ��", e);
            }
        }

        return "delete_success";
    }

    /**
     * @see �޸�ʱ�����ݴ����޸�ҳ��
     */
    public String update() {
        UserRoleVO ur = new UserRoleVO();
        String cuserName;
        try {
            cuserName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
            ur.setUserName(cuserName);
            String croleName = new String(roleName.getBytes("ISO-8859-1"), "UTF-8");
            ur.setRoleName(croleName);
            List<UserRoleVO> userRoleList = new ArrayList<>();
            userRoleList.add(ur);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("userRoleList", userRoleList);
            // ��ѯ�����û��ͽ�ɫ������
            List<UserRoleVO> userName = bo.queryUserName();
            List<UserRoleVO> roleName = bo.queryRoleName();
            request.setAttribute("userName", userName);
            request.setAttribute("roleName", roleName);
        } catch (Exception e) {
            logger.error("�޸�ʱ�����ݴ����޸�ҳ��", e);
        }

        return "update";
    }

    /**
     * @see ȷ���޸�
     */
    public String updateConfi() {
        UserRoleVO ur = new UserRoleVO();
        ur.setUserName(userName);
        ur.setRoleName(roleName);
        ur.setOldRoleName(oldRoleName);
        ur.setOldUserName(oldUserName);
        try {
            bo.updateConfi(ur);
        } catch (Exception e) {
            logger.error("ȷ���޸�", e);
        }
        return "update_success";
    }

    /**
     * @see ����û���ɫ��Ϣ
     */
    public String insertRec() {
        UserRoleVO ur = new UserRoleVO();
        ur.setUserName(userName);
        ur.setRoleName(roleName);
        try {
            bo.insertRec(ur);
        } catch (Exception e) {
            logger.error("����û���ɫ��Ϣ", e);
        }
        return "insert_success";
    }

    /**
     * @see ��ѯ�����û����ֺͽ�ɫ����
     */
    public String queryUserRole() {
        List<UserRoleVO> userName;
        try {
            userName = bo.queryUserName();
            List<UserRoleVO> roleName = bo.queryRoleName();
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("userName", userName);
            request.setAttribute("roleName", roleName);
        } catch (Exception e) {
            logger.error("��ѯ�����û����ֺͽ�ɫ����", e);
        }

        return "queryUserRole_success";
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        UserRoleAction.logger = logger;
    }

    public UserRoleBO getBo() {
        return bo;
    }

    public void setBo(UserRoleBO bo) {
        this.bo = bo;
    }

    public UserRoleVO getUserRoleVO() {
        return userRoleVO;
    }

    public void setUserRoleVO(UserRoleVO userRoleVO) {
        this.userRoleVO = userRoleVO;
    }

    public String getArr() {
        return str;
    }

    public void setArr(String arr) {
        str = arr;
    }

    public String getOldRoleName() {
        return oldRoleName;
    }

    public void setOldRoleName(String oldRoleName) {
        this.oldRoleName = oldRoleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOldUserName() {
        return oldUserName;
    }

    public void setOldUserName(String oldUserName) {
        this.oldUserName = oldUserName;
    }

}
