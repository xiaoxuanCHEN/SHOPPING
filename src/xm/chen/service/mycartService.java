package xm.chen.service;

import java.util.List;

import xm.chen.modal.MyCart;

public interface mycartService {
	/**
	 * ��ӹ��ﳵ
	 * 
	 * @param cart
	 *            ���ﳵʵ��
	 * @return ��Ӱ�������
	 */
	public int addcart(MyCart cart);

	/**
	 * ���¹��ﳵ
	 * 
	 * @param cart
	 *            ���ﳵʵ��
	 * @return ��Ӱ�������
	 */
	public int updatecart(MyCart cart);

	/**
	 * ������Ʒidɾ�����ﳵ�е�����
	 * 
	 * @param gid
	 *            ��Ʒid
	 * @return ��Ӱ�������
	 */
	public int delcart(int gid);

	/**
	 * �����û�idɾ�������е�������Ϣ
	 * 
	 * @param uid
	 *            �û�id
	 * @return ��Ӱ�������
	 */

	public int delcart1(int uid);

	/**
	 * ����idɾ�����ﳵ�ж�Ӧ��Ʒ��Ϣ
	 * 
	 * @param id
	 *            ���ﳵid
	 * @return ��Ӱ�������
	 */
	public int delcart2(int id);

	/**
	 * �����û�id��ȡ��Ӧ�Ĺ��ﳵ������Ʒ��Ϣ
	 * 
	 * @param uid
	 *            �û�id
	 * @return ���ﳵ����Ʒ����
	 */
	public List<MyCart> getAllCart(int uid);

	/**
	 * ����id��ѯ���ﳵ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public MyCart getCart(int id);

	/**
	 * ����������ȡ���ﳵ����Ʒ����Ϣ
	 * 
	 * @param condition
	 *            ����
	 * @return ���ﳵ����Ʒ��Ϣ����
	 */
	public List<MyCart> getAllCartByCondtion(String condition);

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
	 * @return ���ﳵ����Ʒ��Ϣ����
	 */
	public List<MyCart> getCartBypage(int pagesize, int page, String condition, boolean order);
}
