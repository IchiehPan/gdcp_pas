package com.gdcp.common.extend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.sql.RowSet;

import org.apache.log4j.Logger;

import com.gdcp.common.db.DbAccess;

public class ExtendCodeUtil {
    private static Logger logger = Logger.getLogger(ExtendCodeUtil.class);

    private static ExtendCodeUtil instance = null;
    private Map<String, ExtendType> extendTypes = new Hashtable<>();

    // ���췽����
    private ExtendCodeUtil() {
        try {
            init();
        } catch (Exception e) {
            logger.error("���췽��ʧ��", e);
        }
    }

    // ����ģʽ��
    public static ExtendCodeUtil getInstance() {
        if (instance == null) {
            instance = new ExtendCodeUtil();
        }
        return instance;
    }

    public void init() {
        extendTypes.clear();

        DbAccess dbAccess = new DbAccess();
        String sql = "select * from TB_EXTENDCODE t ORDER BY t.EXTENDTYPE,t.EXTENDSORT";
        try {
            RowSet rs = dbAccess.executeQuery(sql);

            while (rs.next()) {
                String typeName = rs.getString("EXTENDTYPE");
                String numKey = rs.getString("EXTENDCODE");
                String value = rs.getString("EXTENDVALUE");
                int sort = rs.getInt("EXTENDSORT");

                ExtendCode extendCode = new ExtendCode();
                extendCode.setType(typeName);
                extendCode.setNumKey(numKey);
                extendCode.setValue(value);
                extendCode.setSort(sort);

                ExtendType extendType = extendTypes.get(typeName);
                if (extendType == null) {
                    extendType = new ExtendType();
                    extendType.setTypeName(typeName);
                }

                extendType.addExtendCode(extendCode);
                extendTypes.put(typeName, extendType);
            }
        } catch (SQLException e) {
            logger.error("sql������", e);
        } catch (Exception e) {
            logger.error("��ʼ���쳣", e);
        }

        instance = this;
    }

    /**
     * ����type��numKey��ȡָ��value��
     * 
     * @param type
     * @param numKey
     * @return ָ��ֵ
     */
    public String getValue(String type, String numKey) {
        try {
            return extendTypes.get(type).getExtendCode(numKey).getValue();
        } catch (NullPointerException e) {
            logger.error("����type��numKey��ȡָ��value����", e);
            return null;
        }
    }

    /**
     * ��ȡtype��Ӧ������value
     * 
     * @param type
     * @return ��type������value
     */
    public List<String> getValuesByType(String type) {
        ArrayList<String> values = new ArrayList<>();
        try {
            ArrayList<ExtendCode> extendCodes = extendTypes.get(type).getExtendCodes();
            for (int i = 0; i < extendCodes.size(); i++) {
                values.add(extendCodes.get(i).getValue());
            }
            return values;
        } catch (Exception e) {
            logger.error("��ȡtype��Ӧ������value����", e);
            return null;
        }
    }

    /**
     * ��ȡһ��ָ����type��d��ExtendCode����
     * 
     * @param ָ���ġ�type��
     * @return ExtendCode���飬����ʱͨ��getValue()��ȡĿ���ַ���
     */
    public List<ExtendCode> getExtendCodeListByType(String type) {
        try {
            ArrayList<ExtendCode> extendCodes = extendTypes.get(type).getExtendCodes();
            return extendCodes;
        } catch (Exception e) {
            logger.error("��ȡһ��ָ����type��d��ExtendCode�������", e);
            return null;
        }
    }

    // ����װ��extendcode��
    public synchronized void reload() throws Exception {
        init();
    }
}
