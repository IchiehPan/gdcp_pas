package com.gdcp.common.db;
/**
 * @author 刘伯睿  2009-7-15 
 * <p>Description:
 *   本类提供 数据库操作的方法
 *     executeQuery(String sql) : 执行sql查询的方法， 返回rowset
 *     executeUpdate(String sql): 执行更新的sql语句
 *     executeBatchUpdate(List sqlList) : 执行批量更新的sql语句
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
	 *            String 待执行的查询的sql
	 * @see 执行查询的sql， 返回一个rowset,然后关闭连接 用来做数据量不大的查询。
	 *      因为ResutlSet向RowSet的转换消耗时间比较多
	 */
	public RowSet executeQuery(String sql) throws Exception {
		ResultSet rs = null;
		RowSet crset = null;
		logger.info("完整SQL:" + sql);
		Connection conn = null;
		Statement stmt = null;
		if (sql == null || sql.equals("")) {
			logger.error("参数错误，查询语句不能为空");
		}
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			crset = getRowset(rs);
			conn.commit();
		} catch (Exception ex) {
			logger.error("执行查询出错:" + sql);
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				rs = null;
			} catch (Exception e) {
				logger.error("数据库关闭错误", e);
			}
			closeDb(conn, stmt);
		}
		return crset;

	}

	/**
	 * @see 执行更新sql语句
	 */
	public int executeUpdate(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int ret = -1;
		if (sql == null || sql.equals("")) {
			logger.error("参数错误，sql语句不能为空");
			throw new Exception("参数错误，sql语句不能为空");
		}
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);
			logger.info("完整SQL：" + sql);
		} catch (Exception ex) {
			logger.error("执行更新出错:" + sql);
			logger.error(ex.toString());
			throw new Exception("执行数据库新操作失败：[" + sql + "]", ex);
		} finally {
			closeDb(conn, stmt);
		}
		return ret;

	}

	/**
	 * @see批量执行更新sql语句
	 */
	public int executeBatchUpdate(List<?> sqlList) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int ret = -1;
		if (sqlList == null || sqlList.isEmpty()) {
			logger.error("执行批更新失败：");
			throw new Exception("执行批更新失败：");
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
				logger.error("事务回滚失败：" + sqlList, e);
			}
			logger.error("批量执行sql出错" + ex);
			throw new Exception("批量执行sql出错", ex);
		} finally {
			closeDb(conn, stmt);
		}
		return ret;
	}

	/**
	 * @see 将ResultSet 转换为CachedRowSet,放入缓存
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
	 * @see 关闭数据库连接
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
				// logger.info("********关闭数据库连接********" + conn);
				conn.close();
				// logger.info("********关闭数据库连接********成功");
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
