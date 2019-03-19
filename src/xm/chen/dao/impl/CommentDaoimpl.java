package xm.chen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.CommentDao;
import xm.chen.modal.Comment;
import xm.chen.modal.HotComGoods;

public class CommentDaoimpl implements CommentDao {

	@Override
	public int addComment(Comment c) {
		String sql = "insert into comment values(?,?,?,?,?)";
		Object[] params = { c.getUid(), c.getOrderid(), c.getCommentTime(), c.getComments(), c.getCommentStatus() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int deleteComment(int id) {
		String sql = "delete comment where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int updataComment(Comment c) {
		String sql = "update comment set uid=?,orderid=?,commenttime=?,comments=?,commentstatus=? where id=?";
		Object[] params = { c.getUid(), c.getOrderid(), c.getCommentTime(), c.getComments(), c.getCommentStatus(),
				c.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public List<Comment> getCommentByCondition(String Condition) {
		String sql = "select * from comment where" + Condition;
		Result res = BaseDao.executeQuery(sql, null);
		List<Comment> list = new ArrayList<Comment>();
		Map[] map = res.getRows();
		for (Map map2 : map) {
			Comment c = new Comment();
			c.setId((Integer) map2.get("id"));
			c.setUid((Integer) map2.get("uid"));
			c.setOrderid((Integer) map2.get("orderid"));
			c.setCommentTime(map2.get("commentTime").toString());
			c.setComments(map2.get("comments").toString());
			c.setCommentStatus((Integer) map2.get("commentStatus"));
			c.setOrderdetail(new OrderDetailDaoimpl().findOrderDetail((Integer) map2.get("orderid")));
			list.add(c);
		}
		return list;
	}

	@Override
	public List<Comment> getCommentPages(int pagesize, int page, String condition, boolean order) {
		int x = (page - 1) * pagesize;
		String sql = "";
		// 根据条件的有无决定不同的sql语句
		if (condition != null && condition.length() > 0) {
			if (!order) {
				sql = "select top " + pagesize + " * from  comment  where " + condition + " and id not in(select top "
						+ x + " id from comment where " + condition + "  order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from comment where  " + condition + " and id not in(select top "
						+ x + " id from comment where " + condition + "  )";
			}
		} else {
			if (!order) {
				sql = "select top " + pagesize + " * from comment where id not in(select top " + x
						+ " id from comment order by id desc)" + " order by id desc";
			} else {
				sql = "select top " + pagesize + " * from comment  where id not in(select top " + x
						+ " id from comment )";
			}
		}
		List<Comment> list = new ArrayList<Comment>();
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		for (Map map2 : map) {
			Comment c = new Comment();
			c.setId((Integer) map2.get("id"));
			c.setUid((Integer) map2.get("uid"));
			c.setOrderid((Integer) map2.get("orderid"));
			c.setCommentTime(map2.get("commentTime").toString());
			c.setComments(map2.get("comments").toString());
			c.setCommentStatus((Integer) map2.get("commentStatus"));
			// c.setOrderdetail(new
			// OrderDetailDaoimpl().findOrderDetail((Integer)map2.get("orderid")));
			list.add(c);
		}
		return list;
	}

	@Override
	public int Count(String condition) {
		int i = 0;// 默认值为0
		String sql = "select count(*) row from comment";
		if (!condition.isEmpty())
			sql += " " + condition;
		Result rs = BaseDao.executeQuery(sql, null);
		Map[] maps = rs.getRows();
		if (maps != null && maps.length > 0)
			i = (Integer) maps[0].get("row");
		return i;
	}

	@Override
	public List<HotComGoods> gethotcg() {
		String sql = "select top 4 g.id,c.comments,commenttime,c.uid,count(g.id) from goods g,orderdetail od,comment c"
				+ " where g.id=od.goodid and od.orderid=c.orderid" + " group by g.id,c.comments ,commenttime,c.uid"
				+ " order by count(g.id),commenttime desc";
		Result res = BaseDao.executeQuery(sql, null);
		List<HotComGoods> list = new ArrayList<HotComGoods>();
		Map[] maps = res.getRows();
		for (Map map : maps) {
			HotComGoods h = new HotComGoods();
			h.setComments(map.get("comments").toString());
			h.setGoodid(map.get("id").toString());
			h.setUid((Integer) map.get("uid"));
			list.add(h);
		}
		list2.clear();
		distinct(list);
		return list2;
	}

	static List<HotComGoods> list2 = new ArrayList<HotComGoods>();

	public static void distinct(List<HotComGoods> list) {
		if (list.size() > 0) {
			HotComGoods temp = list.get(0);// 获取第一条
			list.remove(0);// 移除第一条
			List<HotComGoods> list1 = new ArrayList<HotComGoods>();
			list1.addAll(list);// 将新数据添加到新集合中
			for (HotComGoods hotComGoods : list1) {// 循环与获取的第一条进行比较
				if (temp.getGoodid().equals(hotComGoods.getGoodid()))
					list.remove(hotComGoods);// 存在相同则移除数据
			}
			list2.add(temp);
			distinct(list);// 递归
		}
	}

	public static void main(String[] args) {
		CommentDao c = new CommentDaoimpl();
		// int row = c.Count("where uid=1");
		List<Comment> list = c.getCommentByCondition(" 1=1");
		for (Comment comment : list) {
			System.out.println(comment.getOrderdetail().getGoodid());
		}
	}

}
