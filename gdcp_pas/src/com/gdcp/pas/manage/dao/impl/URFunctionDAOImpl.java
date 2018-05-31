package com.gdcp.pas.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.URFunctionDAO;
import com.gdcp.pas.manage.vo.URFunctionVO;

/**
 * @author �ư��� 2015-03-23
 * @see �ṩ�����ݿ����û���ɫ���ܶ�Ӧ��ϵ��ĸ��ֲ���
 */
public class URFunctionDAOImpl implements URFunctionDAO {
    private DbAccess dbAccess = new DbAccess();

    public DbAccess getDbAccess() {
        return dbAccess;
    }

    public void setDbAccess(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * @param urFunctionVO
     *            �û���ɫ���ܶ�Ӧ��ϵ����
     * @see �����ݿ����һ���û���ɫ���ܶ�Ӧ��ϵ�ļ�¼
     */
    public int insertURFunction(URFunctionVO urFunctionVO) throws Exception {
        String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                + StringUtil.fieldValue(urFunctionVO.getId()) + "," + StringUtil.fieldValue(urFunctionVO.getObjectId())
                + "," + 0 + "," + StringUtil.fieldValue(urFunctionVO.getFunctionId()) + ")";
        return dbAccess.executeUpdate(insertSql);
    }

    /**
     * @param userId
     *            �û�id
     * @see �����û�id��ѯ�����û������еĹ���id
     * @return ���û������еĹ���id�ַ���
     */
    public List<String> queryURFunctionByUserId(String teacherId) throws Exception {
        String querySql = "select FUNCTIONID from TB_USERROLEFUNCTION where OBJECTID ="
                + StringUtil.fieldValue(teacherId);
        RowSet rs = dbAccess.executeQuery(querySql);
        List<String> functionIdList = new ArrayList<>();
        while (rs != null && rs.next()) {
            functionIdList.add(rs.getString("FUNCTIONID"));
        }
        return functionIdList;
    }

    /**
     * @param userId
     *            �û�id
     * @see ����idɾ�����ݿ��е�1~n�����ܶ�Ӧ��ϵ�ļ�¼
     */
    public int delURFunction(String teacherId) throws Exception {
        String updateSql = "delete TB_USERROLEFUNCTION where OBJECTID=" + StringUtil.fieldValue(teacherId);
        return dbAccess.executeUpdate(updateSql);
    }

    public void setUserFunction(String teacherId, List<String> userFunctionList, List<String> baseFunctionList,
            List<String> messageFunctionList) throws Exception {
        List<String> Allsql = new ArrayList<>();

        String[] userStringArr = null;
        if (userFunctionList.size() > 0) {
            String Them = userFunctionList.get(0);
            userStringArr = Them.split(",");
        }

        for (int i = 0; i < userStringArr.length; i++) {
            if (!userStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(teacherId)) + "," + StringUtil.fieldValue("0") + ","
                        + StringUtil.fieldValue(userStringArr[i]) + ")";
                Allsql.add(insertSql);

            }
        }

        String[] baseStringArr = null;
        if (baseFunctionList.size() > 0) {
            String Them = baseFunctionList.get(0);
            baseStringArr = Them.split(",");
        }

        for (int i = 0; i < baseStringArr.length; i++) {

            if (!baseStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(teacherId)) + "," + StringUtil.fieldValue("0") + ","
                        + StringUtil.fieldValue(baseStringArr[i]) + ")";
                Allsql.add(insertSql);

            }
        }

        String[] messageStringArr = null;
        if (messageFunctionList.size() > 0) {
            String Them = messageFunctionList.get(0);
            messageStringArr = Them.split(",");
        }

        for (int i = 0; i < messageStringArr.length; i++) {
            if (!messageStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(teacherId)) + "," + StringUtil.fieldValue("0") + ","
                        + StringUtil.fieldValue(messageStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }

        if (Allsql.size() >= 1) {
            dbAccess.executeBatchUpdate(Allsql);
        }
    }

}
