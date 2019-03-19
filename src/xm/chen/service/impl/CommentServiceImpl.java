package xm.chen.service.impl;


import java.util.List;

import xm.chen.dao.CommentDao;
import xm.chen.dao.impl.CommentDaoimpl;
import xm.chen.modal.Comment;
import xm.chen.modal.HotComGoods;
import xm.chen.service.CommentService;
import xm.chen.service.OrderDetailService;

public class CommentServiceImpl implements CommentService {
	CommentDao comdao = null;
	OrderDetailService oddao = null;

	public CommentServiceImpl() {
		comdao = new CommentDaoimpl();
		oddao = new OrderDetailServiceImpl();
	}

	@Override
	public int addComment(Comment c) {
		int row = comdao.addComment(c);
		return row;
	}

	@Override
	public int deleteComment(int id) {
		int row = comdao.deleteComment(id);
		return row;
	}

	@Override
	public int updataComment(Comment c) {
		int row = comdao.updataComment(c);
		return row;
	}

	@Override
	public List<Comment> getCommentByCondition(String Condition) {
		List<Comment> list = comdao.getCommentByCondition(Condition);
		return list;
	}

	@Override
	public List<Comment> getCommentPages(int pagesize, int page, String condition, boolean order) {
		List<Comment> list = comdao.getCommentPages(pagesize, page, condition, order);
		return list;
	}

	@Override
	public int Count(String condition) {
		int row = comdao.Count(condition);
		return row;
	}

	@Override
	public List<HotComGoods> findhotgoodcom() {
		List<HotComGoods> list = comdao.gethotcg();
		return list;
	}

	public static void main(String[] args) {
		CommentService com = new CommentServiceImpl();
		List<HotComGoods> list = com.findhotgoodcom();
		for (HotComGoods comment : list) {
			System.out.println(comment.getComments());
		}
	}
}
