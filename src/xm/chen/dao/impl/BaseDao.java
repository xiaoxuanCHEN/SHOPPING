package xm.chen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class BaseDao {
	static Connection conn = null;// ���Ӷ���
	static PreparedStatement pst = null;// Ԥ�����������
	static ResultSet rs = null;// �����

	/**
	 * �����ݿ��������ɾ���Ĳ���
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            ��������
	 * @return ��Ӱ�������
	 */
	public static int executeUpdate(String sql, Object[] params) {
		int row = 0;// Ĭ��0���ܵ�Ӱ��
		try {
			// ��������
			conn = ConnectionPool.getConnection();
			pst = conn.prepareStatement(sql);
			// �ж������Ƿ�Ϊ�ջ�������
			if (params != null && params.length > 0) {
				// ���������еĲ�������Ԥ�������������
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			// ִ������õ����
			row = pst.executeUpdate();
			// �ͷ���Դ
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	public static Result executeQuery(String sql, Object[] params) {
		Result result = null;// ���巵�صĻ�������Ĭ��Ϊ��ֵ
		try {
			conn = ConnectionPool.getConnection();
			pst = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			// ִ������õ����
			rs = pst.executeQuery();
			// �ѽ����ת���ɻ���������
			result = ResultSupport.toResult(rs);
			// �ͷ���Դ
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �ͷ���Դ
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
