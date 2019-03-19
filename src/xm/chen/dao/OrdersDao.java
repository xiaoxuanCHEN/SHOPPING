package xm.chen.dao;

import java.util.List;

import xm.chen.modal.MyCart;
import xm.chen.modal.Orders;

public interface OrdersDao {

	public int add(Orders o);

	/**
	 * �ӹ��ﳵ���¶���
	 * 
	 * @param carts
	 *            ���ﳵʵ��
	 * @param count
	 *            ����
	 * @param userid
	 *            �û�id
	 * @return ������Ӱ�������
	 */
	public int addOrders(List<MyCart> carts, List<Integer> count, int userid);

	/**
	 * ֱ���µ�
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

	public int delorders(int id);

	/**
	 * ɾ������
	 * 
	 * @param id
	 *            ����id
	 * @return ��Ӱ�������
	 */
	public int deleteOrders(Orders o);

	/**
	 * ����id�õ�����ʵ��
	 * 
	 * @param id
	 *            ����id
	 * @return ���ض���ʵ��
	 */
	public Orders getOrders(int id);

	/**
	 * ����������ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ���ض�������
	 */
	public List<Orders> getOrdersByCondition(String condition);

	/**
	 * ����������ȡ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ��Ӱ�������
	 */

	public int getcount(String condition);

	/**
	 * ����������ѯ���ݲ���ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ��ǰҳ
	 * @param condition
	 *            ����
	 * @param order
	 *            trueΪ����
	 * @return ��������
	 */
	public List<Orders> getOrdersByConditionPage(int pagesize, int page, String condition, boolean order);

	/**
	 * ͳ��ÿ�ն����ܽ��
	 * 
	 * @param star
	 *            ��ʼʱ��
	 * @param end
	 *            ����ʱ��
	 * @return
	 */
	public List<Orders> getSumTotal();
}
