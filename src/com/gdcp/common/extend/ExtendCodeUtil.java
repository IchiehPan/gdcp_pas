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

    // 构造方法：
    private ExtendCodeUtil() {
        try {
            init();
        } catch (Exception e) {
            logger.error("构造方法失败", e);
        }
    }

    // 单例模式：
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
            logger.error("sql语句错误", e);
        } catch (Exception e) {
            logger.error("初始化异常", e);
        }

        instance = this;
    }

    /**
     * 根据type、numKey获取指定value：
     * 
     * @param type
     * @param numKey
     * @return 指定值
     */
    public String getValue(String type, String numKey) {
        try {
            return extendTypes.get(type).getExtendCode(numKey).getValue();
        } catch (NullPointerException e) {
            logger.error("根据type、numKey获取指定value错误", e);
            return null;
        }
    }

    /**
     * 获取type对应的所有value
     * 
     * @param type
     * @return 改type的所有value
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
            logger.error("获取type对应的所有value错误", e);
            return null;
        }
    }

    /**
     * 获取一个指定“type”d的ExtendCode数组
     * 
     * @param 指定的“type”
     * @return ExtendCode数组，遍历时通过getValue()获取目标字符串
     */
    public List<ExtendCode> getExtendCodeListByType(String type) {
        try {
            ArrayList<ExtendCode> extendCodes = extendTypes.get(type).getExtendCodes();
            return extendCodes;
        } catch (Exception e) {
            logger.error("获取一个指定“type”d的ExtendCode数组错误", e);
            return null;
        }
    }

    // 重新装载extendcode表
    public synchronized void reload() throws Exception {
        init();
    }
}
