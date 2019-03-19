package xm.chen.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.UserDao;
import xm.chen.modal.User;

public class UserDaoimpl implements UserDao {

	@Override
	public User login(String phone, String pwd) {
		String sql = "select * from Users where phone=? and pwd=?";
		Object[] params = { phone, pwd };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] maps = res.getRows();
		User u = new User();
		if (maps.length > 0) {
			for (Map map : maps) {
				u.setId((Integer) (map.get("id")));
				u.setPhone(map.get("phone").toString());
				u.setPwd(map.get("pwd").toString());
				u.setRealname(map.get("realname").toString());
				u.setBalance((BigDecimal) map.get("balance"));
				u.setAddress(map.get("address").toString());
				u.setSex(map.get("sex").toString());
				u.setTranspwd(map.get("transpwd").toString());
				u.setRole((Integer) map.get("role"));
			}
		}
		return u;
	}

	@Override
	public int register(User u) {
		String sql = "insert into Users values(?,?,?,?,?,?,?,?)";
		Object[] params = { u.getRealname(), u.getPhone(), u.getPwd(), u.getTranspwd(), u.getBalance(), u.getAddress(),
				u.getSex(), u.getRole() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public boolean checkUserPhone(String phone) {
		String sql = "select * from Users where phone=?";
		Object[] params = { phone };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] map = res.getRows();
		if (map.length > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int recharge(BigDecimal money, String trans, User u) {
		String sql = "update Users set balance=? where id=? and transpwd=?";
		Object[] params = { u.getBalance().add(money), u.getId(), trans };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int updateUser(User u) {
		String sql = "update Users set realname=?,phone=?,pwd=?,transpwd=?,balance=?,address=?,sex=?,role=? where id=?";
		Object[] params = { u.getRealname(), u.getPhone(), u.getPwd(), u.getTranspwd(), u.getBalance(), u.getAddress(),
				u.getSex(), u.getRole(), u.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public List<User> getUserByCondition(String condition) {
		String sql = "select * from Users where " + condition;
		Result res = BaseDao.executeQuery(sql, null);
		List<User> list = new ArrayList<User>();
		Map[] maps = res.getRows();
		if (maps.length > 0) {
			for (Map map : maps) {
				User u = new User();
				u.setId((Integer) (map.get("id")));
				u.setPhone(map.get("phone").toString());
				u.setPwd(map.get("pwd").toString());
				u.setRealname(map.get("realname").toString());
				u.setBalance((BigDecimal) map.get("balance"));
				u.setAddress(map.get("address").toString());
				u.setSex(map.get("sex").toString());
				u.setTranspwd(map.get("transpwd").toString());
				u.setRole((Integer) map.get("role"));
				list.add(u);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		UserDao udao = new UserDaoimpl();
		// User u = new User(1, "张三", "15115028888", "123456", "112233", new
		// BigDecimal(100), "湖南岳阳", "男", "用户");
		// int row = udao.recharge(new BigDecimal(100), "112233", u);
		// User u = udao.login("15115028888", "123456");
		// System.out.println(u.getId() + " " + u.getBalance());
		// boolean falg = udao.checkUserPhone("15115028888");
		// System.out.println(falg);
		List<User> list = udao.getuserPages(2, 1, "1=1", true);
		for (User user : list) {
			System.out.println(user.getRealname());
		}
		System.out.println(udao.Count(""));
	}

	@Override
	public List<User> getuserPages(int pagesize, int page, String condition, boolean order) {
		int x = (page - 1) * pagesize;
		String sql = "";
		// 根据条件的有无决定不同的sql语句
		if (condition != null && condition.length() > 0) {
			if (!order) {
				sql = "select top " + pagesize + " * from  users  where " + condition + " and id not in(select top " + x
						+ " id from users where " + condition + "  order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from users where " + condition + " and id not in(select top " + x
						+ " id from users where " + condition + "  )";
			}
		} else {
			if (!order) {
				sql = "select top " + pagesize + " * from users where id not in(select top " + x
						+ " id from users order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from users  where id not in(select top " + x + " id from users )";
			}
		}
		List<User> list = new ArrayList<User>();
		Result res = BaseDao.executeQuery(sql, null);
		Map[] maps = res.getRows();
		if (maps.length > 0) {
			for (Map map : maps) {
				User u = new User();
				u.setId((Integer) (map.get("id")));
				u.setPhone(map.get("phone").toString());
				u.setPwd(map.get("pwd").toString());
				u.setRealname(map.get("realname").toString());
				u.setBalance((BigDecimal) map.get("balance"));
				u.setAddress(map.get("address").toString());
				u.setSex(map.get("sex").toString());
				u.setTranspwd(map.get("transpwd").toString());
				u.setRole((Integer) map.get("role"));
				list.add(u);
			}
		}
		return list;
	}

	@Override
	public int Count(String condition) {
		int i = 0;// 默认值为0
		String sql = "select count(*) row from users";
		if (!condition.isEmpty())
			sql += " where " + condition;
		Result rs = BaseDao.executeQuery(sql, null);
		Map[] maps = rs.getRows();
		if (maps != null && maps.length > 0)
			i = (Integer) maps[0].get("row");
		return i;
	}
}
