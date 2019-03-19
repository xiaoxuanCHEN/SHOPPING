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
			addGoodinfo(out, request);// �����Ʒ��Ϣ
		} else if (type.equals("listgood")) {
			listGoods(out, request);// ��ѯ��Ʒ����
		} else if (type.equals("delgood")) {
			delGoods(out, request);// ɾ����Ʒ
		} else if (type.equals("getgoodbyid")) {
			getGoodsByid(out, request);// ����id��ȡ��Ʒ��Ϣ
		} else if (type.equals("editgood")) {
			editGoods(out, request);// �༭��Ʒ��Ϣ
		} else if (type.equals("bykey")) {
			getGoodsByKey(out, request);// ͨ������ֵ��ѯ��Ʒ��Ϣ
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
	 * ��Ʒ��Ϣ���
	 * 
	 * @param out�������
	 * @param request
	 *            ���������������
	 */
	public void addGoodinfo(PrintWriter out, HttpServletRequest request) {
		String name = request.getParameter("name");// �û���
		double price = Double.parseDouble(request.getParameter("price"));// �ƽ��
		int count = Integer.parseInt(request.getParameter("count"));// ����
		int tyid = Integer.parseInt(request.getParameter("goodstype"));// ��Ʒ����
		String config = request.getParameter("config").trim();// ����
		String time = request.getParameter("time");// ʱ��
		String intro = request.getParameter("intro").trim();// ����
		String remark = request.getParameter("remark").trim();// ����
		String info = "";
		String redirect = "";
		Goods good = new Goods(0, tyid, name, new BigDecimal(price), config, time, count, intro, remark);
		int row = gs.addGood(good);// �����Ʒ��Ϣ
		if (row > 0) {
			info = "��Ʒ��ӳɹ�!";
			redirect = "window.location.href='goods/listGoods.jsp';";
		} else {
			info = "��Ʒ���ʧ��!";
			redirect = "window.location.href='goods/addGoods.jsp';";
		}
		String s = "<script type=\"text/javascript\">" + "alert('" + info + "');" + redirect + "</script>";
		out.print(s);
	}

	/**
	 * ����idɾ����Ʒ��Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я��������id
	 */
	public void delGoods(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// ��ȡ��Ʒid
		int row = gs.deleteGood(id);// ɾ����Ʒ��Ϣ
		out.print(row);
	}

	/**
	 * ��ѯ������Ʒ��Ϣ����ҳ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я������������
	 */
	public void listGoods(PrintWriter out, HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));// ��ȡҳ��С
		int page = Integer.parseInt(request.getParameter("page"));// ��ȡ��ǰҳ
		int totalsize = gs.Count("");// ��������
		// ������ҳ��
		int totalpage = (totalsize + (pagesize - 1)) / pagesize;
		List<Goods> list1 = gs.getByGoodPage(pagesize, page, "1=1", true);// ��ѯ��ҳ����
		out.print(JSONUtil.toJsonString1(list1) + ",{\"current\":\"" + page + "\",\"totalpages\":\"" + totalpage
				+ "\"}]");
	}

	/**
	 * ����id�ĵ�ĳ����Ʒ����ϸ��Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я��������id
	 */
	public void getGoodsByid(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// ��Ʒid
		Goods good = gs.getGoodById(id);// ��ѯ��Ʒ��Ϣ
		GoodType gt = gts.getGoodTypeById(good.getTypeid());// ��Ʒ������Ϣ
		out.print("<div class=\"ad\"><span>ѡ�����ͣ�</span> <select name=\"goodstype\"><option value=\"" + gt.getId()
				+ "\">" + gt.getTypeName() + "</option></select></div>");
		out.print("<div class=\"ad\"><span>��Ʒ���ƣ�</span> <input type=\"text\" name=\"name\" value=\""
				+ good.getGoodName() + "\"></div>");
		out.print("<div class=\"ad\"><span>��Ʒ�۸�</span> <input type=\"text\" name=\"price\" value=\"" + good.getPrice()
				+ "\"></div>");
		out.print("<div class=\"ad\"><span>��Ʒ������</span> <input type=\"text\" name=\"count\" value=\"" + good.getCount()
				+ "\"></div>");
		out.print("<div class=\"ad\"><span>����ʱ�䣺</span> <input type=\"text\" name=\"time\" value=\""
				+ good.getProductDate() + "\"></div>");
		out.print("<div class=\"ad\"><span>��Ʒ���ã�</span><br><textarea rows=\"1\" cols=\"30\" name=\"config\">"
				+ good.getConfig() + "</textarea></div>");
		out.print("<div class=\"ad\"><span>��Ʒ��飺</span><br><textarea rows=\"2\" cols=\"30\" name=\"intro\">	"
				+ good.getIntroduction() + "</textarea></div>");
		out.print("<div class=\"ad\"><span>��Ʒ������</span><br><textarea rows=\"2\" cols=\"30\" name=\"remark\">"
				+ good.getDescription() + "</textarea></div>");
	}

	/**
	 * ����id�༭��Ʒ��Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я������������
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
		int row = gs.updateGood(good);// ������Ʒ��Ϣ
		out.print(row);
	}

	/**
	 * ����search��������ݽ���ģ����ѯ��Ʒ��Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я�������Ĳ���
	 */
	public void getGoodsByKey(PrintWriter out, HttpServletRequest request) {
		String key = request.getParameter("key");
		List<Goods> list = gs.getGoodByKey(key, 6);
		out.print(JSONUtil.toJsonString(list));
	}
}
