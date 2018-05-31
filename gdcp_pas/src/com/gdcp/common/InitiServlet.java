package com.gdcp.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.gdcp.common.extend.ExtendCodeUtil;
import com.gdcp.pas.permission.PermissionRead;

public class InitiServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        // ����ʱ��ʵ����ö���࣬�Ͷ�ȡȨ���ļ�
        PermissionRead.getInstance();
        ExtendCodeUtil.getInstance();
    }

}
