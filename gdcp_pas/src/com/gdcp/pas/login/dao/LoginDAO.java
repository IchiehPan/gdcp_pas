package com.gdcp.pas.login.dao;

/**
 * @author 刘伯睿  2015-03-13 
 * @see 提供数据库中校验用户名密码的方法
 */
import com.gdcp.pas.login.vo.LoginVO;

public interface LoginDAO {
    public boolean login(LoginVO loginVO) throws Exception;
}
