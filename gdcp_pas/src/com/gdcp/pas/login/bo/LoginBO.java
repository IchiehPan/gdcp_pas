package com.gdcp.pas.login.bo;

/**
 * @author 刘伯睿  2015-03-13 
 * @see 提供校验用户名密码的方法
 */
import com.gdcp.base.BaseBO;
import com.gdcp.pas.login.dao.LoginDAO;
import com.gdcp.pas.login.dao.impl.LoginDAOImpl;
import com.gdcp.pas.login.vo.LoginVO;

public class LoginBO extends BaseBO {
	LoginDAO loginDAO = new LoginDAOImpl();

	public boolean login(LoginVO loginVO) throws Exception {
		return loginDAO.login(loginVO);
	}
}
