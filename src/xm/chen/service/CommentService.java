package xm.chen.service;

import java.util.List;

import xm.chen.modal.Comment;
import xm.chen.modal.HotComGoods;

public interface CommentService {
	/**
	 * �������
	 * 
	 * @param c
	 *            ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int addComment(Comment c);

	/**
	 * ɾ������
	 * 
	 * @param id
	 *            ����id
	 * @return ��Ӱ�������
	 */
	public int deleteComment(int id);

	/**
	 * ��������
	 * 
	 * @param c
	 *            ����ʵ��
	 * @return ��Ӱ�������
	 */
	public int updataComment(Comment c);

	/**
	 * ����������ѯ����
	 * 
	 * @param Condition
	 *            ����
	 * @return ���ۼ���
	 */
	public List<Comment> getCommentByCondition(String Condition);

	/**
	 * ���۷�ҳ
	 * 
	 * @param pagesize
	 *            ҳ��С
	 * @param page
	 *            ��ǰҳ
	 * @param condition
	 *            ����
	 * @param order
	 *            �Ƿ�����
	 * @return ���ۼ���
	 */
	public List<Comment> getCommentPages(int pagesize, int page, String condition, boolean order);

	/**
	 * ����������ѯ��������
	 * 
	 * @param condition
	 *            ����
	 * @return ����
	 */
	public int Count(String condition);

	public List<HotComGoods> findhotgoodcom();
}
