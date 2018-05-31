package com.gdcp.common;

/**
 * @author daibo
 */
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class Conditions {
    private static List<String> ConditionsList;
    private static Map<String, String> ConditionsMap;

    @SuppressWarnings("rawtypes")
    /**
     * ��request���ó�Conditions_��ͷ�Ĳ�����ŵ�ConditionsMap��
     * ��ȡ������Conditions_����ֶΣ���ɲ�ѯ���ŵ�ConditionsList��
     * 
     * @param request
     */
    public Conditions(HttpServletRequest request) {
        ConditionsList = new ArrayList<>();
        ConditionsMap = new HashMap<>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement().toString();
            if (name.startsWith("Conditions_")) {
                String value = request.getParameter(name);
                if (!value.equals("")) {
                    ConditionsMap.put(name, value);
                    String key = name.substring(11);
                    String result;
                    if (key.endsWith("Id")) {
                        result = " " + key + " = '" + value + "'";
                    } else {
                        result = " " + key + " like '%" + value + "%' ";
                    }
                    ConditionsList.add(result);
                }
            }
        }
    }

    /**
     * ��ConditionsList�е��������ת��Ϊwhere ��� ���
     */
    public String getConditions() {
        if (ConditionsList.size() != 0) {
            StringBuffer result = new StringBuffer();

            for (int i = 0; i < ConditionsList.size(); i++) {
                String conditions = ConditionsList.get(i);
                result.append(conditions);
                if (i != ConditionsList.size() - 1)
                    result.append(" and ");
            }
            return result.toString();
        }

        return null;
    }

    public String getConditionsValue(String name) {
        if (ConditionsMap.containsKey(name)) {
            return ConditionsMap.get(name);
        } else {
            return "";
        }
    }
}
