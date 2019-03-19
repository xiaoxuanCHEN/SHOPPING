package xm.chen.dao;

import java.util.List;

import xm.chen.modal.MyCart;

public interface myCartDao {
	/**
	 * ����Ʒ��ӽ����ﳵ
	 * 
	 * @param cart
	 *            ���ﳵʵ��
	 * @return ��ӵ�����
	 */
	public int addcart(MyCart cart);

	/**
	 * ���¹��ﳵ
	 * 
	 * @param cart
	 *            ���ﳵʵ��
	 * @return �������
	 */
	public int updatecart(MyCart cart);

	/**
	 * ������Ʒidɾ�����ﳵ�е���Ʒ
	 * 
	 * @param gid
	 *            ��Ʒid
	 * @return ɾ��������
	 */
	public int delcart(int gid);

	/**
	 * �����û�idɾ�����ﳵ
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
	 * ����uid��ѯ���û����ﳵ�е�������Ʒ��Ϣ
	 * 
	 * @param uid
	 *            �û�id
	 * @return ������Ϣ����
	 */
	public List<MyCart> getAllCart(int uid);

	/**
	 * ���ݹ��ﳵid��ȡ���ﳵ��Ϣ
	 * 
	 * @param id
	 *            ���ﳵid
	 * @return
	 */
	public MyCart getCart(int id);

	/**
	 * ����������ѯ���ﳵ�е���Ʒ��Ϣ
	 * 
	 * @param condition
	 *            ����
	 * @return ������Ϣ����
	 */
	public List<MyCart> getAllCartByCondtion(String condition);

	/**
	 * ����ѯ��������Ʒ��Ϣ��ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ��ǰҳ
	 * @param condition
	 *            ����
	 * @param order
	 *            �Ƿ�����trueΪ����
	 * @return ������Ϣ����
	 */
	public List<MyCart> getCartBypage(int pagesize, int page, String condition, boolean order);
}
