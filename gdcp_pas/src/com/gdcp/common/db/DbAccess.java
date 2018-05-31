package com.gdcp.common.db;
/**
 * @author �����  2009-7-15 
 * <p>Description:
 *   �����ṩ ���ݿ�����ķ���
 *     executeQuery(String sql) : ִ��sql��ѯ�ķ����� ����rowset
 *     executeUpdate(String sql): ִ�и��µ�sql���
 *     executeBatchUpdate(List sqlList) : ִ���������µ�sql���
 * </p>
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.RowSet;
import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

public class DbAccess {
	private static Logger logger = Logger.getLogger(DbAccess.class);

	private String jndiName = "java:comp/env/jdbc/pas";

	/**
	 * @param sql
	 *            String ��ִ�еĲ�ѯ��sql
	 * @see ִ�в�ѯ��sql�� ����һ��rowset,Ȼ��ر����� ����������������Ĳ�ѯ��
	 *      ��ΪResutlSet��RowSet��ת������ʱ��Ƚ϶�
	 */
	public RowSet executeQuery(String sql) throws Exception {
		ResultSet rs = null;
		RowSet crset = null;
		logger.info("����SQL:" + sql);
		Connection conn = null;
		Statement stmt = null;
		if (sql == null || sql.equals("")) {
			logger.error("�������󣬲�ѯ��䲻��Ϊ��");
		}
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			crset = getRowset(rs);
			conn.commit();
		} catch (Exception ex) {
			logger.error("ִ�в�ѯ����:" + sql);
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				rs = null;
			} catch (Exception e) {
				logger.error("���ݿ�رմ���", e);
			}
			closeDb(conn, stmt);
		}
		return crset;

	}

	/**
	 * @see ִ�и���sql���
	 */
	public int executeUpdate(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int ret = -1;
		if (sql == null || sql.equals("")) {
			logger.error("��������sql��䲻��Ϊ��");
			throw new Exception("��������sql��䲻��Ϊ��");
		}
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);
			logger.info("����SQL��" + sql);
		} catch (Exception ex) {
			logger.error("ִ�и��³���:" + sql);
			logger.error(ex.toString());
			throw new Exception("ִ�����ݿ��²���ʧ�ܣ�[" + sql + "]", ex);
		} finally {
			closeDb(conn, stmt);
		}
		return ret;

	}

	/**
	 * @see����ִ�и���sql���
	 */
	public int executeBatchUpdate(List<?> sqlList) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int ret = -1;
		if (sqlList == null || sqlList.isEmpty()) {
			logger.error("ִ��������ʧ�ܣ�");
			throw new Exception("ִ��������ʧ�ܣ�");
		}
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sqlList.size(); i++) {
				stmt.addBatch((String) sqlList.get(i));
			}
			int[] intArr = stmt.executeBatch();
			conn.commit();
			ret = 0;
			for (int i = 0; i < intArr.length; i++) {
				ret += intArr[i];
			}
		} catch (Exception ex) {
			try {
				// if(isCommit)
				conn.rollback();
			} catch (SQLException e) {
				logger.error("����ع�ʧ�ܣ�" + sqlList, e);
			}
			logger.error("����ִ��sql����" + ex);
			throw new Exception("����ִ��sql����", ex);
		} finally {
			closeDb(conn, stmt);
		}
		return ret;
	}

	/**
	 * @see ��ResultSet ת��ΪCachedRowSet,���뻺��
	 * 
	 */
	private RowSet getRowset(ResultSet rs) throws SQLException {
		CachedRowSet crset = null;
		crset = new CachedRowSet();
		crset.populate(rs);
		return crset;
	}

	public Connection getConnection() {
		Connection conn = null;
		conn = DbConnectionManager.getConnection(jndiName);
		return conn;
	}

	/**
	 * @see �ر����ݿ�����
	 */
	public void closeDb(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			if (conn != null) {
				// logger.info("********�ر����ݿ�����********" + conn);
				conn.close();
				// logger.info("********�ر����ݿ�����********�ɹ�");
				conn = null;
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

}
