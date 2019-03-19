package xm.chen.dao;

import java.util.List;

import xm.chen.modal.Goods;

public interface GoodDao {
	/**
	 * �����Ʒ
	 * 
	 * @param good
	 *            ��Ʒʵ��
	 * @return ��Ӱ�������
	 */
	public int addGood(Goods good);

	/**
	 * ������Ʒ
	 * 
	 * @param good
	 *            ��Ʒʵ��
	 * @return ��Ӱ�������
	 */
	public int updateGood(Goods good);

	/**
	 * ����id�õ���Ʒ��Ϣ
	 * 
	 * @param id
	 *            ��Ʒid
	 * @return ������Ʒʵ��
	 */
	public Goods getGoodById(int id);

	/**
	 * ͨ��������ѯ��Ʒ��Ϣ
	 * 
	 * @param Condition
	 *            ����
	 * @return ������Ʒʵ��
	 */
	public List<Goods> getGoodByCondition(String Condition);

	/**
	 * ����ɾ����Ʒ��Ϣ
	 * 
	 * @param id
	 *            ��Ʒid
	 * 
	 * @return ������Ӱ�������
	 */
	public int deleteGood(int id);

	/**
	 * ��������������Ʒ��ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ҳ��
	 * @param condition
	 *            ����
	 * @param order
	 *            �Ƿ�����
	 * @return ��Ʒ����
	 */
	public List<Goods> getByGoodPage(int pagesize, int page, String condition, boolean order);

	/**
	 * ����������ѯ��Ʒ
	 * 
	 * @param condition
	 *            ����
	 * @param n
	 *            ��Ʒ����
	 * @return ��Ʒ����
	 */
	public List<Goods> getGoods(String condition, int n);

	/**
	 * ����������ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ����
	 */
	public int Count(String condition);
}
