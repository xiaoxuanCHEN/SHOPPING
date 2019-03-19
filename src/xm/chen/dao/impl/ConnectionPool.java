package xm.chen.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPool {
	// ����
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// ���ӵ�ַ���������ơ���������
	private static final String URL = "jdbc:sqlserver://localhost:1433;databasename=xiaomi;"
			+ "username=sa;password=123456";
	// ʹ�����ݿ����ӳؼ���
	static BasicDataSource dataSource = new BasicDataSource();

	static {
		// �趨���������ӵ�ַ
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setMaxActive(500);// �������������
		dataSource.setMinIdle(10);// ��С����������
		dataSource.setMaxWait(1000);// ���ȴ�������
	}

	/**
	 * �������ݿ�ķ���
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
