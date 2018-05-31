package com.gdcp.pas.manage.action;
/**
 * @author 张俊杰   2015-03-19  
 * @see 提供用户角色关系管理的界面响应请求
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
    private String str; // 批量删除时用户和角色的字符串
    private UserRoleVO userRoleVO; // 用户角色对象
    private String userName; // 用户姓名
    private String roleName; // 角色姓名
    private String oldRoleName; // 修改前，原角色姓名
    private String oldUserName; // 修改前，原用户名字

    /**
     * @see 查询所用用户角色信息
     */
    public String queryPage() {
        List<UserRoleVO> roleList;
        try {
            roleList = bo.queryPage(null);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("userRoleList", roleList);
        } catch (Exception e) {
            logger.error("查询所用用户角色信息", e);
        }

        return "queryPage_success";

    }

    /**
     * @see 确认删除一条记录
     */
    public String deleteRec() {
        UserRoleVO ur = new UserRoleVO();
        ur.setUserName(userName);
        ur.setRoleName(roleName);
        try {
            bo.deleteRec(ur);
        } catch (Exception e) {
            logger.error("确认删除一条记录", e);
        }
        return "delete_success";
    }

    /**
     * @see 批量删除
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
                logger.error("批量删除", e);
            }
        }

        return "delete_success";
    }

    /**
     * @see 修改时将数据带到修改页面
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
            // 查询所有用户和角色的名字
            List<UserRoleVO> userName = bo.queryUserName();
            List<UserRoleVO> roleName = bo.queryRoleName();
            request.setAttribute("userName", userName);
            request.setAttribute("roleName", roleName);
        } catch (Exception e) {
            logger.error("修改时将数据带到修改页面", e);
        }

        return "update";
    }

    /**
     * @see 确认修改
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
            logger.error("确认修改", e);
        }
        return "update_success";
    }

    /**
     * @see 添加用户角色信息
     */
    public String insertRec() {
        UserRoleVO ur = new UserRoleVO();
        ur.setUserName(userName);
        ur.setRoleName(roleName);
        try {
            bo.insertRec(ur);
        } catch (Exception e) {
            logger.error("添加用户角色信息", e);
        }
        return "insert_success";
    }

    /**
     * @see 查询所用用户名字和角色名称
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
            logger.error("查询所用用户名字和角色名称", e);
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
