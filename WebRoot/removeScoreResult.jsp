
<%@ page import="javax.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gdcp.common.db.*"%>
<%
	DbAccess dbAccess = new DbAccess();
	double highNum = 0.05;
	double lowNum = 0.1;

	String sql = "select distinct OBJECTID,objecttype,scorertype from TB_SCORERESULT where OBJECTTYPE <> '100120' and OBJECTTYPE <> '100121'";

	RowSet rs = null;

	try {
		rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			String objectId = rs.getString("OBJECTID");
			String objectType = rs.getString("objecttype");
			String scorerType = rs.getString("scorertype");
			String countSql = "select count(*) from TB_SCORERESULT where OBJECTID='" + objectId
					+ "' and objecttype='" + objectType + "' and scorertype='" + scorerType
					+ "' and status='2'";
			RowSet rs1 = dbAccess.executeQuery(countSql);
			int count = 21;
			if (rs1 != null && rs1.next()) {
				count = rs1.getInt(1);
			}
			int a1 = (int) (count * highNum);
			int a2 = (int) (count * lowNum);
			System.out.println("a1:" + a1);
			System.out.println("a2:" + a2);
			if (a2 > 0) {
				String tempSql = "select * from TB_SCORERESULT where OBJECTID='" + objectId
						+ "' and objecttype='" + objectType + "' and scorertype='" + scorerType
						+ "' and status='2' order by scoreresult desc";
				rs1 = dbAccess.executeQuery(tempSql);
				int tempCount = 0;
				while (rs1 != null && rs1.next()) {
					if (tempCount < a1 || tempCount >= count - a2) {
						String recordId = rs1.getString("id");
						String updateSql = "update TB_SCORERESULT set status='-1' where id='" + recordId + "'";
						dbAccess.executeUpdate(updateSql);

					}
					tempCount++;
				}
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
%>