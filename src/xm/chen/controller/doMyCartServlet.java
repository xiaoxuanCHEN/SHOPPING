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
			addMyCart(out, request);// 加入购物车
		} else if (type.equals("delCartBygid")) {
			delMyCartBygid(out, request);// 根据商品id删除购物车数据
		} else if (type.equals("delCartByuid")) {
			delMyCartByuid(out, request);// 根据uid删除购物车所有数据
		} else if (type.equals("updateCart")) {
			updateMyCart(out, request);// 跟新购物车数据
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
	 * 加入购物车
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的参数
	 */
	public void addMyCart(PrintWriter out, HttpServletRequest request) {
		int count = Integer.parseInt(request.getParameter("count"));// 数量
		int goodid = Integer.parseInt(request.getParameter("goodid"));// 商品id
		User u = (User) request.getSession().getAttribute("user");// 获取用户信息
		int row = 0;
		if (count > 0 && goodid > 0) {
			List<MyCart> carts = mycart.getAllCart(u.getId());// 根据用户id查询购物车信息
			boolean falg = true;
			for (int i = 0; i < carts.size(); i++) {
				// 如果该商品之前添加过只跟新数量
				if (carts.get(i).getGoodid() == goodid) {
					int c = carts.get(i).getCount();
					c += count;
					MyCart cart = new MyCart(carts.get(i).getId(), u.getId(), goodid, c);
					row = mycart.updatecart(cart);// 跟新数据
					falg = false;
					break;
				}
			}
			if (falg) {
				MyCart cart = new MyCart(0, u.getId(), goodid, count);
				row = mycart.addcart(cart);// 添加进购物车
			}
		}
		out.print(row);
	}

	/**
	 * 根据gid删除购物车中的信息
	 * 
	 * @param out
	 *            输出对象
	 * @param request
	 *            请求携带过来的参数
	 */
	public void delMyCartBygid(PrintWriter out, HttpServletRequest request) {
		int gid = Integer.parseInt(request.getParameter("gid"));
		int row = mycart.delcart(gid);// 删除购物车数据
		out.print(row);
	}

	public void getAllcart(PrintWriter out, HttpServletRequest request) {
		List<MyCart> list = mycart.getAllCartByCondtion("1=1");
		out.print(JSONUtil.toJsonString(list));
	}

	/**
	 * 根据用户id删除购物车
	 * 
	 * @param out
	 *            输出对象
	 * @param request
	 *            请求
	 */
	public void delMyCartByuid(PrintWriter out, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		int row = mycart.delcart1(u.getId());// 删除购物车所有数据
		out.print(row);
	}

	/**
	 * 根据id跟新购物车信息
	 * 
	 * @param out
	 *            输出对象
	 * @param request
	 *            请求携带过来的参数
	 */
	public void updateMyCart(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("cid"));
		User u = (User) request.getSession().getAttribute("user");
		int count = Integer.parseInt(request.getParameter("count"));
		int goodid = Integer.parseInt(request.getParameter("gid"));
		MyCart cart = new MyCart(id, u.getId(), goodid, count);
		int row = mycart.updatecart(cart);// 跟新购物车数据
		out.print(row);
	}

	public void getMyCart(PrintWriter out, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		List<MyCart> list = mycart.getAllCart(u.getId());// 根据uid获取购物车信息
		out.print(JSONUtil.toJsonString(list));
	}
}
