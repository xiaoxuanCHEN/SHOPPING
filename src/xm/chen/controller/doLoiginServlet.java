package xm.chen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xm.chen.dao.JSONUtil;
import xm.chen.modal.User;
import xm.chen.service.UserService;
import xm.chen.service.impl.UserServiceImpl;

public class doLoiginServlet extends HttpServlet {
	UserService us = null;

	/**
	 * Constructor of the object.
	 */
	public doLoiginServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		// String phone = request.getParameter("phone");// 用户手机号(登录账号)
		// String pwd = request.getParameter("pwd");// 密码
		// User u = us.login(phone, pwd);
		// if (u != null && u.getId() != 0) {
		// HttpSession session = request.getSession();// 保存到会话
		// session.setAttribute("user", u);
		// response.sendRedirect("index.jsp");
		// } else {
		// response.sendRedirect("user/login.jsp");
		// }
		String type = request.getParameter("type");
		if (type.equals("login")) {
			login(out, request, response);
		}
		if (type.equals("uslist")) {
			userlist(out, request);
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
	 * 用户登录
	 * 
	 * @param out
	 * @param request
	 * @param response
	 */
	public void login(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");// 用户手机号(登录账号)
		String pwd = request.getParameter("pwd");// 密码
		User u = us.login(phone, pwd);
		if (u != null && u.getId() != 0) {
			HttpSession session = request.getSession();// 保存到会话
			session.setAttribute("user", u);
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendRedirect("user/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 后台用户信息管理
	 * 
	 * @param out
	 * @param request
	 */
	public void userlist(PrintWriter out, HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));// 页大小
		int page = Integer.parseInt(request.getParameter("page"));// 当前页
		int totalsize = us.Count("");// 数据条数
		int totalpage = (totalsize + (pagesize - 1)) / pagesize;// 计算总页数
		List<User> list = us.getuserPages(pagesize, page, "1=1", false);
		out.print(
				JSONUtil.toJsonString1(list) + ",{\"current\":\"" + page + "\",\"totalpages\":\"" + totalpage + "\"}]");
	}
}
