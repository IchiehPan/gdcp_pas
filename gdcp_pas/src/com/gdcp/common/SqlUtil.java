package com.gdcp.common;

import javax.sql.RowSet;

import org.apache.log4j.Logger;

import com.gdcp.common.db.DbAccess;

public class SqlUtil {
    private static Logger logger = Logger.getLogger(SqlUtil.class);
    private static SqlUtil sqlUtil = null;

    public static SqlUtil getInstance() {
        if (sqlUtil == null) {
            sqlUtil = new SqlUtil();
        }
        return sqlUtil;
    }

    public String replaceSql(String sql) {
        sql = sql.replaceAll("'", "''");
        return sql;

    }

    /**
     * 该方法将sql组装成满足翻页要求的SQLServer数据库的sql。
     * 
     * @param pageNo
     *            取第几页的数据
     * @param pageSize
     *            每页多少条数据
     * @param keyColumName
     *            取值字段的名字(该字段取值必须唯一，类似主键),用来过滤数据。
     * @param sql
     *            原始sql语句。需要组装的sql
     */
    public String getPageSql(String sql, String keyColumnName, int pageSize, int pageNo) {
        int min = (pageNo - 1) * pageSize;
        int max = pageNo * pageSize;
        sql = setSQLTop(max, sql);
        sql = "select top " + pageSize + " * from (" + sql + ") t1 where " + keyColumnName + " not in ( select top "
                + min + " " + keyColumnName + " from (" + sql + ") t2 )";
        return sql;
    }

    public static String getSQLServerPageSQL(int pageNo, int pageSize, String keyColumnName, String sql) {
        int min = (pageNo - 1) * pageSize;
        int max = pageNo * pageSize;
        sql = setSQLTop(max, sql);
        sql = "select top " + pageSize + " * from (" + sql + ") t1 where " + keyColumnName + " not in ( select top "
                + min + " " + keyColumnName + " from (" + sql + ") t2 )";
        return sql;
    }

    /**
     * 将传入的sql的主查询中加上top，如果有top则不加，原sql返回。 例如传入size:5 ; sql: select * from test
     * 返回sql: select top 5 * from test 例如传入size:5 ; sql: select top 100 * from
     * test 返回sql: select top 100 * from test(有top则原sql返回)
     * 
     * @param size
     *            如果需要加top， 则加 top size
     */
    private static String setSQLTop(int size, String sql) {
        String tempSql = "";
        if (sql.toLowerCase().indexOf("select distinct") != -1) {
            int index = sql.toLowerCase().indexOf("select distinct");
            tempSql = sql.substring(index + 15, sql.length());
            if (tempSql.trim().toLowerCase().startsWith("top")) {
                tempSql = sql;
            } else {
                tempSql = "select distinct top " + size + " " + tempSql;
            }

        } else {

            int index = sql.toLowerCase().indexOf("select");
            tempSql = sql.substring(index + 6, sql.length());
            if (tempSql.trim().toLowerCase().startsWith("top")) {
                tempSql = sql;
            } else {
                tempSql = "select top " + size + " " + tempSql;
            }
        }

        return tempSql;
    }

    public String getCountSql(String sql) {
        return "select count(*) from (" + sql + ") t";
    }

    public int getCount(String sql) {
        int count = 0;
        String countSql = "select count(*) from (" + sql + ") t";
        try {
            DbAccess dbAccess = new DbAccess();
            RowSet rs = dbAccess.executeQuery(countSql);
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            logger.error("获取数量错误", e);
        }

        return count;
    }

    public int getSeqId() {
        int seq = 1;
        String sql = "select * from tb_seq";
        try {
            DbAccess dbAccess = new DbAccess();
            dbAccess.setJndiName("java:comp/env/jdbc/pas");
            RowSet rs = dbAccess.executeQuery(sql);
            if (rs == null || !rs.next()) {
                dbAccess.executeUpdate("insert into tb_seq values(1)");
            } else {
                seq = rs.getInt(1);
                dbAccess.executeUpdate("update tb_seq set seqid = seqid + 1");
            }
        } catch (Exception e) {
            logger.error("getSeqId", e);
        }
        return seq;
    }

}
