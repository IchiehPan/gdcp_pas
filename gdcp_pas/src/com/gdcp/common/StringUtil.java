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
     * 判断字符串是否是空字符串
     * 
     * @param value
     *            String 需验证的字符串
     * @return boolean 如果是空字符串,返回true;否则,返回false
     */
    public static boolean isBlank(String value) {
        boolean ret = false;
        if (value != null && value.trim().equals("")) {
            ret = true;
        }
        return ret;
    }

    /**
     * 判断字符串是否是null
     * 
     * @param value
     *            String 需验证的字符串
     * @return boolean 如果是null,返回true;否则,返回false
     */
    public static boolean isNull(String value) {
        return value == null ? true : false;
    }

    /**
     * 判断字符串是否是null或者空字符串,或者字符串"null"或者"NULL"
     * 
     * @param value
     *            String 需验证的字符串
     * @return boolean 如果是null或者空字符串,或者字符串"null"或者"NULL",返回true;否则,返回false
     */
    public static boolean isNullOrBlank(String value) {
        return isNull(value) || isBlank(value) || isnullOrNULL(value);
    }

    /**
     * 判断字符串是否是字符串"null"或者"NULL"
     * 
     * @param value
     *            String 需验证的字符串
     * @return boolean 如果是字符串"null"或者"NULL",返回true;否则,返回false
     */
    private static boolean isnullOrNULL(String value) {
        boolean info = false;
        if (null != value && (value.equals("null") || value.equals("NULL"))) {
            info = true;
        }
        return info;
    }

    /**
     * 将null转换成""
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
