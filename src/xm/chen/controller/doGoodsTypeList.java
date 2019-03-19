package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xm.chen.dao.JSONUtil;
import xm.chen.modal.GoodType;
import xm.chen.service.GoodTypeService;
import xm.chen.service.impl.GoodsTypeServiceImpl;

public class doGoodsTypeList extends HttpServlet {
	GoodTypeService gts = null;

	/**
	 * Constructor of the object.
	 */
	public doGoodsTypeList() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (type.equals("list")) {
			gtList(out);// 获取商品类型集合
		} else if (type.equals("addty")) {
			addgt(out, request);// 添加商品类型信息
		} else if (type.equals("delty")) {
			delgt(out, request);// 删除商品类型
		} else if (type.equals("gettybyid")) {
			gettybyid(out, request);// 通过商品类型id获取类型信息
		} else if (type.equals("editty")) {
			editty(out, request);// 编辑类型信息
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
		gts = new GoodsTypeServiceImpl();
	}

	/**
	 * 得到所有商品类型信息
	 * 
	 * @param out
	 *            输出参数
	 */
	public void gtList(PrintWriter out) {
		List<GoodType> list = gts.getGoodType("1=1");
		out.print(JSONUtil.toJsonString(list));
	}

	/**
	 * 添加商品类型
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的数据
	 */
	public void addgt(PrintWriter out, HttpServletRequest request) {
		String tyname = request.getParameter("name");// 商品类型名称
		String remark = request.getParameter("remark");// 介绍
		GoodType gt = new GoodType(0, tyname, remark);// 商品类型实体
		String info = "";
		String redirect = "";
		int row = gts.addGoodType(gt);// 添加商品类型
		if (row > 0) {
			info = "商品类型添加成功!";
			redirect = "window.location.href='goodsType/listGoodsType.jsp';";
		} else {
			info = "商品类型添加失败!";
			redirect = "window.location.href='goodsType/addGoodsType.jsp';";
		}
		String s = "<script type=\"text/javascript\">" + "alert('" + info + "');" + redirect + "</script>";
		out.print(s);
	}

	/**
	 * 根据id删除商品类型信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的id
	 */
	public void delgt(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// 商品类型id
		int row = gts.delGoodType(id);// 删除商品类型
		out.print(row);
	}

	/**
	 * 根据id得到某个商品类型的详细信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的数据
	 */
	public void gettybyid(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// 商品类型id
		GoodType gt = gts.getGoodTypeById(id);// 获取商品类型信息
		// request.setAttribute("goodtype", gt);
		out.print("<span>类型名称：</span><input type=\"text\" value=\"" + gt.getTypeName() + "\"/>");
		out.print("<br><span>备注说明：</span><br><textarea rows=\"2\" cols=\"30\" name=\"remark\">" + gt.getRemark()
				+ "</textarea>");
	}

	/**
	 * 根据id编辑商品类型信息
	 * 
	 * @param out
	 *            输出参数
	 * @param request
	 *            请求携带过来的参数
	 */
	public void editty(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String tyname = request.getParameter("tyname");
		String remark = request.getParameter("remark");
		GoodType gt = new GoodType(id, tyname, remark);
		int row = gts.editGoodType(gt);// 编辑商品类型信息
		out.print(row);
	}
}
