package xm.chen.service;

import java.util.List;

import xm.chen.modal.Comment;
import xm.chen.modal.HotComGoods;

public interface CommentService {
	/**
	 * 添加评论
	 * 
	 * @param c
	 *            评论实体
	 * @return 受影响的行数
	 */
	public int addComment(Comment c);

	/**
	 * 删除评论
	 * 
	 * @param id
	 *            评论id
	 * @return 受影响的行数
	 */
	public int deleteComment(int id);

	/**
	 * 跟新评论
	 * 
	 * @param c
	 *            评论实体
	 * @return 受影响的行数
	 */
	public int updataComment(Comment c);

	/**
	 * 根据条件查询评论
	 * 
	 * @param Condition
	 *            条件
	 * @return 评论集合
	 */
	public List<Comment> getCommentByCondition(String Condition);

	/**
	 * 评论分页
	 * 
	 * @param pagesize
	 *            页大小
	 * @param page
	 *            当前页
	 * @param condition
	 *            条件
	 * @param order
	 *            是否排序
	 * @return 评论集合
	 */
	public List<Comment> getCommentPages(int pagesize, int page, String condition, boolean order);

	/**
	 * 根据条件查询数据条数
	 * 
	 * @param condition
	 *            条件
	 * @return 条数
	 */
	public int Count(String condition);

	public List<HotComGoods> findhotgoodcom();
}
