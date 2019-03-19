package xm.chen.service;

import java.math.BigDecimal;
import java.util.List;

import xm.chen.modal.User;

public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @return 用户实体
	 */
	public User login(String phone, String pwd);

	/**
	 * 用户注册
	 * 
	 * @param u
	 *            用户实体
	 * @return 受影响的行数
	 */

	public int register(User u);

	/**
	 * 根据手机号查询用户是否存在
	 * 
	 * @param phone
	 *            手机号
	 * @return true/false
	 */
	public boolean checkUserPhone(String phone);

	/**
	 * 用户充值
	 * 
	 * @param money
	 *            充值金额
	 * @param trans
	 *            交易密码
	 * @param u
	 *            用户实体
	 * @return 受影响的行数
	 */
	public int recharge(BigDecimal money, String trans, User u);

	/**
	 * 跟新用户信息
	 * 
	 * @param u
	 *            用户实体
	 * @return 受影响的行数
	 */
	public int updateUser(User u);

	/**
	 * 根据条件查询用户集合
	 * 
	 * @param condition
	 *            条件
	 * @return 用户信息集合
	 */
	public List<User> getUserByCondition(String condition);

	/**
	 * 后台用户信息分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            当前页
	 * @param condition
	 *            条件
	 * @param order
	 *            是否排序
	 * @return
	 */
	public List<User> getuserPages(int pagesize, int page, String condition, boolean order);

	/**
	 * 计算用户数量
	 * 
	 * @param condition
	 *            条件
	 * @return
	 */
	public int Count(String condition);
}
