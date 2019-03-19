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
			gtList(out);// ��ȡ��Ʒ���ͼ���
		} else if (type.equals("addty")) {
			addgt(out, request);// �����Ʒ������Ϣ
		} else if (type.equals("delty")) {
			delgt(out, request);// ɾ����Ʒ����
		} else if (type.equals("gettybyid")) {
			gettybyid(out, request);// ͨ����Ʒ����id��ȡ������Ϣ
		} else if (type.equals("editty")) {
			editty(out, request);// �༭������Ϣ
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
	 * �õ�������Ʒ������Ϣ
	 * 
	 * @param out
	 *            �������
	 */
	public void gtList(PrintWriter out) {
		List<GoodType> list = gts.getGoodType("1=1");
		out.print(JSONUtil.toJsonString(list));
	}

	/**
	 * �����Ʒ����
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я������������
	 */
	public void addgt(PrintWriter out, HttpServletRequest request) {
		String tyname = request.getParameter("name");// ��Ʒ��������
		String remark = request.getParameter("remark");// ����
		GoodType gt = new GoodType(0, tyname, remark);// ��Ʒ����ʵ��
		String info = "";
		String redirect = "";
		int row = gts.addGoodType(gt);// �����Ʒ����
		if (row > 0) {
			info = "��Ʒ������ӳɹ�!";
			redirect = "window.location.href='goodsType/listGoodsType.jsp';";
		} else {
			info = "��Ʒ�������ʧ��!";
			redirect = "window.location.href='goodsType/addGoodsType.jsp';";
		}
		String s = "<script type=\"text/javascript\">" + "alert('" + info + "');" + redirect + "</script>";
		out.print(s);
	}

	/**
	 * ����idɾ����Ʒ������Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я��������id
	 */
	public void delgt(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// ��Ʒ����id
		int row = gts.delGoodType(id);// ɾ����Ʒ����
		out.print(row);
	}

	/**
	 * ����id�õ�ĳ����Ʒ���͵���ϸ��Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я������������
	 */
	public void gettybyid(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));// ��Ʒ����id
		GoodType gt = gts.getGoodTypeById(id);// ��ȡ��Ʒ������Ϣ
		// request.setAttribute("goodtype", gt);
		out.print("<span>�������ƣ�</span><input type=\"text\" value=\"" + gt.getTypeName() + "\"/>");
		out.print("<br><span>��ע˵����</span><br><textarea rows=\"2\" cols=\"30\" name=\"remark\">" + gt.getRemark()
				+ "</textarea>");
	}

	/**
	 * ����id�༭��Ʒ������Ϣ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 *            ����Я�������Ĳ���
	 */
	public void editty(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String tyname = request.getParameter("tyname");
		String remark = request.getParameter("remark");
		GoodType gt = new GoodType(id, tyname, remark);
		int row = gts.editGoodType(gt);// �༭��Ʒ������Ϣ
		out.print(row);
	}
}
