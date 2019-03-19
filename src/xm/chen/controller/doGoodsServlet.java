package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xm.chen.dao.JSONUtil;
import xm.chen.modal.GoodType;
import xm.chen.modal.Goods;
import xm.chen.service.GoodService;
import xm.chen.service.GoodTypeService;
import xm.chen.service.impl.GoodServiceImpl;
import xm.chen.service.impl.GoodsTypeServiceImpl;

public class doGoodsServlet extends HttpServlet {
	GoodService gs = null;
	GoodTypeService gts = null;

	/**
	 * Constructor of the object.
	 */
	public doGoodsServlet() {
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
		if (type.equals("addgood")) {
			addGoodinfo(out, request);// 添加商品信息
		} else if (type.equals("listgood")) {
			listGoods(out, request);// 查询商品集合
		} else if (type.equals("delgood")) {
			delGoods(out, request);// 删除商品
		} else if (type.equals("getgoodbyid")) {
			getGoodsByid(out, request);// 根据id获取商品信息
		} else if (type.equals("editgood")) {
			editGoods(out, request);// 编辑商品信息
		} else if (type.equals("bykey")) {
			getGoodsByKey(out, request);// 通过键入值查询商品信息
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
		gs = new GoodServiceImpl();
		gts = new GoodsTypeServiceImpl();
	}

	/**
	 * 商品信息添加
	 * 
	 * @param out输出参数
	 * @param request
	 *            请求带过来的数据
	 */
	public void addGoodinfo(PrintWriter out, HttpServletRequest request) {
		String name = request.getParameter("name");// 用户名
		double price = Double.parseDouble(request.getParameter("price"));// 计金额
		int count = Integer.parseInt(request.getParameter("count"));// 数量
		int tyid = Integer.parseInt(request.getParameter("goodstype"));// 商品类型
		String config = request.getParameter("config").trim();// 配置
		String time = request.getParameter("time");// 时间
		String intro = request.getParameter("intro").trim();// 描述
		String remark = request.getParameter("remark").trim();// 介绍
		String info = "";
		String redirect = "";
		Goods good = new Goods(0, tyid, name, new BigDecimal(price), config, time, count, intro, remark);
		int row = gs.addGood(good);// 添加商品信息
		if (row > 0) {
			info = "商品添加成功!";
			redirect = "window.location.href='goods/listGoods.jsp';";
		} else {
			info = "商品添加失败!";
			redirect = "window.location.href='goods/addGoods.jsp';";
		}
		String s = "<script type=\"text/javascript\">" + "alert('" + info + "');" + redirect + "</script>";
		out.print(s);
	}

	/**
	 * 根据id删除商品信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的id
	 */
	public void delGoods(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// 获取商品id
		int row = gs.deleteGood(id);// 删除商品信息
		out.print(row);
	}

	/**
	 * 查询所有商品信息并分页
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的数据
	 */
	public void listGoods(PrintWriter out, HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));// 获取页大小
		int page = Integer.parseInt(request.getParameter("page"));// 获取当前页
		int totalsize = gs.Count("");// 数据条数
		// 计算总页数
		int totalpage = (totalsize + (pagesize - 1)) / pagesize;
		List<Goods> list1 = gs.getByGoodPage(pagesize, page, "1=1", true);// 查询分页数据
		out.print(JSONUtil.toJsonString1(list1) + ",{\"current\":\"" + page + "\",\"totalpages\":\"" + totalpage
				+ "\"}]");
	}

	/**
	 * 根据id的到某个商品的详细信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的id
	 */
	public void getGoodsByid(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// 商品id
		Goods good = gs.getGoodById(id);// 查询商品信息
		GoodType gt = gts.getGoodTypeById(good.getTypeid());// 商品类型信息
		out.print("<div class=\"ad\"><span>选择类型：</span> <select name=\"goodstype\"><option value=\"" + gt.getId()
				+ "\">" + gt.getTypeName() + "</option></select></div>");
		out.print("<div class=\"ad\"><span>商品名称：</span> <input type=\"text\" name=\"name\" value=\""
				+ good.getGoodName() + "\"></div>");
		out.print("<div class=\"ad\"><span>商品价格：</span> <input type=\"text\" name=\"price\" value=\"" + good.getPrice()
				+ "\"></div>");
		out.print("<div class=\"ad\"><span>商品数量：</span> <input type=\"text\" name=\"count\" value=\"" + good.getCount()
				+ "\"></div>");
		out.print("<div class=\"ad\"><span>上市时间：</span> <input type=\"text\" name=\"time\" value=\""
				+ good.getProductDate() + "\"></div>");
		out.print("<div class=\"ad\"><span>产品配置：</span><br><textarea rows=\"1\" cols=\"30\" name=\"config\">"
				+ good.getConfig() + "</textarea></div>");
		out.print("<div class=\"ad\"><span>商品简介：</span><br><textarea rows=\"2\" cols=\"30\" name=\"intro\">	"
				+ good.getIntroduction() + "</textarea></div>");
		out.print("<div class=\"ad\"><span>商品描述：</span><br><textarea rows=\"2\" cols=\"30\" name=\"remark\">"
				+ good.getDescription() + "</textarea></div>");
	}

	/**
	 * 根据id编辑商品信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的数据
	 */
	public void editGoods(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int count = Integer.parseInt(request.getParameter("count"));
		int tyid = Integer.parseInt(request.getParameter("typeid"));
		String config = request.getParameter("config").trim();
		String time = request.getParameter("time");
		String intro = request.getParameter("intro").trim();
		String remark = request.getParameter("remark").trim();
		Goods good = new Goods(id, tyid, name, new BigDecimal(price), config, time, count, intro, remark);
		int row = gs.updateGood(good);// 跟新商品信息
		out.print(row);
	}

	/**
	 * 根据search输入的内容进行模糊查询商品信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的参数
	 */
	public void getGoodsByKey(PrintWriter out, HttpServletRequest request) {
		String key = request.getParameter("key");
		List<Goods> list = gs.getGoodByKey(key, 6);
		out.print(JSONUtil.toJsonString(list));
	}
}
