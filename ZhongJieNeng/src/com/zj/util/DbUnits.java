package com.zj.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author xueliang
 * DbHelper用来实现数据库的链接和释放，用户不直接使用
 * 建立链接
 * 释放链接
 */
public final class DbUnits {

	/**
	 * 连接URL
	 */
	private static String url ;
	/**
	 * 用户名
	 */
	private static String userName ;
	/**
	 * 用户密码
	 */
	private static String password;
	
	/**
	 * 所需驱动
	 * */
	private static String driver;

	/**
	 *静态块中执行构造
	 **/
	static {
		DbConfig config=new DbConfig();
		url=config.getUrl();
		userName=config.getUserName();
		password=config.getPassword();
		driver=config.getDriver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 建立链接
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}

	/**
	 * 释放链接资源
	 * @param conn
	 */
	private static void freeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭链接声明
	 * @param statement
	 */
	private static void freeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放数据集
	 * @param rs
	 */
	private static void freeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *断开链接释放资源 
	 * @param conn
	 * @param statement
	 * @param rs
	 */
	public static void free(Connection conn, Statement statement, ResultSet rs) {
		if (rs != null) {
			freeResultSet(rs);
		}
		if (statement != null) {
			freeStatement(statement);
		}
		if (conn != null) {
			freeConnection(conn);
		}
	}

}
