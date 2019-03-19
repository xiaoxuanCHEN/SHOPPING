package xm.chen.service;

import java.math.BigDecimal;
import java.util.List;

import xm.chen.modal.User;

public interface UserService {
	/**
	 * �û���¼
	 * 
	 * @param phone
	 *            �ֻ���
	 * @param pwd
	 *            ����
	 * @return �û�ʵ��
	 */
	public User login(String phone, String pwd);

	/**
	 * �û�ע��
	 * 
	 * @param u
	 *            �û�ʵ��
	 * @return ��Ӱ�������
	 */

	public int register(User u);

	/**
	 * �����ֻ��Ų�ѯ�û��Ƿ����
	 * 
	 * @param phone
	 *            �ֻ���
	 * @return true/false
	 */
	public boolean checkUserPhone(String phone);

	/**
	 * �û���ֵ
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
	 * �����û���Ϣ
	 * 
	 * @param u
	 *            �û�ʵ��
	 * @return ��Ӱ�������
	 */
	public int updateUser(User u);

	/**
	 * ����������ѯ�û�����
	 * 
	 * @param condition
	 *            ����
	 * @return �û���Ϣ����
	 */
	public List<User> getUserByCondition(String condition);

	/**
	 * ��̨�û���Ϣ��ҳ
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
