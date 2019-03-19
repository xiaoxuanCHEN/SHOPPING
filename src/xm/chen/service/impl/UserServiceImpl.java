package xm.chen.service.impl;

import java.math.BigDecimal;
import java.util.List;

import xm.chen.dao.UserDao;
import xm.chen.dao.impl.UserDaoimpl;
import xm.chen.modal.User;
import xm.chen.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao udao;

	@Override
	public User login(String phone, String pwd) {
		udao = new UserDaoimpl();
		return udao.login(phone, pwd);
	}

	@Override
	public int register(User u) {
		udao = new UserDaoimpl();
		return udao.register(u);
	}

	@Override
	public boolean checkUserPhone(String phone) {
		udao = new UserDaoimpl();
		boolean falg = udao.checkUserPhone(phone);
		if (falg)
			return false;
		else
			return true;
	}

	@Override
	public int recharge(BigDecimal money, String trans, User u) {
		udao = new UserDaoimpl();
		List<User> user = udao.getUserByCondition("phone='" + u.getPhone() + "' and transpwd='" + trans + "'");
		if (user.size() > 0) {
			u.setBalance(u.getBalance().add(money));
			return udao.updateUser(u);
		} else {
			// √‹¬Î¥ÌŒÛ
			return 2;
		}
	}

	@Override
	public int updateUser(User u) {
		udao = new UserDaoimpl();
		return udao.updateUser(u);
	}

	@Override
	public List<User> getUserByCondition(String condition) {
		udao = new UserDaoimpl();
		List<User> list = udao.getUserByCondition(condition);
		return list;
	}

	@Override
	public List<User> getuserPages(int pagesize, int page, String condition, boolean order) {
		udao = new UserDaoimpl();
		List<User> list = udao.getuserPages(pagesize, page, condition, order);
		return list;
	}

	@Override
	public int Count(String condition) {
		udao = new UserDaoimpl();
		int count = udao.Count(condition);
		return count;
	}

}
