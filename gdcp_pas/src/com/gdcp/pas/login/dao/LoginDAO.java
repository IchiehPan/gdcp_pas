package com.gdcp.pas.login.dao;

/**
 * @author �����  2015-03-13 
 * @see �ṩ���ݿ���У���û�������ķ���
 */
import com.gdcp.pas.login.vo.LoginVO;

public interface LoginDAO {
    public boolean login(LoginVO loginVO) throws Exception;
}
