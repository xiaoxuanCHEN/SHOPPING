package xm.chen.dao;

import java.math.BigDecimal;
import java.util.List;

import xm.chen.modal.User;

public interface UserDao {
	/**
	 * �û���¼
	 * 
	 * @param phone
	 *            �ֻ���
	 * @param pwd
	 *            ����
	 * @return ��Ӱ�������
	 */
	public User login(String phone, String pwd);

	/**
	 * ����������ѯ�û���Ϣ
	 * 
	 * @param condition
	 *            ����
	 * @return �û���Ϣ
	 */
	public List<User> getUserByCondition(String condition);

	/**
	 * ע���û�
	 * 
	 * @param u
	 *            �û�ʵ��
	 * @return ��Ӱ�������
	 */
	public int register(User u);

	/**
	 * ������û��Ƿ�ע��
	 * 
	 * @param phone
	 *            �ֻ���
	 * @return true/false(trueΪ��ע��)
	 */
	public boolean checkUserPhone(String phone);

	/**
	 * �û������˻�����ֵ
	 * 
	 * @param money
	 *            ��ֵ���
	 * @param trans
	 *            ��������
	 * @param u
	 *            �û�ʵ��
	 * @return ��Ӱ�������
	 */
	public int recharge(BigDecimal money, String trans, User u);

	/**
	 * �û��������ݸ���
	 * 
	 * @param u
	 *            �û�ʵ��
	 * @return ��Ӱ�������
	 */
	public int updateUser(User u);

	/**
	 * �û����Ϸ�ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ��ǰҳ
	 * @param condition
	 *            ����
	 * @param order
	 *            �Ƿ�����
	 * @return
	 */
	public List<User> getuserPages(int pagesize, int page, String condition, boolean order);

	/**
	 * �����û�����
	 * 
	 * @param condition
	 *            ����
	 * @return
	 */
	public int Count(String condition);
}
