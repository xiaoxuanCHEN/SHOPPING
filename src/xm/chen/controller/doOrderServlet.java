package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xm.chen.dao.JSONUtil;
import xm.chen.modal.MyCart;
import xm.chen.modal.OrderStatus;
import xm.chen.modal.Orders;
import xm.chen.modal.User;
import xm.chen.service.OrderService;
import xm.chen.service.UserService;
import xm.chen.service.mycartService;
import xm.chen.service.impl.OrderServiceImpl;
import xm.chen.service.impl.UserServiceImpl;
import xm.chen.service.impl.mycartServiceIpml;

public class doOrderServlet extends HttpServlet {
	OrderService oser = null;
	UserService user = null;
	mycartService cartser = null;

	/**
	 * Constructor of the object.
	 */
	public doOrderServlet() {
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
		if (type.equals("zjbuy")) {
			zjBuy(out, request);// ֱ�ӹ���
		} else if (type.equals("pay")) {
			pay(out, request);// ֧��
		} else if (type.equals("edit")) {
			editOrderStatus(out, request);// �༭����״̬
		} else if (type.equals("del")) {
			delOrder(out, request);// ɾ������
		} else if (type.equals("carbuy")) {
			cartBuy(out, request, response);// �ӹ��ﳵ�µ�
		} else if (type.equals("tj")) {
			tj(out, request);
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
		oser = new OrderServiceImpl();
		user = new UserServiceImpl();
		cartser = new mycartServiceIpml();
	}

	public void getAllorder(PrintWriter out, HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));// ҳ��С
		int page = Integer.parseInt(request.getParameter("page"));// ��ǰҳ
		int totalsize = oser.getcount("");// ��������
		int totalpage = (totalsize + (pagesize - 1)) / pagesize;// ������ҳ��
		List<Orders> list = oser.getOrdersByConditionPage(pagesize, totalpage, "1=1", true);
		out.print(
				JSONUtil.toJsonString1(list) + ",{\"current\":\"" + page + "\",\"totalpages\":\"" + totalpage + "\"}]");
	}

	/**
	 * ����Ʒ����ҳ��ֱ�ӹ���
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����
	 */
	public void zjBuy(PrintWriter out, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		int gid = Integer.parseInt(request.getParameter("gid"));
		int count = Integer.parseInt(request.getParameter("count"));
		int row = oser.addOrders1(gid, count, u.getId());// ��Ӷ���
		out.print(row);
	}

	/**
	 * ����
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����
	 */
	public void pay(PrintWriter out, HttpServletRequest request) {
		String s = "";// �趨��Ϣ��ʾ
		User u = (User) request.getSession().getAttribute("user");
		int id = Integer.parseInt(request.getParameter("oid"));// ����id
		String pwd = request.getParameter("transpwd");// ֧�����
		u = user.getUserByCondition("id='" + u.getId() + "'").get(0);// ��ѯ�û���Ϣ
		if (pwd != null && pwd.equals(u.getTranspwd())) {
			Orders o = oser.getOrders(id);// ����ʵ��
			double money = u.getBalance().doubleValue() - o.getTotalmoney().doubleValue();// �����˻����
			if (money > 0 && o.getOrderstatus() == OrderStatus.δ����.ordinal()) {
				o.setOrderstatus(OrderStatus.�ȴ�����.ordinal());
				oser.updateOrders(o);// ���¶���״̬
				u.setBalance(new BigDecimal(money));
				user.updateUser(u);// �����˻����
				s = "֧���ɹ�!";
			} else {
				s = "�������ֵ!";
			}
		} else {
			s = "�������!";
		}
		out.print(s);
	}

	/**
	 * �༭����״̬
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����
	 */
	public void editOrderStatus(PrintWriter out, HttpServletRequest request) {
		int row = 0;
		int id = Integer.parseInt(request.getParameter("oid"));
		String status = request.getParameter("status");
		// ������״̬ת��Ϊ���
		int sta = OrderStatus.valueOf(status).ordinal();
		Orders o = oser.getOrders(id);
		if (o != null && status != null) {
			o.setOrderstatus(sta);
			row = oser.updateOrders(o);// ���¶���״̬
		}
		out.print(row);
	}

	/**
	 * ɾ������
	 * 
	 * @param out
	 * @param request
	 */
	public void delOrder(PrintWriter out, HttpServletRequest request) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Orders o = oser.getOrders(oid);
		int row = oser.deleteOrders(o);
		out.print(row);
	}

	/**
	 * ���ﳵ�Ľ���
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void cartBuy(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		String info = "";
		String redirect = "";
		User u = (User) request.getSession().getAttribute("user");
		String[] cartsid = request.getParameterValues("check");
		if (cartsid == null) {
			try {
				response.sendRedirect("cart/myCart.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// ���幺������
		List<MyCart> carts = new ArrayList<MyCart>();
		// ���弴ʱ������Ʒ��������
		List<Integer> count = new ArrayList<Integer>();
		for (int i = 0; i < cartsid.length; i++) {
			MyCart cart = cartser.getCart(Integer.parseInt(cartsid[i]));// ͨ��id���ҹ��ﳵ����
			count.add(Integer.parseInt(request.getParameter("incount" + cartsid[i])));
			carts.add(cart);// �Ѷ�����뼯��
		}
		int row = oser.addOrders(carts, count, u.getId());
		if (row > 0) {
			info = "����ɹ�!";
			redirect = "window.location.href='order/order.jsp';";
		} else {
			info = "����ʧ��!";
			redirect = "window.location.href='cart/myCart.jsp';";
		}
		String s = "<script type=\"text/javascript\">" + "alert('" + info + "');" + redirect + "</script>";
		out.print(s);
	}

	public void tj(PrintWriter out, HttpServletRequest request) {		
		List<Orders> list = oser.getSumTotal();
		out.print(JSONUtil.toJsonString(list));
	}
}
