package com.gdcp.common.db;
/**
 * @author �����  2009-7-15 
 * <p>Description:
 *   �����ṩ����ͨ��jndi��Ӧ�÷��������������ӳ��л�����ݿ�����
 *  ��չ��� getConnection(String jndiName)  �����ṩjndi������
 * </p>
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.*;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class DbConnectionManager {
    private static Logger logger = Logger.getLogger(DbConnectionManager.class);
    private static DataSource ds = null;

    protected static Connection getConnection(String jndiName) {
        ds = null;
        Connection conn = null;
        if (ds == null) {
            try {
                Context ctx = new InitialContext();
                ds = (DataSource) ctx.lookup(jndiName);
            } catch (Exception nameEx) {
                logger.error("----JNDI DataSource '" + jndiName + "' is unaviable");
                nameEx.printStackTrace();
            }
        }
        try {
            conn = ds.getConnection();
            if (conn == null) {
                logger.error("���ݿ����Ӳ�����---------------" + jndiName);
            }
        } catch (SQLException sqlEx) {
            logger.error("----Get a connection is failed");
            sqlEx.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://123.207.236.35:1433;DatabaseName=pas";
        String username = "sa";
        String password = "5r2Y5a6c5p2w";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(url, username, password);
            con.close();
            logger.info("���ݿ����ӳɹ�!");
        } catch (Exception se) {
            logger.error("���ݿ�����ʧ��", se);
        }
    }

}
