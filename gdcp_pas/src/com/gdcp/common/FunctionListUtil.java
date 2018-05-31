package com.gdcp.common;

import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;
import org.apache.log4j.Logger;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.vo.FunctionVO;

public class FunctionListUtil {

    private static Logger logger = Logger.getLogger(FunctionListUtil.class);

    public static String rightsCheckboxHtml() {
        return rightsCheckboxHtml(null);
    }

    static DbAccess dbAccess = new DbAccess();

    public static String rightsCheckboxHtml(String[] checkedRights) {

        StringBuffer html = new StringBuffer(
                "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#a8c7ce\">");
        List<String> checkedRightsList = new ArrayList<String>();
        for (int i = 0; checkedRights != null && i < checkedRights.length; i++) {
            checkedRightsList.add(checkedRights[i]);
        }
        try {
            // RightsBO bo = new RightsBO();
            // RowSet rs = bo.query();

            String sql = "select * from TB_FUNCTION";

            RowSet rs = dbAccess.executeQuery(sql);
            List<FunctionVO> userFunctionlList = new ArrayList<>();
            List<FunctionVO> baseFunctionlList = new ArrayList<>();
            List<FunctionVO> messageFunctionlList = new ArrayList<>();
            while (rs != null && rs.next()) {

                if (rs.getString("ISVALID").equals("1") && rs.getString("REMARK").equals("51")) {
                    FunctionVO functionVO = new FunctionVO();
                    functionVO.setFunctionId(rs.getString("FUNCTIONID"));
                    functionVO.setFunctionName(rs.getString("FUNCTIONNAME"));
                    functionVO.setRemark(rs.getString("REMARK"));
                    userFunctionlList.add(functionVO);
                }

                if (rs.getString("ISVALID").equals("1") && rs.getString("REMARK").equals("55")) {
                    FunctionVO functionVO = new FunctionVO();
                    functionVO.setFunctionId(rs.getString("FUNCTIONID"));
                    functionVO.setFunctionName(rs.getString("FUNCTIONNAME"));
                    functionVO.setRemark(rs.getString("REMARK"));
                    baseFunctionlList.add(functionVO);
                }

                if (rs.getString("ISVALID").equals("1") && rs.getString("REMARK").equals("56")) {
                    FunctionVO functionVO = new FunctionVO();
                    functionVO.setFunctionId(rs.getString("FUNCTIONID"));
                    functionVO.setFunctionName(rs.getString("FUNCTIONNAME"));
                    functionVO.setRemark(rs.getString("REMARK"));
                    messageFunctionlList.add(functionVO);
                }

            }

            html.append("<tr><td height=\"20\" bgcolor=\"d3eaef\" class=\"STYLE6\">"
                    + "<input type=\"checkbox\"   name=\"chkAll\" id=\"chkAll\"  onClick=\"ChkAllClick('chkSon','chkAll')\" />基础数据管理</td></tr><tr><td bgcolor=\"#FFFFFF\" class=\"STYLE19\">");

            for (int i = 0; i < userFunctionlList.size(); i++) {
                html.append("<div height=\"20\" style=\"float:left;padding-right:20px\">"
                        + "<input type=\"checkbox\" name=\"chkSon\"  id=\"" + userFunctionlList.get(i).getFunctionId()
                        + "\"   onclick=\"ChkSonClick('chkSon','chkAll')\"   value=\""
                        + userFunctionlList.get(i).getFunctionId() + "\"  />"
                        + userFunctionlList.get(i).getFunctionName() + "</div>");

            }

            html.append("</td></tr>");

            html.append("<tr><td height=\"20\" bgcolor=\"d3eaef\" class=\"STYLE6\">"
                    + "<input type=\"checkbox\"   name=\"chkAll2\" id=\"chkAll2\"  onClick=\"ChkAllClick('chkSon2','chkAll2')\" />统计查询</td></tr><tr><td bgcolor=\"#FFFFFF\" class=\"STYLE19\">");

            for (int i = 0; i < baseFunctionlList.size(); i++) {
                html.append("<div height=\"20\" style=\"float:left;padding-right:20px\">"
                        + "<input type=\"checkbox\" id=\"" + baseFunctionlList.get(i).getFunctionId()
                        + "\"   onclick=\"ChkSonClick('chkSon2','chkAll2')\"  name=\"chkSon2\" value=\""
                        + baseFunctionlList.get(i).getFunctionId() + "\" "
                        + (checkedRightsList.contains(baseFunctionlList.get(i).getFunctionId()) ? "checked" : "") + "/>"
                        + baseFunctionlList.get(i).getFunctionName() + "</div>");

            }

            html.append("</td></tr>");

            html.append("<tr><td height=\"20\" bgcolor=\"d3eaef\" class=\"STYLE6\">"
                    + "<input type=\"checkbox\"   name=\"chkAll3\" id=\"chkAll3\"  onClick=\"ChkAllClick('chkSon3','chkAll3')\" />评分考核</td></tr><tr><td bgcolor=\"#FFFFFF\" class=\"STYLE19\">");

            for (int i = 0; i < messageFunctionlList.size(); i++) {
                html.append("<div height=\"20\" style=\"float:left;padding-right:20px\">"
                        + "<input type=\"checkbox\" id=\"" + messageFunctionlList.get(i).getFunctionId()
                        + "\"   onclick=\"ChkSonClick('chkSon3','chkAll3')\"  name=\"chkSon3\" value=\""
                        + messageFunctionlList.get(i).getFunctionId() + "\" "
                        + (checkedRightsList.contains(messageFunctionlList.get(i).getFunctionId()) ? "checked" : "")
                        + "/>" + messageFunctionlList.get(i).getFunctionName() + "</div>");

            }

            html.append("</td></tr>");
            html.append("</td></tr>");
            html.append("</table>");
        } catch (Exception e) {
            logger.error("", e);
        }
        return html.toString();
    }
}
