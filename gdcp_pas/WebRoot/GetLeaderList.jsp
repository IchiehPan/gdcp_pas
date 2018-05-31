<%@page import="com.gdcp.pas.manage.vo.UserVO"%>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.dao.impl.*" %>
<%@ page import="com.gdcp.pas.manage.vo.*" %>
<%
	UserDAOImpl dao = new UserDAOImpl();
	try{ 
		//List<UserVO> list = dao.getDeptLeaderByUser("1973110013");
		List<UserVO> list = dao.getLeaderList();
		for(int i=0;i<list.size();i++){
			UserVO vo = list.get(i);
			out.print("ÓÃ»§Ãû£º" + vo.getUserName() + "<br>");
		}
	}catch(Exception e){
		System.out.println(e.toString());
	}

%>