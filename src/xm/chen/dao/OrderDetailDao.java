package xm.chen.dao;

import java.util.List;

import xm.chen.modal.OrderDetail;

public interface OrderDetailDao {
	/**
	 * ��Ӷ�������
	 * 
	 * @param od
	 *            ��������ʵ��
	 * @return ��Ӱ�������
	 */
	public int addOrderDetail(OrderDetail od);

	/**
	 * ���¶�������
	 * 
	 * @param od
	 *            ��������ʵ��
	 * @return ��Ӱ�������
	 */
	public int updateOrderDetail(OrderDetail od);

	/**
	 * ���ݶ�������idɾ����������
	 * 
	 * @param id
	 *            ��������id
	 * @return ��Ӱ�������
	 */
	public int delOrderDetail(int id);

	/**
	 * ���ݶ�������id��ѯ������������
	 * 
	 * @param id
	 *            ��������id
	 * @return ������������ʵ��
	 */
	public OrderDetail findOrderDetail(int id);

	/**
	 * ������Ӳ�ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return �������鼯��
	 */
	public List<OrderDetail> findODByCondition(String condition);
}
