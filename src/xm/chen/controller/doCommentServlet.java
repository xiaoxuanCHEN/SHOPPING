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
	CommentService comser = null;// 评论服务层
	OrderService oser = null;// 订单服务层
	OrderDetailService odser = null;// 订单详情服务层

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
			addComment(out, request);// 添加评论
		} else if (type.equals("delcom")) {
			delComment(out, request);// 删除评论
		} else if (type.equals("getcom")) {
			listComment(out, request);// 得到评论集合
		} else if (type.equals("showcom")) {
			showComment(out, request);// 显示对应评论
		} else if (type.equals("getcomment")) {
			showCommentbygid(out, request);// 根据评论id显示评论
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
	 * 添加评论
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 */
	public void addComment(PrintWriter out, HttpServletRequest request) {
		int orow = 0;// 定义输出数据数
		User u = (User) request.getSession().getAttribute("user");// 用户数据
		int oid = Integer.parseInt(request.getParameter("oid"));// 订单id
		String comment = request.getParameter("comment");// 评论
		int status = Integer.parseInt(request.getParameter("status"));// 评价状态
		String ostatus = request.getParameter("ostatus");// 订单状态
		Comment com = new Comment(0, u.getId(), oid, common.StringtoTime().toLocaleString(), comment, status);
		int row = comser.addComment(com);// 添加评论
		// 将订单状态转化为序号
		int sta = OrderStatus.valueOf(ostatus).ordinal();
		Orders o = oser.getOrders(oid);// 获取订单
		if (o != null && ostatus != null) {
			o.setOrderstatus(sta);// 修改订单状态
			orow = oser.updateOrders(o);
		}
		if (row > 0 && orow > 0) {
			out.print(1);
		} else {
			out.print(0);
		}
	}

	/**
	 * 删除评论
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 */
	public void delComment(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("comid"));// 获取评论id
		int row = comser.deleteComment(id);// 删除评论
		out.print(row);
	}

	/**
	 * 将评论进行分页
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 */
	public void listComment(PrintWriter out, HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));// 获取页大小
		int page = Integer.parseInt(request.getParameter("page"));// 获取当前页
		int uid = Integer.parseInt(request.getParameter("uid"));// 获取用户id用于判断显示说有数据还是对应uid的数据
		User u = (User) request.getSession().getAttribute("user");
		int totalsize = 0;// 总页数
		List<Comment> list = null;// 定义评论集合
		if (uid == 0) {// 查询所有数据
			totalsize = comser.Count("");// 数据条数
			list = comser.getCommentPages(pagesize, page, "1=1", false);
		} else if (uid == 1) {// 根据uid查询数据
			totalsize = comser.Count("where uid='" + u.getId() + "'");
			list = comser.getCommentPages(pagesize, page, " uid='" + u.getId() + "'", false);
		}
		int totalpage = (totalsize + (pagesize - 1)) / pagesize;// 计算总页数
		out.print(
				JSONUtil.toJsonString1(list) + ",{\"current\":\"" + page + "\",\"totalpages\":\"" + totalpage + "\"}]");
	}

	/**
	 * 根据订单id查询对应评论
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 */
	public void showComment(PrintWriter out, HttpServletRequest request) {
		int oid = Integer.parseInt(request.getParameter("oid"));// 获取订单id
		List<Comment> list = comser.getCommentByCondition(" orderid='" + oid + "'");
		for (Comment comment : list) {// 输出评论数据
			out.print("<p>评论状态：" + CommentStatus.values()[comment.getCommentStatus() - 1].toString() + "</p><p>评论时间："
					+ comment.getCommentTime() + "</p><p>评论内容：" + comment.getComments() + "</p>");
		}
	}

	/**
	 * 根据商品id查询对应商品的所有评论
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 */
	public void showCommentbygid(PrintWriter out, HttpServletRequest request) {
		int gid = Integer.parseInt(request.getParameter("gid"));// 获取商品id
		List<OrderDetail> list = odser.findODByCondition("goodid='" + gid + "'");// 根据商品id查询订单详情集合
		List<Integer> l = new ArrayList<Integer>();// 定义orderid集合
		List<Comment> l2 = new ArrayList<Comment>();// 定义评论集合
		for (OrderDetail orderDetail : list) {
			l.add(orderDetail.getOrderid());// 将orderid添加入l集合
		}
		for (Integer integer : l) {
			List<Comment> listcom = comser.getCommentByCondition(" orderid='" + integer + "'");
			l2.addAll(listcom);// 将评论添加入评论集合
		}
		out.print(JSONUtil.toJsonString(l2));
	}

	/**
	 * 根据cid查询评论信息
	 * 
	 * @param out
	 * @param request
	 */
	public void getCommentbycid(PrintWriter out, HttpServletRequest request) {
		int cid = Integer.parseInt(request.getParameter("cid"));// 获取评论id
		List<Comment> listcom = comser.getCommentByCondition(" id='" + cid + "'");
		out.print(JSONUtil.toJsonString(listcom));
	}

	/**
	 * 修改评论内容
	 * 
	 * @param out
	 * @param request
	 */
	public void updateComment(PrintWriter out, HttpServletRequest request) {
		int cid = Integer.parseInt(request.getParameter("cid"));// 订单id
		String comment = request.getParameter("comment");// 评论
		Comment c = comser.getCommentByCondition(" id='" + cid + "'").get(0);
		Comment com = new Comment(c.getId(), c.getUid(), c.getOrderid(), common.StringtoTime().toLocaleString(),
				comment, c.getCommentStatus());
		int row = comser.updataComment(com);
		out.print(row);
	}
}
