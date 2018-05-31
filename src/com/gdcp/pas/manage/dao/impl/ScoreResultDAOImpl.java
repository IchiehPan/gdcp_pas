package com.gdcp.pas.manage.dao.impl;

/**
 * @author 张俊杰  2015-03-26 
 * @see 提供评价关系管理查询，删除，增加操作
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
     * @see 在数据库删除一条评分关系记录
     * @param scoreResultVO
     *            ScoreResultVO 在TB_SCORERESULT表中删除
     */
    public int deleteRec(ScoreResultVO scoreResult) throws Exception {
        String objectId = scoreResult.getObjectId(); // 评分对象ID
        String scorerId = scoreResult.getScorerId(); // 评价主体ID
        // 删除对应的评价结果表的一条数据
        String sql = "delete from TB_SCORERESULT where OBJECTID='" + objectId + "' and SCORERID='" + scorerId + "'";
        return dbAccess.executeUpdate(sql);
    }

    /**
     * @see 从数据库查询所有评分关系记录 关联的表有TB_SCORERULE，TB_DEPT
     *      在TB_SCORERESULT表中获取评分对象ID（教职工ID）和评价主体ID（教职工ID）和评分规则ID
     *      根据教职工ID在TB_USER表中获取相应的用户和部门ID 根据部门ID在TB_DEPT表中获取相应部门名称
     *      根据评分规则ID在TB_SCORERULE表中获取相应的评分规则名称
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
            String ruleName = rs.getString("RULENAME"); // 获取评分规则名称
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
     * @see 根据用户输入的用户名查询评分对象或评价主体
     * @param userName
     *            用户输入的名字
     * @param ouserName
     *            评分对象
     * @param suserName
     *            评价主体 关联的表为TB_SCORERULE，TB_DEPT 若ouserName不为空，则查询相应的评价主体信息
     *            若suserName不为空，则查询相应的评分对象信息
     */
    public List<ScoreResultVO> query(ScoreResultVO scoreResult) throws Exception {
        List<ScoreResultVO> list = new ArrayList<>();
        String userName = scoreResult.getUserName(); // 用户名
        String ouserName = scoreResult.getOuserName(); // 评分对象
        String sql = null;
        if (ouserName.equals(0 + "")) {// 查询所有对应的评价主体
            sql = "  select a.* ,b.rulename,c.USERNAME as ousername,d.DEPTNAME as odeptname,c1.USERNAME as susername,d1.DEPTNAME as sdeptname";
            sql = sql + "  from TB_SCORERESULT a,TB_SCORERULE b ,TB_USER c,TB_DEPT d  ,TB_USER c1 ,TB_DEPT d1  ";
            sql = sql
                    + "where a.SCORERULEID=b.SCORERULEID and a.OBJECTID=c.USERID and c.DEPTID=d.DEPTID  and a.SCORERID=c1.USERID and c1.DEPTID=d1.DEPTID and c.username='"
                    + userName + "'";
        }
        if (ouserName.equals(1 + "")) {// 查询所有对应的评分对象
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
            String ruleName = rs.getString("RULENAME"); // 获取评分规则名称
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
     * @see 查询所有评价规则名称
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
     * @see 根据评分对象部门ID获取该部门下的所有用户名
     */
    @Override
    public List<ScoreResultVO> queryObjectName(int odeptId) throws Exception {
        String sqlUser = "select * from TB_USER where DEPTID=" + odeptId + "";
        RowSet rsUser = dbAccess.executeQuery(sqlUser);
        List<ScoreResultVO> list = new ArrayList<>();
        while (rsUser != null && rsUser.next()) {
            ScoreResultVO srVO = new ScoreResultVO();
            String ouserName = rsUser.getString("USERNAME"); // 获取用户名
            String objectId = rsUser.getString("TEACHERID"); // 获取教职工号
            srVO.setOuserName(ouserName);
            srVO.setObjectId(objectId);
            list.add(srVO);
        }
        return list;
    }

    /**
     * @see 在评分结果主表中添加一条评分关系记录
     */
    @Override
    public int insertRec(ScoreResultVO scoreResult) throws Exception {
        String objectId = scoreResult.getObjectId(); // 评分对象ID
        int objectTypeId = Integer.parseInt(scoreResult.getObjectTypeId());// 评分对象类型ID
        String screrId = scoreResult.getScorerId(); // 评价主体ID
        int scorerTypeId = Integer.parseInt(scoreResult.getScorerTypeId()); // 评价主体类型ID
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
     * @see 根据评价主体部门id查询改部门下的所有用户名
     */
    @Override
    public List<ScoreResultVO> queryScorerName(int sdeptId) throws Exception {
        String sqlUser = "select * from TB_USER where DEPTID=" + sdeptId + "";
        RowSet rsUser = dbAccess.executeQuery(sqlUser);
        List<ScoreResultVO> list = new ArrayList<>();
        while (rsUser != null && rsUser.next()) {
            ScoreResultVO srVO = new ScoreResultVO();
            String suserName = rsUser.getString("USERNAME"); // 获取用户名
            String scorerId = rsUser.getString("TEACHERID"); // 获取教职工号
            srVO.setSuserName(suserName);
            srVO.setScorerId(scorerId);
            list.add(srVO);
        }
        return list;
    }

    /**
     * @see 查询所有考核对象类型
     */
    @Override
    public List<ScoreResultVO> queryObjectType() throws Exception {
        String sqlExtendCode = "select * from TB_EXTENDCODE	where EXTENDTYPE='objectType'";
        RowSet rsExtendCode = dbAccess.executeQuery(sqlExtendCode);
        List<ScoreResultVO> list = new ArrayList<>();
        String extendCode = null; // 考核对象类型ID
        String extendValue = null; // 考核对象类型
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
     * @see 查询所有评价主体类型
     */
    @Override
    public List<ScoreResultVO> queryScorerType() throws Exception {
        String sqlExtendCode = "select * from TB_EXTENDCODE	where EXTENDTYPE='scorerType'";
        RowSet rsExtendCode = dbAccess.executeQuery(sqlExtendCode);
        List<ScoreResultVO> list = new ArrayList<>();
        String extendCode = null; // 评价主体类型ID
        String extendValue = null; // 评价主体类型
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
