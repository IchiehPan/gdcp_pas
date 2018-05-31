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
        // 启动时先实例化枚举类，和读取权限文件
        PermissionRead.getInstance();
        ExtendCodeUtil.getInstance();
    }

}
