package com.gdcp.common;

public class StringUtil {

    public static String fieldValue(String fieldValue) {
        if (fieldValue != null && fieldValue.trim().equals("")) {
            return null;
        }
        if (StringUtil.isNullOrBlank(fieldValue)) {
            return "null";
        } else {
            return ("'" + fieldValue.trim().replaceAll("'", "''") + "'");
        }

    }

    /**
     * �ж��ַ����Ƿ��ǿ��ַ���
     * 
     * @param value
     *            String ����֤���ַ���
     * @return boolean ����ǿ��ַ���,����true;����,����false
     */
    public static boolean isBlank(String value) {
        boolean ret = false;
        if (value != null && value.trim().equals("")) {
            ret = true;
        }
        return ret;
    }

    /**
     * �ж��ַ����Ƿ���null
     * 
     * @param value
     *            String ����֤���ַ���
     * @return boolean �����null,����true;����,����false
     */
    public static boolean isNull(String value) {
        return value == null ? true : false;
    }

    /**
     * �ж��ַ����Ƿ���null���߿��ַ���,�����ַ���"null"����"NULL"
     * 
     * @param value
     *            String ����֤���ַ���
     * @return boolean �����null���߿��ַ���,�����ַ���"null"����"NULL",����true;����,����false
     */
    public static boolean isNullOrBlank(String value) {
        return isNull(value) || isBlank(value) || isnullOrNULL(value);
    }

    /**
     * �ж��ַ����Ƿ����ַ���"null"����"NULL"
     * 
     * @param value
     *            String ����֤���ַ���
     * @return boolean ������ַ���"null"����"NULL",����true;����,����false
     */
    private static boolean isnullOrNULL(String value) {
        boolean info = false;
        if (null != value && (value.equals("null") || value.equals("NULL"))) {
            info = true;
        }
        return info;
    }

    /**
     * ��nullת����""
     * 
     * @param value
     * @return
     */
    public static String dealNull(String value) {
        if (isNull(value)) {
            value = "";
        }
        return value;
    }

}
