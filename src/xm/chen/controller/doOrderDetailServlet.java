package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xm.chen.modal.Goods;
import xm.chen.modal.OrderDetail;
import xm.chen.modal.Orders;
import xm.chen.service.GoodService;
import xm.chen.service.ImagesService;
import xm.chen.service.OrderDetailService;
import xm.chen.service.OrderService;
import xm.chen.service.impl.GoodServiceImpl;
import xm.chen.service.impl.ImagesServiceImpl;
import xm.chen.service.impl.OrderDetailServiceImpl;
import xm.chen.service.impl.OrderServiceImpl;

public class doOrderDetailServlet extends HttpServlet {
	OrderDetailService odser = null;
	OrderService oser = null;
	GoodService gser = null;
	ImagesService imgser = null;

	/**
	 * Constructor of the object.
	 */
	public doOrderDetailServlet() {
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
		if (type.equals("xq")) {// 订单详情数据
			getorderDetail(out, request);
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
		odser = new OrderDetailServiceImpl();
		oser = new OrderServiceImpl();
		gser = new GoodServiceImpl();
		imgser = new ImagesServiceImpl();
	}

	/**
	 * 订单详情的展示
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求
	 */
	public void getorderDetail(PrintWriter out, HttpServletRequest request) {
		int oid = Integer.parseInt(request.getParameter("oid"));// 订单id
		Orders o = oser.getOrders(oid);// 获取订单实体
		List<OrderDetail> odlist = odser.findODByCondition("orderid='" + o.getId() + "'");// 查询订单详情集合
		out.print(
				"<table width=\"500px;\"><tr><th style=\"width:120px;text-align:center;\"></th><th style=\"width:120px;text-align:center;\">商品名称</th>"
						+ "<th style=\"width:120px;text-align:center;\">数量</th><th style=\"width:120px;text-align:center;\">价格</th></tr>");
		for (OrderDetail orderDetail : odlist) {
			int goodid = orderDetail.getGoodid();
			Goods good = gser.getGoodById(goodid);// 获取商品集合
			String[] imgpath = imgser.goodsImg(goodid);// 获取图片
			out.print("<tr><td style=\"text-align:center;line-height:45px;\"><a href=\"goods/goodsDetail.jsp?gid="
					+ good.getId() + "\"><img style=\"width:50px;height:50px;\" src=\"upload/" + imgpath[0]
					+ "\"></a></td><td style=\"text-align:center;\">" + good.getGoodName()
					+ "</td><td style=\"text-align:center;\">" + orderDetail.getCount()
					+ "</td><td style=\"text-align:center;\">"
					+ good.getPrice().multiply(new BigDecimal(orderDetail.getCount())) + "</td></tr>");
		}
		out.print("</table>");
	}
}
