package com.gdcp.pas.login.dao.impl;

/**
 * @author 刘伯睿  2015-03-13  
 * @see 提供数据库中校验用户名密码的方法
 */
import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.login.dao.LoginDAO;
import com.gdcp.pas.login.vo.LoginVO;

public class LoginDAOImpl implements LoginDAO {
    DbAccess dbAccess = new DbAccess();

    public boolean login(LoginVO loginVO) throws Exception {
        boolean flag = false;
        String sql = "select * from tb_user where teacherId='"
                + SqlUtil.getInstance().replaceSql(loginVO.getTeacherId()) + "'";
        RowSet rs = dbAccess.executeQuery(sql);
        if (rs != null && rs.next()) {
            String password = rs.getString("password");
            if (password.equals(loginVO.getPassword().trim())) {
                flag = true;
            }
        }
        return flag;
    }

}
