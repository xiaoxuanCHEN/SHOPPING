package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpRetryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xm.chen.modal.User;
import xm.chen.service.UserService;
import xm.chen.service.impl.UserServiceImpl;

public class doEditUserServlet extends HttpServlet {
	UserService us = null;

	/**
	 * Constructor of the object.
	 */
	public doEditUserServlet() {
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
		if (type.equals("editUser")) {
			editUser(out, request);// �༭�û�����
		} else if (type.equals("recharge")) {
			recharge(out, request);// �û���ֵ
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
		us = new UserServiceImpl();
	}

	/**
	 * �û���Ϣ�޸�
	 * 
	 * @param out
	 * @param request
	 */
	public void editUser(PrintWriter out, HttpServletRequest request) {
		String info = "";// �趨��Ӧ��Ϣ
		String redirect = "";// �趨��ת��ַ
		String pwd = request.getParameter("pwd");// ����
		String address = request.getParameter("address");// ��ַ
		String sex = request.getParameter("sex");// �Ա�
		User u = (User) request.getSession().getAttribute("user");// �û�ʵ��
		User u1 = new User(u.getId(), u.getRealname(), u.getPhone(), pwd, u.getTranspwd(), u.getBalance(), address, sex,
				u.getRole());
		int row = us.updateUser(u1);// �޸�����
		if (row > 0) {
			info = "�޸ĳɹ�!";
			request.getSession().invalidate();
			redirect = "window.location.href='user/login.jsp';";
		} else {
			info = "�޸�ʧ��!";
			redirect = "window.location.href='user/edituser.jsp';";
		}
		String s = "<script type=\"text/javascript\"> " + "alert('" + info + "');" + redirect + "</script>";
		out.print(s);
	}

	/**
	 * �û���ֵ
	 * 
	 * @param out
	 *            �������
	 * @param request
	 */
	public void recharge(PrintWriter out, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");// �û�ʵ��
		BigDecimal money = new BigDecimal(request.getParameter("money"));// ��ֵ���
		String pwd = request.getParameter("pwd");// ��������
		int row = us.recharge(money, pwd, u);// ��ֵ
		BigDecimal balance = u.getBalance();// �˻����
		out.print(row + "," + balance);
	}
}
