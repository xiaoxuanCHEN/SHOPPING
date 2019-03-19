package xm.chen.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPool {
	// 驱动
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// 连接地址、连接名称、连接密码
	private static final String URL = "jdbc:sqlserver://localhost:1433;databasename=xiaomi;"
			+ "username=sa;password=123456";
	// 使用数据库连接池技术
	static BasicDataSource dataSource = new BasicDataSource();

	static {
		// 设定驱动和连接地址
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setMaxActive(500);// 设置最大活动连接数
		dataSource.setMinIdle(10);// 最小空闲连接数
		dataSource.setMaxWait(1000);// 最大等待连接数
	}

	/**
	 * 连接数据库的方法
	 * 
	 * @return
	 */
	public synchronized static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
