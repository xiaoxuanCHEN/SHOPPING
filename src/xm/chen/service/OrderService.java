package xm.chen.service;

import java.util.List;

import xm.chen.modal.MyCart;
import xm.chen.modal.Orders;

public interface OrderService {
	/**
	 * ��Ӷ���
	 * 
	 * @param o
	 *            ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int add(Orders o);

	/**
	 * �ӹ��ﳵ�й�����Ʒ����Ӷ���
	 * 
	 * @param carts
	 *            ���ﳵ����
	 * @param count
	 *            ��������
	 * @param userid
	 *            �û�id
	 * @return ��Ӱ�������
	 */
	public int addOrders(List<MyCart> carts, List<Integer> count, int userid);

	/**
	 * ����Ʒ����ֱ�ӹ�����Ʒ�����ɶ���
	 * 
	 * @param gid
	 *            ��Ʒid
	 * @param count
	 *            ����
	 * @param uid
	 *            �û�id
	 * @return ��Ӱ�������
	 */
	public int addOrders1(int gid, int count, int uid);

	/**
	 * ���¶���
	 * 
	 * @param o
	 *            ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int updateOrders(Orders o);

	/**
	 * ɾ������
	 * 
	 * @param o
	 *            ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int deleteOrders(Orders o);

	/**
	 * ���ݶ���id��ȡ������Ϣ
	 * 
	 * @param id
	 *            ����id
	 * @return ��Ӱ�������
	 */
	public Orders getOrders(int id);

	/**
	 * ����������ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ��Ӱ�������
	 */
	public int getcount(String condition);

	/**
	 * ����������ȡ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ��������
	 */
	public List<Orders> getOrdersByCondition(String condition);

	/**
	 * �����������з�ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ��ǰҳ
	 * @param condition
	 *            ����
	 * @param order
	 *            true(����)
	 * @return ��������
	 */
	public List<Orders> getOrdersByConditionPage(int pagesize, int page, String condition, boolean order);

	/**
	 * ����ÿ�ն����ܽ��
	 * 
	 * @param star
	 *            ��ʼʱ��
	 * @param end
	 *            ����ʱ��
	 * @return
	 */
	public List<Orders> getSumTotal();
}
