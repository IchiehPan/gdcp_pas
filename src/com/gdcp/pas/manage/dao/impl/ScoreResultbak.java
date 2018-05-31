package com.gdcp.pas.manage.dao.impl;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import org.apache.log4j.Logger;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.vo.ScoreResultVO;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @author cyx
 *
 */
public class ScoreResultbak {
    private static Logger logger = Logger.getLogger(ScoreResultbak.class);
    private DbAccess dbAccess = new DbAccess();

    public String insert() throws Exception {
        List<ScoreResultVO> srList = new ArrayList<>();

        List<String> allSrList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        String[] depTname = null; // �������岿����
        String[] doTeacheriId = null;// ���������ʦ���
        String[] doTeacherName = null;// ���������ʦ����
        String[] beDeptName = null;// �����˲�������
        String[] beDeptTeacherId = null;// �����˽�ʦ���
        String[] beDeptTeacherName = null;// �����˽�ʦ����
        String[] objectType = null;// �����˽������
        String[] ruleId = null;// ���˹���
        String[] scorerType = null;// �������� ����

        try {
            String path = System.getProperty("user.dir") + "/��Ч�������۶�Ӧ��ϵ����.xls";

            InputStream is = new FileInputStream(path);
            Workbook rwb = Workbook.getWorkbook(is);

            Sheet sheet = rwb.getSheet(0);

            int rowNum = sheet.getRows();

            depTname = new String[rowNum];
            doTeacheriId = new String[rowNum];
            doTeacherName = new String[rowNum];
            beDeptName = new String[rowNum];
            beDeptTeacherId = new String[rowNum];
            beDeptTeacherName = new String[rowNum];
            objectType = new String[rowNum];
            ruleId = new String[rowNum];
            scorerType = new String[rowNum];

            for (int i = 0; i < rowNum; i++) {
                depTname[i] = sheet.getCell(6, i).getContents().trim();
                doTeacheriId[i] = sheet.getCell(7, i).getContents().trim();
                doTeacherName[i] = sheet.getCell(8, i).getContents().trim();
                beDeptName[i] = sheet.getCell(1, i).getContents().trim();
                beDeptTeacherId[i] = sheet.getCell(2, i).getContents().trim();
                beDeptTeacherName[i] = sheet.getCell(3, i).getContents().trim();
                objectType[i] = sheet.getCell(4, i).getContents().trim();
                ruleId[i] = sheet.getCell(5, i).getContents().trim();
                scorerType[i] = sheet.getCell(9, i).getContents().trim();
            }

        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (BiffException e) {
            logger.error("BiffException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }

        for (int i = 1; i < depTname.length - 1; i++) {

            boolean check1 = false;
            boolean check2 = false;
            boolean check3 = false;
            boolean check4 = false;
            boolean check5 = false;

            boolean checkDept = true;
            boolean checkDept2 = true;

            /*
             * int objUserID=0; int scoreUserID=0;
             */

            String querySql = "select * from TB_DEPT where DEPTNAME =" + StringUtil.fieldValue(depTname[i]);
            RowSet rs;

            rs = dbAccess.executeQuery(querySql);

            int deptId = -1;
            if (rs != null && rs.next()) {
                deptId = rs.getInt("DEPTID");
            } else {

                if (depTname[i].equals("")) {

                } else {
                    buffer.append("�������壺��" + i + "�и��������ڲ���   " + depTname[i] + "  \r\n");
                }
                checkDept = false;
            }

            if (checkDept) {
                String querySql2 = "select * from TB_USER where DEPTID ="
                        + StringUtil.fieldValue(String.valueOf(deptId));
                RowSet rs2 = dbAccess.executeQuery(querySql2);

                while (rs2 != null && rs2.next()) {

                    if (rs2.getString("TEACHERID").equals(doTeacheriId[i])) {
                        // scoreUserID=rs2.getInt("USERID");
                        check1 = true;
                    }

                    if (rs2.getString("USERNAME").equals(doTeacherName[i])) {
                        check2 = true;
                    }

                }

                if (!check1) {
                    buffer.append("�������壺��" + i + "�в��  " + depTname[i] + " ��û�иý̹�Id��" + doTeacheriId[i] + "\r\n");
                }

                if (!check2) {
                    buffer.append("�������壺��" + i + "�в�ý̹���ţ�" + doTeacheriId[i] + "�²�����" + doTeacherName[i] + "\r\n");
                }

            }

            String querySql3 = "select * from TB_DEPT where DEPTNAME =" + StringUtil.fieldValue(beDeptName[i]);

            RowSet rs3 = dbAccess.executeQuery(querySql3);
            int bedeptId = -1;
            if (rs3 != null && rs3.next()) {
                bedeptId = rs3.getInt("DEPTID");
            } else {

                if (beDeptName[i].equals("")) {

                } else {
                    buffer.append("�����۶��󣺵�" + i + "�и��������ڲ���    " + beDeptName[i] + "\r\n");
                }

                checkDept2 = false;
            }

            if (checkDept2) {
                String querySql4 = "select * from TB_USER where DEPTID ="
                        + StringUtil.fieldValue(String.valueOf(bedeptId));

                RowSet rs4 = dbAccess.executeQuery(querySql4);

                while (rs4 != null && rs4.next()) {

                    if (rs4.getString("TEACHERID").equals(beDeptTeacherId[i])) {
                        // objUserID=rs4.getInt("USERID");
                        check3 = true;

                    }

                    if (rs4.getString("USERNAME").equals(beDeptTeacherName[i])) {
                        check4 = true;
                    }

                }

                if (!check3) {
                    buffer.append(
                            "�����˶��󣺵�" + i + "�в��  " + beDeptName[i] + "  ��û�иý̹�Id��" + beDeptTeacherId[i] + "\r\n");
                }

                if (!check4) {
                    buffer.append(
                            "�����˶��󣺵�" + i + "�в�ý̹���ţ�" + beDeptTeacherId[i] + "�²�����" + beDeptTeacherName[i] + "\r\n");
                }

            }

            String querySql5 = "select * from TB_SCORERULE";
            RowSet rs5 = dbAccess.executeQuery(querySql5);

            while (rs5 != null && rs5.next()) {
                if (rs5.getString("SCORERULEID").equals(ruleId[i])) {
                    check5 = true;
                }

            }

            if (!check5) {

                if (ruleId[i].equals("")) {

                } else {
                    buffer.append("��" + i + "���ڲ��淶��scoreRule:" + ruleId[i] + "\r\n");
                }

            }

            if (check5 && check4 && check3 && check2 && check1) {
                ScoreResultVO sr = new ScoreResultVO();
                sr.setId(SqlUtil.getInstance().getSeqId());
                sr.setObjectId(beDeptTeacherId[i]);
                /*
                 * sr.setObjectType(Integer.parseInt(objectType[i]));
                 * sr.setScorerId(doTeacheriId[i]);
                 * sr.setScorerType(Integer.parseInt(scorerType[i]));
                 */
                sr.setScoreruleId(Integer.parseInt(ruleId[i]));
                sr.setStatus(0);
                srList.add(sr);
            }

        }

        for (int i = 0; i < srList.size(); i++) {

            String insertSql = "insert into TB_SCORERESULT(id,OBJECTID,OBJECTTYPE,SCORERID,SCORERTYPE,SCORERULEID,STATUS) values("
                    + StringUtil.fieldValue(String.valueOf(srList.get(i).getId())) + ","
                    + StringUtil.fieldValue(String.valueOf(srList.get(i).getObjectId())) + ","
                    /*
                     * + StringUtil.fieldValue(String.valueOf(srList.get(i)
                     * .getObjectType())) + ","
                     */
                    + StringUtil.fieldValue(String.valueOf(srList.get(i).getScorerId())) + ","
                    + StringUtil.fieldValue(String.valueOf(srList.get(i).getScorerType())) + ","
                    + StringUtil.fieldValue(String.valueOf(srList.get(i).getScoreruleId())) + ","
                    + StringUtil.fieldValue(String.valueOf(srList.get(i).getStatus())) + ")";
            allSrList.add(insertSql);

        }

        if (allSrList.size() > 0) {

            int result = dbAccess.executeBatchUpdate(allSrList);
            logger.info("������������ " + result + " ��");
        }

        try {
            FileOutputStream f1 = new FileOutputStream("E:\\Test\\4.txt");
            DataOutputStream d1 = new DataOutputStream(f1);
            d1.writeUTF(buffer.toString());
            d1.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return buffer.toString();
    }

}
