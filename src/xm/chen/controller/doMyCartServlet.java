package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xm.chen.dao.JSONUtil;
import xm.chen.modal.MyCart;
import xm.chen.modal.User;
import xm.chen.service.mycartService;
import xm.chen.service.impl.mycartServiceIpml;

public class doMyCartServlet extends HttpServlet {
	mycartService mycart = null;

	/**
	 * Constructor of the object.
	 */
	public doMyCartServlet() {
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

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		if (type.equals("addCart")) {
			addMyCart(out, request);// ���빺�ﳵ
		} else if (type.equals("delCartBygid")) {
			delMyCartBygid(out, request);// ������Ʒidɾ�����ﳵ����
		} else if (type.equals("delCartByuid")) {
			delMyCartByuid(out, request);// ����uidɾ�����ﳵ��������
		} else if (type.equals("updateCart")) {
			updateMyCart(out, request);// ���¹��ﳵ����
		} else if (type.equals("getall")) {
			getAllcart(out, request);
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
		mycart = new mycartServiceIpml();
	}

	/**
	 * ���빺�ﳵ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я�������Ĳ���
	 */
	public void addMyCart(PrintWriter out, HttpServletRequest request) {
		int count = Integer.parseInt(request.getParameter("count"));// ����
		int goodid = Integer.parseInt(request.getParameter("goodid"));// ��Ʒid
		User u = (User) request.getSession().getAttribute("user");// ��ȡ�û���Ϣ
		int row = 0;
		if (count > 0 && goodid > 0) {
			List<MyCart> carts = mycart.getAllCart(u.getId());// �����û�id��ѯ���ﳵ��Ϣ
			boolean falg = true;
			for (int i = 0; i < carts.size(); i++) {
				// �������Ʒ֮ǰ��ӹ�ֻ��������
				if (carts.get(i).getGoodid() == goodid) {
					int c = carts.get(i).getCount();
					c += count;
					MyCart cart = new MyCart(carts.get(i).getId(), u.getId(), goodid, c);
					row = mycart.updatecart(cart);// ��������
					falg = false;
					break;
				}
			}
			if (falg) {
				MyCart cart = new MyCart(0, u.getId(), goodid, count);
				row = mycart.addcart(cart);// ��ӽ����ﳵ
			}
		}
		out.print(row);
	}

	/**
	 * ����gidɾ�����ﳵ�е���Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я�������Ĳ���
	 */
	public void delMyCartBygid(PrintWriter out, HttpServletRequest request) {
		int gid = Integer.parseInt(request.getParameter("gid"));
		int row = mycart.delcart(gid);// ɾ�����ﳵ����
		out.print(row);
	}

	public void getAllcart(PrintWriter out, HttpServletRequest request) {
		List<MyCart> list = mycart.getAllCartByCondtion("1=1");
		out.print(JSONUtil.toJsonString(list));
	}

	/**
	 * �����û�idɾ�����ﳵ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����
	 */
	public void delMyCartByuid(PrintWriter out, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		int row = mycart.delcart1(u.getId());// ɾ�����ﳵ��������
		out.print(row);
	}

	/**
	 * ����id���¹��ﳵ��Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я�������Ĳ���
	 */
	public void updateMyCart(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("cid"));
		User u = (User) request.getSession().getAttribute("user");
		int count = Integer.parseInt(request.getParameter("count"));
		int goodid = Integer.parseInt(request.getParameter("gid"));
		MyCart cart = new MyCart(id, u.getId(), goodid, count);
		int row = mycart.updatecart(cart);// ���¹��ﳵ����
		out.print(row);
	}

	public void getMyCart(PrintWriter out, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		List<MyCart> list = mycart.getAllCart(u.getId());// ����uid��ȡ���ﳵ��Ϣ
		out.print(JSONUtil.toJsonString(list));
	}
}
