 <%@ page import="javax.sql.*" %>
 <%@ page import="java.util.*" %>
 <%@ page import="com.gdcp.common.db.*" %>
 <%
	DbAccess dbAccess = new DbAccess();
	int heightNum = 73;
	int middleNum =60;
	int lowNum = 6;
	int otherNum = 47;//综合办主任及教务员、辅导员、工勤岗

	ArrayList allList = new ArrayList();
	ArrayList keyList = new ArrayList();
	ArrayList userList = new ArrayList();
	String hieghtSql = "select * from tb_user where usercharacter not in ('100211','100212','100213','100214','100215') and EVALPOSITION in('100113') and USERLEVEL in('3','4','5','6','7')";

	String middleSql = "select * from tb_user where usercharacter not in ('100211','100212','100213','100214','100215') and EVALPOSITION in('100113') and USERLEVEL in('8','9','10')";

	String lowSql = "select * from tb_user where usercharacter not in ('100211','100212','100213','100214','100215') and EVALPOSITION in('100113') and USERLEVEL in('11','12','13','14')";

	String otherSql = "select * from tb_user where usercharacter not in ('100211','100212','100213','100214','100215') and EVALPOSITION in('100116','100118','100112','100117') ";

	Random random = new Random();
	RowSet rs  = null;

	/****************************高级*************************************/

	try{
		rs = dbAccess.executeQuery(hieghtSql);
		while(rs!=null && rs.next()){
			allList.add(rs.getString("userId"));
		}
		System.out.println("allList.size():" + allList.size());
		if(allList.size() > heightNum){
			while(keyList.size() < heightNum){
				int temp = random.nextInt(allList.size());
				if(!keyList.contains(temp)){
					keyList.add(temp);
					System.out.println(temp);
					userList.add(allList.get(temp));
				} 
			}
		}else{
			 for(int i=0;i<allList.size();i++){
				keyList.add(i);
				userList.add(allList.get(i));
				//System.out.println(keyList.get(i));
			 }
		}



	}catch(Exception e){
		System.out.println(e.toString());
		out.println(e.toString());
	}
 	/****************************高级 end*************************************/


	/****************************中级*************************************/
	try{
		allList = new ArrayList();
		keyList = new ArrayList();
		rs = dbAccess.executeQuery(middleSql);
		while(rs!=null && rs.next()){
			allList.add(rs.getString("userId"));
		}
		if(allList.size() > middleNum){
			while(keyList.size() < middleNum){
				int temp = random.nextInt(allList.size());
				if(!keyList.contains(temp)){
					keyList.add(temp);
					System.out.println(temp);
					userList.add(allList.get(temp));
				} 
			}
		}else{
			 for(int i=0;i<allList.size();i++){
				keyList.add(i);
				userList.add(allList.get(i));
				//System.out.println(keyList.get(i));
			 }
		}



	}catch(Exception e){
		System.out.println(e.toString());
		out.println(e.toString());
	}
	/****************************中级 end*************************************/


	/****************************初级*************************************/
	try{
		allList = new ArrayList();
		keyList = new ArrayList();
		rs = dbAccess.executeQuery(lowSql);
		while(rs!=null && rs.next()){
			allList.add(rs.getString("userId"));
		}
		if(allList.size() > lowNum){
			while(keyList.size() < lowNum){
				int temp = random.nextInt(allList.size());
				if(!keyList.contains(temp)){
					keyList.add(temp);
					System.out.println(temp);
					userList.add(allList.get(temp));
				} 
			}
		}else{
			 for(int i=0;i<allList.size();i++){
				keyList.add(i);
				userList.add(allList.get(i));
				//System.out.println(keyList.get(i));
			 }
		}



	}catch(Exception e){
		System.out.println(e.toString());
		out.println(e.toString());
	}
	/****************************初级 end*************************************/


	/****************************综合办主任及教务员、辅导员、工勤岗*********************************/
	try{
		allList = new ArrayList();
		keyList = new ArrayList();
		rs = dbAccess.executeQuery(otherSql);
		while(rs!=null && rs.next()){
			allList.add(rs.getString("userId"));
		}
		if(allList.size() > otherNum){
			while(keyList.size() < otherNum){
				int temp = random.nextInt(allList.size());
				if(!keyList.contains(temp)){
					keyList.add(temp);
					System.out.println(temp);
					userList.add(allList.get(temp));
				} 
			}
		}else{
			 for(int i=0;i<allList.size();i++){
				keyList.add(i);
				userList.add(allList.get(i));
				//System.out.println(keyList.get(i));
			 }
		}



	}catch(Exception e){
		System.out.println(e.toString());
		out.println(e.toString());
	}
	/********************综合办主任及教务员、辅导员、工勤岗end*************************************/



	String sql = "update tb_user set ISPROFESSIONAL='1' where USERID in (''";
	for(int i=0;i<userList.size();i++){
		sql = sql + ",'" + userList.get(i) + "'";
	}
	sql = sql + ")";
	System.out.println(sql);
	try{
		dbAccess.executeUpdate(sql);
	}catch(Exception e){
		out.println(e.toString());
	}
 %>