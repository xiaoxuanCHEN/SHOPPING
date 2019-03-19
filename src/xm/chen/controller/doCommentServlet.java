package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xm.chen.dao.JSONUtil;
import xm.chen.dao.common;
import xm.chen.modal.Comment;
import xm.chen.modal.CommentStatus;
import xm.chen.modal.OrderDetail;
import xm.chen.modal.OrderStatus;
import xm.chen.modal.Orders;
import xm.chen.modal.User;
import xm.chen.service.CommentService;
import xm.chen.service.OrderDetailService;
import xm.chen.service.OrderService;
import xm.chen.service.impl.CommentServiceImpl;
import xm.chen.service.impl.OrderDetailServiceImpl;
import xm.chen.service.impl.OrderServiceImpl;

public class doCommentServlet extends HttpServlet {
	CommentService comser = null;// ���۷����
	OrderService oser = null;// ���������
	OrderDetailService odser = null;// ������������

	/**
	 * Constructor of the object.
	 */
	public doCommentServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		if (type.equals("addcom")) {
			addComment(out, request);// �������
		} else if (type.equals("delcom")) {
			delComment(out, request);// ɾ������
		} else if (type.equals("getcom")) {
			listComment(out, request);// �õ����ۼ���
		} else if (type.equals("showcom")) {
			showComment(out, request);// ��ʾ��Ӧ����
		} else if (type.equals("getcomment")) {
			showCommentbygid(out, request);// ��������id��ʾ����
		} else if (type.equals("getcombycid")) {
			getCommentbycid(out, request);
		} else if (type.equals("updatecom")) {
			updateComment(out, request);
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		comser = new CommentServiceImpl();
		oser = new OrderServiceImpl();
		odser = new OrderDetailServiceImpl();
	}

	/**
	 * �������
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void addComment(PrintWriter out, HttpServletRequest request) {
		int orow = 0;// �������������
		User u = (User) request.getSession().getAttribute("user");// �û�����
		int oid = Integer.parseInt(request.getParameter("oid"));// ����id
		String comment = request.getParameter("comment");// ����
		int status = Integer.parseInt(request.getParameter("status"));// ����״̬
		String ostatus = request.getParameter("ostatus");// ����״̬
		Comment com = new Comment(0, u.getId(), oid, common.StringtoTime().toLocaleString(), comment, status);
		int row = comser.addComment(com);// �������
		// ������״̬ת��Ϊ���
		int sta = OrderStatus.valueOf(ostatus).ordinal();
		Orders o = oser.getOrders(oid);// ��ȡ����
		if (o != null && ostatus != null) {
			o.setOrderstatus(sta);// �޸Ķ���״̬
			orow = oser.updateOrders(o);
		}
		if (row > 0 && orow > 0) {
			out.print(1);
		} else {
			out.print(0);
		}
	}

	/**
	 * ɾ������
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void delComment(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("comid"));// ��ȡ����id
		int row = comser.deleteComment(id);// ɾ������
		out.print(row);
	}

	/**
	 * �����۽��з�ҳ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void listComment(PrintWriter out, HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));// ��ȡҳ��С
		int page = Integer.parseInt(request.getParameter("page"));// ��ȡ��ǰҳ
		int uid = Integer.parseInt(request.getParameter("uid"));// ��ȡ�û�id�����ж���ʾ˵�����ݻ��Ƕ�Ӧuid������
		User u = (User) request.getSession().getAttribute("user");
		int totalsize = 0;// ��ҳ��
		List<Comment> list = null;// �������ۼ���
		if (uid == 0) {// ��ѯ��������
			totalsize = comser.Count("");// ��������
			list = comser.getCommentPages(pagesize, page, "1=1", false);
		} else if (uid == 1) {// ����uid��ѯ����
			totalsize = comser.Count("where uid='" + u.getId() + "'");
			list = comser.getCommentPages(pagesize, page, " uid='" + u.getId() + "'", false);
		}
		int totalpage = (totalsize + (pagesize - 1)) / pagesize;// ������ҳ��
		out.print(
				JSONUtil.toJsonString1(list) + ",{\"current\":\"" + page + "\",\"totalpages\":\"" + totalpage + "\"}]");
	}

	/**
	 * ���ݶ���id��ѯ��Ӧ����
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void showComment(PrintWriter out, HttpServletRequest request) {
		int oid = Integer.parseInt(request.getParameter("oid"));// ��ȡ����id
		List<Comment> list = comser.getCommentByCondition(" orderid='" + oid + "'");
		for (Comment comment : list) {// �����������
			out.print("<p>����״̬��" + CommentStatus.values()[comment.getCommentStatus() - 1].toString() + "</p><p>����ʱ�䣺"
					+ comment.getCommentTime() + "</p><p>�������ݣ�" + comment.getComments() + "</p>");
		}
	}

	/**
	 * ������Ʒid��ѯ��Ӧ��Ʒ����������
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void showCommentbygid(PrintWriter out, HttpServletRequest request) {
		int gid = Integer.parseInt(request.getParameter("gid"));// ��ȡ��Ʒid
		List<OrderDetail> list = odser.findODByCondition("goodid='" + gid + "'");// ������Ʒid��ѯ�������鼯��
		List<Integer> l = new ArrayList<Integer>();// ����orderid����
		List<Comment> l2 = new ArrayList<Comment>();// �������ۼ���
		for (OrderDetail orderDetail : list) {
			l.add(orderDetail.getOrderid());// ��orderid�����l����
		}
		for (Integer integer : l) {
			List<Comment> listcom = comser.getCommentByCondition(" orderid='" + integer + "'");
			l2.addAll(listcom);// ��������������ۼ���
		}
		out.print(JSONUtil.toJsonString(l2));
	}

	/**
	 * ����cid��ѯ������Ϣ
	 * 
	 * @param out
	 * @param request
	 */
	public void getCommentbycid(PrintWriter out, HttpServletRequest request) {
		int cid = Integer.parseInt(request.getParameter("cid"));// ��ȡ����id
		List<Comment> listcom = comser.getCommentByCondition(" id='" + cid + "'");
		out.print(JSONUtil.toJsonString(listcom));
	}

	/**
	 * �޸���������
	 * 
	 * @param out
	 * @param request
	 */
	public void updateComment(PrintWriter out, HttpServletRequest request) {
		int cid = Integer.parseInt(request.getParameter("cid"));// ����id
		String comment = request.getParameter("comment");// ����
		Comment c = comser.getCommentByCondition(" id='" + cid + "'").get(0);
		Comment com = new Comment(c.getId(), c.getUid(), c.getOrderid(), common.StringtoTime().toLocaleString(),
				comment, c.getCommentStatus());
		int row = comser.updataComment(com);
		out.print(row);
	}
}
