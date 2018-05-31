package com.gdcp.pas.manage.dao.impl;

/**
 * @author �ư��� 2015-03-19
 * @see �ṩ�����ݿ����û���ĸ��ֲ���
 * @upDate ��ΰ�� 2015-03-30 08:25 getUserById()-->getUserByTeacherId() and ��ӡ�getUserByUserId()��
 * @upDate ��ΰ�� 2015-04-02 16:25 ���getObjectIdsByObjectType()��getScorerIdsByScorerType()
 * @upDate  ���Ӹ����û����빦��
 */
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.UserDAO;
import com.gdcp.pas.manage.vo.UserVO;

public class UserDAOImpl implements UserDAO {
    private DbAccess dbAccess = new DbAccess();

    /**
     * �����û�����
     */
    public int updatePassword(UserVO userVO, String uOldPassWord) throws Exception {
        String querySql = "select * from TB_USER where PASSWORD =" + StringUtil.fieldValue(uOldPassWord);
        boolean check = false;
        RowSet rs = dbAccess.executeQuery(querySql);

        while (rs != null && rs.next()) {
            if (rs.getString("TEACHERID").equals(userVO.getTeacherId())) {
                check = true;
            }
        }

        if (check) {
            String updateSql = "update TB_USER set PASSWORD=" + StringUtil.fieldValue(userVO.getPassword())
                    + " where TEACHERID=" + StringUtil.fieldValue(userVO.getTeacherId());
            return dbAccess.executeUpdate(updateSql);
        } else {
            return -1;
        }

    }

    /**
     * @param userId
     *            �û�id
     * @return һ��UserVO����
     * @see ����id��ѯһ���û��ļ�¼
     */
    public UserVO getUserByUserId(int userId) throws Exception {
        String querySql = "select * from TB_USER where USERID =" + userId;
        UserVO userVO = new UserVO();
        RowSet rs = dbAccess.executeQuery(querySql);
        if (rs != null && rs.next()) {
            userVO = getUserVO(rs);
        }
        return userVO;
    }

    /**
     * @param teacherId
     *            �̹�id
     * @return һ��UserVO����
     * @see ����id��ѯһ���û��ļ�¼
     */
    public UserVO getUserByTeacherId(String teacherId) throws Exception {
        String querySql = "select * from TB_USER where TEACHERID =" + StringUtil.fieldValue(teacherId);
        UserVO userVO = new UserVO();
        RowSet rs = dbAccess.executeQuery(querySql);
        if (rs != null && rs.next()) {
            userVO = getUserVO(rs);
        }
        return userVO;
    }

    /**
     * @author ��ΰ�� ��ȡָ���������������н̹���
     * @param objectType
     * @return �̹��ŵ�List
     */
    @Override
    public List<String> getObjectIdsByObjectType(int objectType) throws Exception {
        String sql = "select * from TB_USER where EVALPOSITION =" + objectType;

        RowSet rs = dbAccess.executeQuery(sql);
        if (rs == null) {
            return null;
        }

        ArrayList<String> objectIds = new ArrayList<>();
        while (rs.next()) {
            objectIds.add(rs.getString("TEACHERID"));
        }
        return objectIds;
    }

    /**
     * @author ��ΰ�� ��ȡָ���������������н̹���
     * @param scorerType
     * @return �̹��ŵ�List
     */
    @Override
    public List<String> getScorerIdsByScorerType(int scorerType) throws Exception {
        String sql = "select TEACHERID from TB_USER where USER CHARACTER =" + scorerType;

        RowSet rs = dbAccess.executeQuery(sql);
        if (rs == null) {
            return null;
        }

        ArrayList<String> scorerIds = new ArrayList<>();
        while (rs.next()) {
            scorerIds.add(rs.getString("TEACHERID"));
        }

        return scorerIds;
    }

    /**
     * @param userVO
     *            �û�����
     * @see �����ݿ����һ���û��ļ�¼
     */
    public int insertUser(UserVO userVO) throws Exception {
        String insertSql = "insert into TB_USER(USERID, TEACHERID, USERNAME, PASSWORD, USERCHARACTER, ROLEID, DEPTID, PRODEPTID1, PRODEPTID2, EVALPOSITION, SEX, BIRTHDAY, TECHNICALTITLE, JOB, DEGREE, PRESENTPOSITION, POSITIONKIND, USERLEVEL,ISPROFESSIONAL, REMARK) values("
                // + SqlUtil.getInstance().getSeqId()
                + StringUtil.fieldValue(userVO.getTeacherId()) + "," + StringUtil.fieldValue(userVO.getTeacherId())
                + "," + StringUtil.fieldValue(userVO.getUserName()) + "," + StringUtil.fieldValue(userVO.getPassword())
                + "," + (userVO.getUserCharacter()) + "," + (userVO.getRoleId()) + "," + (userVO.getDeptId()) + ","
                + (userVO.getProdeptId1()) + "," + (userVO.getProdeptId2()) + "," + (userVO.getEvalPosition()) + ","
                + StringUtil.fieldValue(userVO.getSex()) + "," + StringUtil.fieldValue(userVO.getBirthday()) + ","
                + StringUtil.fieldValue(userVO.getTechnicaltitle()) + "," + StringUtil.fieldValue(userVO.getJob()) + ","
                + StringUtil.fieldValue(userVO.getDegree()) + "," + StringUtil.fieldValue(userVO.getPresentPosition())
                + "," + StringUtil.fieldValue(userVO.getPositionKind()) + ","
                + StringUtil.fieldValue(userVO.getUserLevel()) + ","
                + StringUtil.fieldValue(String.valueOf(userVO.getIsProfessional())) + ","
                + StringUtil.fieldValue(userVO.getRemark()) + ")";
        return dbAccess.executeUpdate(insertSql);
    }

    /**
     * @param userVO
     *            �û�����
     * @see �޸����ݿ�һ���û��ļ�¼
     */
    public int updateUser(UserVO userVO) throws Exception {
        String updateSql = "update TB_USER set USERNAME=" + StringUtil.fieldValue(userVO.getUserName()) + ",PASSWORD="
                + StringUtil.fieldValue(userVO.getPassword()) + ",USERCHARACTER=" + userVO.getUserCharacter()
                + ",ROLEID=" + userVO.getRoleId() + ",DEPTID=" + userVO.getDeptId() + ",PRODEPTID1="
                + userVO.getProdeptId1() + ",PRODEPTID2=" + userVO.getProdeptId2() + ",EVALPOSITION="
                + userVO.getEvalPosition() + ",SEX=" + StringUtil.fieldValue(userVO.getSex()) + ",BIRTHDAY="
                + StringUtil.fieldValue(userVO.getBirthday()) + ",TECHNICALTITLE="
                + StringUtil.fieldValue(userVO.getTechnicaltitle()) + ",JOB=" + StringUtil.fieldValue(userVO.getJob())
                + ",DEGREE=" + StringUtil.fieldValue(userVO.getDegree()) + ",PRESENTPOSITION="
                + StringUtil.fieldValue(userVO.getPresentPosition()) + ",POSITIONKIND="
                + StringUtil.fieldValue(userVO.getPositionKind()) + ",USERLEVEL="
                + StringUtil.fieldValue(userVO.getUserLevel()) + ",ISPROFESSIONAL="
                + StringUtil.fieldValue(String.valueOf(userVO.getIsProfessional())) + ",REMARK="
                + StringUtil.fieldValue(userVO.getRemark()) + "  where TEACHERID="
                + StringUtil.fieldValue(userVO.getTeacherId());

        return dbAccess.executeUpdate(updateSql);
    }

    /**
     * @param userId
     *            �û�id
     * @see ɾ�����ݿ�һ���û��ļ�¼
     */
    public int deleteUser(String teacherId) throws Exception {
        String updateSql = "delete TB_USER where TEACHERID=" + StringUtil.fieldValue(teacherId);
        return dbAccess.executeUpdate(updateSql);
    }

    /**
     * @see ��ѯ��ȫ�����û���¼
     */
    public List<UserVO> queryUsers() throws Exception {
        String querySql = "select * from TB_USER";
        RowSet rs = dbAccess.executeQuery(querySql);
        List<UserVO> userList = new ArrayList<>();
        while (rs != null && rs.next()) {
            UserVO userVO = getUserVO(rs);
            userList.add(userVO);
        }
        return userList;
    }

    public List<UserVO> queryPage(Page page, UserVO quserVO) throws Exception {

        List<UserVO> userList = new ArrayList<>();
        String querySql = "select * from TB_USER where 1=1";
        String countSql = "select count(*) from TB_USER where 1=1";
        if (quserVO != null && !StringUtil.isNullOrBlank(quserVO.getUserName())) {
            querySql = querySql + " and USERNAME like'%" + quserVO.getUserName() + "%'";
            countSql = countSql + " and USERNAME like'%" + quserVO.getUserName() + "%'";
        }
        RowSet rs = dbAccess.executeQuery(countSql);
        if (rs != null && rs.next()) {
            page.setTotal(rs.getInt(1));
        }

        querySql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "userId", querySql);
        rs = dbAccess.executeQuery(querySql);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO.setUserId(rs.getInt("USERID"));
            userVO.setTeacherId(rs.getString("TEACHERID"));
            userVO.setUserName(rs.getString("USERNAME"));
            userVO.setPassword(rs.getString("PASSWORD"));
            userVO.setUserCharacter(rs.getInt("USERCHARACTER"));
            userVO.setRoleId(rs.getInt("ROLEID"));
            userVO.setDeptId(rs.getInt("DEPTID"));

            String sql = "select * from TB_DEPT where DEPTID = " + rs.getInt("DEPTID");
            RowSet Rs = dbAccess.executeQuery(sql);
            if (Rs != null && Rs.next()) {
                userVO.setDeptName(Rs.getString("DEPTNAME"));
            }

            userVO.setIsProfessional(rs.getInt("ISPROFESSIONAL"));

            userVO.setProdeptId1(rs.getInt("PRODEPTID1"));
            userVO.setProdeptId2(rs.getInt("PRODEPTID2"));
            userVO.setEvalPosition(rs.getInt("EVALPOSITION"));
            userVO.setSex(rs.getString("SEX"));
            userVO.setBirthday(rs.getString("BIRTHDAY"));
            userVO.setTechnicaltitle(rs.getString("TECHNICALTITLE"));
            userVO.setJob(rs.getString("JOB"));
            userVO.setDegree(rs.getString("DEGREE"));
            userVO.setPresentPosition(rs.getString("PRESENTPOSITION"));
            userVO.setPositionKind(rs.getString("POSITIONKIND"));
            userVO.setUserLevel(rs.getString("USERLEVEL"));
            userVO.setRemark(rs.getString("REMARK"));
            userList.add(userVO);
        }
        return userList;

        /*
         * String querySql = "select * from TB_USER"; RowSet rs =
         * dbAccess.executeQuery(querySql); List<UserVO> userList = new
         * ArrayList<UserVO>(); while (rs != null && rs.next()) { userVO = new
         * UserVO(); userVO.setUserId(rs.getInt("USERID"));
         * userVO.setTeacherId(rs.getString("TEACHERID"));
         * userVO.setUserName(rs.getString("USERNAME"));
         * userVO.setPassword(rs.getString("PASSWORD"));
         * userVO.setUserCharacter(rs.getInt("USERCHARACTER"));
         * userVO.setRoleId(rs.getInt("ROLEID"));
         * userVO.setDeptId(rs.getInt("DEPTID"));
         * userVO.setProdeptId1(rs.getInt("PRODEPTID1"));
         * userVO.setProdeptId2(rs.getInt("PRODEPTID2"));
         * userVO.setEvalPosition(rs.getInt("EVALPOSITION"));
         * userVO.setSex(rs.getString("SEX"));
         * userVO.setBirthday(rs.getString("BIRTHDAY"));
         * userVO.setTechnicaltitle(rs.getString("TECHNICALTITLE"));
         * userVO.setJob(rs.getString("JOB"));
         * userVO.setDegree(rs.getString("DEGREE"));
         * userVO.setPresentPosition(rs.getString("PRESENTPOSITION"));
         * userVO.setPositionKind(rs.getString("POSITIONKIND"));
         * userVO.setUserLevel(rs.getString("USERLEVEL"));
         * userVO.setRemark(rs.getString("REMARK")); userList.add(userVO); }
         * return userList;
         */
    }

    /**
     * @param userName
     *            �û�����
     * @see ��ѯָ���û�������Ϣ
     */
    public List<UserVO> queryLikeName(String userName) throws Exception {
        String querySql = "select * from TB_USER where USERNAME like '%" + userName + "%'";
        RowSet rs = dbAccess.executeQuery(querySql);
        List<UserVO> userList = new ArrayList<>();
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO.setUserId(rs.getInt("USERID"));
            userVO.setTeacherId(rs.getString("TEACHERID"));
            userVO.setUserName(rs.getString("USERNAME"));
            userVO.setPassword(rs.getString("PASSWORD"));
            userVO.setUserCharacter(rs.getInt("USERCHARACTER"));
            userVO.setRoleId(rs.getInt("ROLEID"));
            userVO.setDeptId(rs.getInt("DEPTID"));

            String sql = "select * from TB_DEPT where DEPTID = " + rs.getInt("DEPTID");
            RowSet Rs = dbAccess.executeQuery(sql);
            if (Rs != null && Rs.next()) {
                userVO.setDeptName(Rs.getString("DEPTNAME"));
            }

            userVO.setProdeptId1(rs.getInt("PRODEPTID1"));
            userVO.setProdeptId2(rs.getInt("PRODEPTID2"));
            userVO.setIsProfessional(rs.getInt("ISPROFESSIONAL"));
            userVO.setEvalPosition(rs.getInt("EVALPOSITION"));
            userVO.setSex(rs.getString("SEX"));
            userVO.setBirthday(rs.getString("BIRTHDAY"));
            userVO.setTechnicaltitle(rs.getString("TECHNICALTITLE"));
            userVO.setJob(rs.getString("JOB"));
            userVO.setDegree(rs.getString("DEGREE"));
            userVO.setPresentPosition(rs.getString("PRESENTPOSITION"));
            userVO.setPositionKind(rs.getString("POSITIONKIND"));
            userVO.setUserLevel(rs.getString("USERLEVEL"));
            userVO.setRemark(rs.getString("REMARK"));
            userList.add(userVO);
        }
        return userList;
    }

    /**
     * @author �ſ���
     * @see ��ȡѧУ�쵼
     */
    @Override
    public List<UserVO> getLeaderList() throws Exception {
        String sql = "select * from TB_USER where USERCHARACTER='100211'";
        List<UserVO> list = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            list.add(userVO);
        }
        return list;
    }

    /**
     * @author �ſ���
     * @see ��ȡ���������쵼��ָ����������id��
     */
    @Override
    public List<UserVO> getDeptLeader(String deptId) throws Exception {
        List<String> deptList = new ArrayList<>();
        deptList.add(deptId);
        return getDeptLeader(deptList);
    }

    /**
     * @author �ſ���
     * @see ��ȡ���ڶ��������쵼
     */
    @Override
    public List<UserVO> getDeptLeaderByUser(String userId) throws Exception {
        UserVO tempUser = getUserByTeacherId(userId);
        List<String> deptList = new ArrayList<>();
        deptList.add(tempUser.getDeptId() + "");

        return getDeptLeader(deptList);
    }

    /**
     * @author ���˽�
     * @see ��ȡ���ڲ��ŵ�����Ա��
     */
    @Override
    public List<UserVO> getUsersByDept(String deptId) throws Exception {
        String sql = "select * from TB_USER where USERCHARACTER is not null and deptId='" + deptId + "'";
        List<UserVO> list = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            list.add(userVO);
        }
        return list;
    }

    @Override
    public List<UserVO> getDeptLeader() throws Exception {
        return getDeptLeader(new ArrayList<>());
    }

    /**
     * @author �ſ���
     * @see ��ȡ���ж��������쵼
     */

    public List<UserVO> getDeptLeader(List<String> deptIdList) throws Exception {
        String sql = "select * from TB_USER where  (USERCHARACTER='100212' or USERCHARACTER='100213' or USERCHARACTER='100214' or USERCHARACTER='100215')";
        if (deptIdList != null && deptIdList.size() == 1) {
            sql = sql + "and deptId ='" + deptIdList.get(0) + "'";

        } else if (deptIdList != null && deptIdList.size() > 1) {
            sql = sql + "and deptId in (";
            for (int i = 0; i < deptIdList.size(); i++) {
                if (i == deptIdList.size() - 1) {
                    sql = sql + "'" + deptIdList.get(i) + "'";
                } else {
                    sql = sql + "'" + deptIdList.get(i) + "'" + ",";
                }
            }

            sql = sql + ")";
        }
        List<UserVO> list = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            list.add(userVO);
        }
        return list;
    }

    /**
     * @author �ſ���
     * @see ��ó��������ڲ����������в��ŵ��쵼
     */
    @Override
    public List<UserVO> getOtherDeptLeader(String userId) throws Exception {
        String sqlUser = "select distinct deptid from TB_USER where DEPTID <> (select DEPTID from TB_USER where USERID='"
                + userId + "')";
        List<String> list = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sqlUser);
        while (rs != null && rs.next()) {
            list.add(rs.getString("DEPTID"));

        }

        return getDeptLeader(list);
    }

    /**
     * @author �ſ���
     * @update ��ΰ��0423�������ڲ���ҲҪ����������Ա���ۣ����и������ݿ�ṹ�������޸�
     * @see ������ڲ��ŵ�����ְ��
     */
    @Override
    public List<UserVO> getWorkmateList(String userId) throws Exception {
        String sqlUser = "select * from TB_USER where DEPTID = (select DEPTID from TB_USER where USERID='" + userId
                + "')" + " and userId not like ('1000%')";// �����ǳ�ΰ��ӵ�

        List<UserVO> list = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sqlUser);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            list.add(userVO);
        }

        return list;
    }

    /**
     * @author �ſ���
     * @see ��ý�ְ������
     */
    @Override
    public List<UserVO> getDelegateList(String userId) throws Exception {

        List<UserVO> list = new ArrayList<>();
        String sql = "select * from TB_USER where ISPROFESSIONAL=1";
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            list.add(userVO);
        }

        return list;
    }

    /**
     * @author �ſ���
     * @see ��ñ������ڲ��ŵ����а�����
     */
    @Override
    public List<UserVO> getHeadMasterList(String userId) throws Exception {
        String sqlUser = "select * from TB_HEADMASTER where deptid=(select DEPTID from TB_USER where userId='" + userId
                + "')";
        List<UserVO> list = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sqlUser);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO.setUserId(rs.getInt("userId"));
            userVO.setTeacherId(rs.getString("userId"));
            list.add(userVO);
        }

        return list;
    }

    /**
     * @author �ſ���
     * @see ��ñ������ڵ�С��Ŀ��˶���
     */
    @Override
    public List<UserVO> getPositionUserList(String userId) throws Exception {

        List<UserVO> list = new ArrayList<>();
        String sqlUser = "select *  from TB_USER where EVALPOSITION = (select EVALPOSITION from TB_USER where USERID='"
                + userId + "')";
        RowSet rs = dbAccess.executeQuery(sqlUser);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            list.add(userVO);
        }

        return list;
    }

    /**
     * @author �ſ���
     * @see ���ָ�����ŵ���ְ�쵼
     */
    @Override
    public UserVO getDeptLeaderS(String deptId) throws Exception {
        String sql = "select * from TB_USER where DEPTID='" + deptId
                + "' and ( USERCHARACTER='100212' or USERCHARACTER='100214')";
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            UserVO userVO = new UserVO();
            userVO = getUserVO(rs);
            return userVO;
        }
        return null;
    }

    /**
     * @param rs
     * @return userVO
     * @throws Exception
     * @see ���ݲ���id��ȡ�û���Ϣ
     */
    private UserVO getUserVO(RowSet rs) throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserId(rs.getInt("USERID"));
        userVO.setTeacherId(rs.getString("TEACHERID"));
        userVO.setUserName(rs.getString("USERNAME"));
        userVO.setPassword(rs.getString("PASSWORD"));
        userVO.setUserCharacter(rs.getInt("USERCHARACTER"));
        userVO.setRoleId(rs.getInt("ROLEID"));
        userVO.setDeptId(rs.getInt("DEPTID"));

        String sql = "select * from TB_DEPT where DEPTID = " + rs.getInt("DEPTID");
        RowSet Rs = dbAccess.executeQuery(sql);
        if (Rs != null && Rs.next()) {
            userVO.setDeptName(Rs.getString("DEPTNAME"));
        }

        userVO.setIsProfessional(rs.getInt("ISPROFESSIONAL"));
        userVO.setProdeptId1(rs.getInt("PRODEPTID1"));
        userVO.setProdeptId2(rs.getInt("PRODEPTID2"));
        userVO.setEvalPosition(rs.getInt("EVALPOSITION"));
        userVO.setSex(rs.getString("SEX"));
        userVO.setBirthday(rs.getString("BIRTHDAY"));
        userVO.setTechnicaltitle(rs.getString("TECHNICALTITLE"));
        userVO.setJob(rs.getString("JOB"));
        userVO.setDegree(rs.getString("DEGREE"));
        userVO.setPresentPosition(rs.getString("PRESENTPOSITION"));
        userVO.setPositionKind(rs.getString("POSITIONKIND"));
        userVO.setUserLevel(rs.getString("USERLEVEL"));
        userVO.setRemark(rs.getString("REMARK"));

        return userVO;

    }

}
