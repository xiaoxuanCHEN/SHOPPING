package xm.chen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class BaseDao {
	static Connection conn = null;// 连接对象
	static PreparedStatement pst = null;// 预处理命令对象
	static ResultSet rs = null;// 结果集

	/**
	 * 对数据库进行增、删、改操作
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 受影响的行数
	 */
	public static int executeUpdate(String sql, Object[] params) {
		int row = 0;// 默认0行受到影响
		try {
			// 创建连接
			conn = ConnectionPool.getConnection();
			pst = conn.prepareStatement(sql);
			// 判断数组是否为空或无数据
			if (params != null && params.length > 0) {
				// 把数组所有的参数放入预处理命令对象中
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			// 执行命令得到结果
			row = pst.executeUpdate();
			// 释放资源
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	public static Result executeQuery(String sql, Object[] params) {
		Result result = null;// 定义返回的缓存结果，默认为空值
		try {
			conn = ConnectionPool.getConnection();
			pst = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			// 执行命令得到结果
			rs = pst.executeQuery();
			// 把结果集转换成缓存结果接收
			result = ResultSupport.toResult(rs);
			// 释放资源
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 释放资源
	 */
	public static void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
