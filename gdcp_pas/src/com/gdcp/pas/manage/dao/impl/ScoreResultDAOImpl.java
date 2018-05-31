package com.gdcp.pas.manage.dao.impl;

/**
 * @author �ſ���  2015-03-26 
 * @see �ṩ���۹�ϵ�����ѯ��ɾ�������Ӳ���
 */
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.ScoreResultDAO;
import com.gdcp.pas.manage.vo.ScoreResultVO;

public class ScoreResultDAOImpl implements ScoreResultDAO {
    private DbAccess dbAccess = new DbAccess();

    public DbAccess getDbAccess() {
        return dbAccess;
    }

    public void setDbAccess(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * @see �����ݿ�ɾ��һ�����ֹ�ϵ��¼
     * @param scoreResultVO
     *            ScoreResultVO ��TB_SCORERESULT����ɾ��
     */
    public int deleteRec(ScoreResultVO scoreResult) throws Exception {
        String objectId = scoreResult.getObjectId(); // ���ֶ���ID
        String scorerId = scoreResult.getScorerId(); // ��������ID
        // ɾ����Ӧ�����۽�����һ������
        String sql = "delete from TB_SCORERESULT where OBJECTID='" + objectId + "' and SCORERID='" + scorerId + "'";
        return dbAccess.executeUpdate(sql);
    }

    /**
     * @see �����ݿ��ѯ�������ֹ�ϵ��¼ �����ı���TB_SCORERULE��TB_DEPT
     *      ��TB_SCORERESULT���л�ȡ���ֶ���ID����ְ��ID������������ID����ְ��ID�������ֹ���ID
     *      ���ݽ�ְ��ID��TB_USER���л�ȡ��Ӧ���û��Ͳ���ID ���ݲ���ID��TB_DEPT���л�ȡ��Ӧ��������
     *      �������ֹ���ID��TB_SCORERULE���л�ȡ��Ӧ�����ֹ�������
     */
    public List<ScoreResultVO> queryPage(Page page, ScoreResultVO vo) throws Exception {
        String countSql = "select count(*)";

        String querySql = "  select a.* ,b.rulename,c.USERNAME as ousername,d.DEPTNAME as odeptname,c1.USERNAME as susername,d1.DEPTNAME as sdeptname";
        querySql = querySql + "  from TB_SCORERESULT a,TB_SCORERULE b ,TB_USER c,TB_DEPT d  ,TB_USER c1 ,TB_DEPT d1  ";
        querySql = querySql
                + "where a.SCORERULEID=b.SCORERULEID and a.OBJECTID=c.USERID and c.DEPTID=d.DEPTID  and a.SCORERID=c1.USERID and c1.DEPTID=d1.DEPTID";

        countSql = countSql + " from TB_SCORERESULT a,TB_SCORERULE b ,TB_USER c,TB_DEPT d  ,TB_USER c1 ,TB_DEPT d1 ";
        countSql = countSql
                + " where a.SCORERULEID=b.SCORERULEID and a.OBJECTID=c.USERID and c.DEPTID=d.DEPTID  and a.SCORERID=c1.USERID and c1.DEPTID=d1.DEPTID";
        if (vo.getUserType() != null && vo.getUserType().equals("0")) {
            querySql = querySql + " and c.username like '%" + vo.getUserName() + "%'";
            countSql = countSql + " and c.username like '%" + vo.getUserName() + "%'";
        }
        if (vo.getUserType() != null && vo.getUserType().equals("1")) {
            querySql = querySql + " and c1.username like '%" + vo.getUserName() + "%'";
            countSql = countSql + " and c1.username like '%" + vo.getUserName() + "%'";
        }

        RowSet rs = dbAccess.executeQuery(countSql);
        if (rs != null && rs.next()) {
            page.setTotal(rs.getInt(1));
        }

        List<ScoreResultVO> list = new ArrayList<>();
        querySql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "ID", querySql);
        rs = dbAccess.executeQuery(querySql);
        while (rs != null && rs.next()) {
            ScoreResultVO scoreResultVO = new ScoreResultVO();

            String teacherId1 = rs.getString("OBJECTID");
            String teacherId2 = rs.getString("SCORERID");
            String ruleName = rs.getString("RULENAME"); // ��ȡ���ֹ�������
            scoreResultVO.setObjectId(teacherId1);
            scoreResultVO.setOuserName(rs.getString("ousername"));
            scoreResultVO.setOdeptName(rs.getString("odeptname"));
            scoreResultVO.setScorerId(teacherId2);
            scoreResultVO.setSuserName(rs.getString("susername"));
            scoreResultVO.setSdeptName(rs.getString("sdeptname"));
            scoreResultVO.setRuleName(ruleName);
            list.add(scoreResultVO);
        }
        return list;
    }

    /**
     * @see �����û�������û�����ѯ���ֶ������������
     * @param userName
     *            �û����������
     * @param ouserName
     *            ���ֶ���
     * @param suserName
     *            �������� �����ı�ΪTB_SCORERULE��TB_DEPT ��ouserName��Ϊ�գ����ѯ��Ӧ������������Ϣ
     *            ��suserName��Ϊ�գ����ѯ��Ӧ�����ֶ�����Ϣ
     */
    public List<ScoreResultVO> query(ScoreResultVO scoreResult) throws Exception {
        List<ScoreResultVO> list = new ArrayList<>();
        String userName = scoreResult.getUserName(); // �û���
        String ouserName = scoreResult.getOuserName(); // ���ֶ���
        String sql = null;
        if (ouserName.equals(0 + "")) {// ��ѯ���ж�Ӧ����������
            sql = "  select a.* ,b.rulename,c.USERNAME as ousername,d.DEPTNAME as odeptname,c1.USERNAME as susername,d1.DEPTNAME as sdeptname";
            sql = sql + "  from TB_SCORERESULT a,TB_SCORERULE b ,TB_USER c,TB_DEPT d  ,TB_USER c1 ,TB_DEPT d1  ";
            sql = sql
                    + "where a.SCORERULEID=b.SCORERULEID and a.OBJECTID=c.USERID and c.DEPTID=d.DEPTID  and a.SCORERID=c1.USERID and c1.DEPTID=d1.DEPTID and c.username='"
                    + userName + "'";
        }
        if (ouserName.equals(1 + "")) {// ��ѯ���ж�Ӧ�����ֶ���
            sql = "  select a.* ,b.rulename,c.USERNAME as ousername,d.DEPTNAME as odeptname,c1.USERNAME as susername,d1.DEPTNAME as sdeptname";
            sql = sql + "  from TB_SCORERESULT a,TB_SCORERULE b ,TB_USER c,TB_DEPT d  ,TB_USER c1 ,TB_DEPT d1  ";
            sql = sql
                    + "where a.SCORERULEID=b.SCORERULEID and a.OBJECTID=c.USERID and c.DEPTID=d.DEPTID  and a.SCORERID=c1.USERID and c1.DEPTID=d1.DEPTID and c1.username='"
                    + userName + "'";
        }
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            ScoreResultVO scoreResultVO = new ScoreResultVO();
            String teacherId1 = rs.getString("OBJECTID");
            String teacherId2 = rs.getString("SCORERID");
            String ruleName = rs.getString("RULENAME"); // ��ȡ���ֹ�������
            scoreResultVO.setObjectId(teacherId1);
            scoreResultVO.setOuserName(rs.getString("ousername"));
            scoreResultVO.setOdeptName(rs.getString("odeptname"));
            scoreResultVO.setScorerId(teacherId2);
            scoreResultVO.setSuserName(rs.getString("susername"));
            scoreResultVO.setSdeptName(rs.getString("sdeptname"));
            scoreResultVO.setRuleName(ruleName);
            list.add(scoreResultVO);
        }
        return list;
    }

    public List<ScoreResultVO> queryByStatus(int status) throws Exception {
        List<ScoreResultVO> list = new ArrayList<>();
        String sql = null;
        sql = "select * from TB_SCORERESULT where STATUS = " + status;
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            ScoreResultVO scoreResultVO = new ScoreResultVO();
            String teacherId1 = rs.getString("OBJECTID");
            String teacherId2 = rs.getString("SCORERID");
            scoreResultVO.setObjectId(teacherId1);
            scoreResultVO.setScorerId(teacherId2);
            list.add(scoreResultVO);
        }
        return list;
    }

    /**
     * @see ��ѯ�������۹�������
     */
    @Override
    public List<ScoreResultVO> queryRuleName() throws Exception {
        String sqlScoreRule = "select * from TB_SCORERULE" + " where RULETYPE = 1";
        List<ScoreResultVO> list = new ArrayList<>();
        RowSet rsScoreRule = dbAccess.executeQuery(sqlScoreRule);
        while (rsScoreRule != null && rsScoreRule.next()) {
            ScoreResultVO srVO = new ScoreResultVO();
            int scoreRuleId = rsScoreRule.getInt("SCORERULEID");
            String ruleName = rsScoreRule.getString("RULENAME");
            srVO.setScoreruleId(scoreRuleId);
            srVO.setRuleName(ruleName);
            list.add(srVO);
        }
        return list;
    }

    /**
     * @see �������ֶ�����ID��ȡ�ò����µ������û���
     */
    @Override
    public List<ScoreResultVO> queryObjectName(int odeptId) throws Exception {
        String sqlUser = "select * from TB_USER where DEPTID=" + odeptId + "";
        RowSet rsUser = dbAccess.executeQuery(sqlUser);
        List<ScoreResultVO> list = new ArrayList<>();
        while (rsUser != null && rsUser.next()) {
            ScoreResultVO srVO = new ScoreResultVO();
            String ouserName = rsUser.getString("USERNAME"); // ��ȡ�û���
            String objectId = rsUser.getString("TEACHERID"); // ��ȡ��ְ����
            srVO.setOuserName(ouserName);
            srVO.setObjectId(objectId);
            list.add(srVO);
        }
        return list;
    }

    /**
     * @see �����ֽ�����������һ�����ֹ�ϵ��¼
     */
    @Override
    public int insertRec(ScoreResultVO scoreResult) throws Exception {
        String objectId = scoreResult.getObjectId(); // ���ֶ���ID
        int objectTypeId = Integer.parseInt(scoreResult.getObjectTypeId());// ���ֶ�������ID
        String screrId = scoreResult.getScorerId(); // ��������ID
        int scorerTypeId = Integer.parseInt(scoreResult.getScorerTypeId()); // ������������ID
        int scoreRuleId = scoreResult.getScoreruleId();

        String sqlSum = "select * from TB_SCORERESULT";
        RowSet rs = dbAccess.executeQuery(sqlSum);
        int id = 0;
        while (rs != null && rs.next()) {
            id = id + 1;
        }
        String sqlScoreResult = "insert into TB_SCORERESULT (id,OBJECTID,OBJECTTYPE,SCORERID,SCORERTYPE,SCORERULEID,STATUS) values("
                + id + ",'" + objectId + "'," + objectTypeId + ",'" + screrId + "'," + scorerTypeId + "," + scoreRuleId
                + ",0);";

        return dbAccess.executeUpdate(sqlScoreResult);
    }

    /**
     * @see �����������岿��id��ѯ�Ĳ����µ������û���
     */
    @Override
    public List<ScoreResultVO> queryScorerName(int sdeptId) throws Exception {
        String sqlUser = "select * from TB_USER where DEPTID=" + sdeptId + "";
        RowSet rsUser = dbAccess.executeQuery(sqlUser);
        List<ScoreResultVO> list = new ArrayList<>();
        while (rsUser != null && rsUser.next()) {
            ScoreResultVO srVO = new ScoreResultVO();
            String suserName = rsUser.getString("USERNAME"); // ��ȡ�û���
            String scorerId = rsUser.getString("TEACHERID"); // ��ȡ��ְ����
            srVO.setSuserName(suserName);
            srVO.setScorerId(scorerId);
            list.add(srVO);
        }
        return list;
    }

    /**
     * @see ��ѯ���п��˶�������
     */
    @Override
    public List<ScoreResultVO> queryObjectType() throws Exception {
        String sqlExtendCode = "select * from TB_EXTENDCODE	where EXTENDTYPE='objectType'";
        RowSet rsExtendCode = dbAccess.executeQuery(sqlExtendCode);
        List<ScoreResultVO> list = new ArrayList<>();
        String extendCode = null; // ���˶�������ID
        String extendValue = null; // ���˶�������
        while (rsExtendCode != null && rsExtendCode.next()) {
            extendCode = rsExtendCode.getString("EXTENDCODE");
            ScoreResultVO srVO = new ScoreResultVO();
            extendValue = rsExtendCode.getString("EXTENDVALUE");
            srVO.setObjectTypeId(extendCode);
            srVO.setObecjtType(extendValue);
            list.add(srVO);
        }
        return list;
    }

    /**
     * @see ��ѯ����������������
     */
    @Override
    public List<ScoreResultVO> queryScorerType() throws Exception {
        String sqlExtendCode = "select * from TB_EXTENDCODE	where EXTENDTYPE='scorerType'";
        RowSet rsExtendCode = dbAccess.executeQuery(sqlExtendCode);
        List<ScoreResultVO> list = new ArrayList<>();
        String extendCode = null; // ������������ID
        String extendValue = null; // ������������
        while (rsExtendCode != null && rsExtendCode.next()) {
            ScoreResultVO srVO = new ScoreResultVO();
            extendCode = rsExtendCode.getString("EXTENDCODE");
            extendValue = rsExtendCode.getString("EXTENDVALUE");
            srVO.setScorerTypeId(extendCode);
            srVO.setScorerType(extendValue);
            list.add(srVO);
        }
        return list;
    }
}
