package xm.chen.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xm.chen.dao.JSONUtil;
import xm.chen.dao.common;
import xm.chen.modal.Goods;
import xm.chen.modal.Images;
import xm.chen.modal.ImgType;
import xm.chen.service.GoodService;
import xm.chen.service.ImagesService;
import xm.chen.service.impl.GoodServiceImpl;
import xm.chen.service.impl.ImagesServiceImpl;

public class doImgServlet extends HttpServlet {
	GoodService gs = null;
	ImagesService idao = null;

	/**
	 * Constructor of the object.
	 */
	public doImgServlet() {
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
		if (type.equals("getgoodinfo")) {
			getgoodinfo(out, request);// 得到商品信息
		} else if (type.equals("imgsc")) {
			imgsc(out, request);// 得到图片
		} else if (type.equals("delImg")) {
			delImgById(out, request);// 删除图片
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
		// 创建图像操作对象
		idao = new ImagesServiceImpl();
	}

	/**
	 * 得到商品信息
	 * 
	 * @param out
	 * @param request
	 */
	public void getgoodinfo(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Goods good = gs.getGoodById(id);
		out.print("<label class=\"ty-title\">商品名称:</lebel>&nbsp;&nbsp&nbsp;&nbsp;<label>" + good.getGoodName()
				+ "</lebel>");
	}

	/**
	 * 删除图片
	 * 
	 * @param out
	 * @param request
	 */
	public void delImgById(PrintWriter out, HttpServletRequest request) {
		// 定义输出内容
		String info = "";
		// 获取上传路径
		String fileUpLoadPath = request.getSession().getServletContext().getRealPath("upload/");
		int id = Integer.parseInt(request.getParameter("id"));
		Images img = idao.getImgById(id);
		// 拼接文件路径
		fileUpLoadPath += "\\" + img.getImgName();
		// 先去upload文件夹中删除文件
		boolean falg = common.deleteFile(fileUpLoadPath);
		// 如果删除文件成功 就去删除数据库的数据
		int row = 0;
		if (falg) {
			row = idao.delImg(id);
		}
		out.print(row);
	}

	/**
	 * 图片上传
	 * 
	 * @param out
	 * @param request
	 */
	public void imgsc(PrintWriter out, HttpServletRequest request) {
		String uploadFileName = "";
		String filedname = "";
		int goodsid = 0;
		int x = 0;// 用于判断图片的主次
		// 检查类型是否为mulipart类型
		boolean mulipart = ServletFileUpload.isMultipartContent(request);
		// 设置上传路径
		String fileUploadPath = request.getSession().getServletContext().getRealPath("upload/");
		String s = "";// 保存信息
		if (mulipart) {// 判断是否为上传文件
			// 文件条目工厂对象
			FileItemFactory factory = new DiskFileItemFactory();
			// 通过工厂对象建立servlet上传对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// 通过上传对象获取所有文件条目
				List<FileItem> items = upload.parseRequest(request);
				// 转换为iterator集合
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					// 把每次循环获取的当前文件条目
					FileItem item = iter.next();
					if (item.isFormField()) {// 判断这个条目是不是字段
						filedname = item.getFieldName();
						if (filedname.equals("gid")) {
							goodsid = Integer.parseInt(item.getString("utf-8"));
						}
					} else {
						String filename = item.getName();
						if (filename != null && !filename.equals("") && goodsid != 0) {
							x++;
							// 把fileitem转换为file对象
							File lin = new File(filename);
							// 此处为了去除filename的路径部分
							String linname = lin.getName();
							// 为了确保上传的文件名不重复
							// 把上传的文件前面部分（不包含后缀名）+UUID随机的前10个字符+文件后缀名
							String type = linname.substring(linname.lastIndexOf('.'));
							String fname = linname.substring(0, linname.lastIndexOf('.'))
									+ common.getUUID().substring(0, 10) + type;
							// 设置图片主次
							int imgtype = ImgType.次要.ordinal();
							if (x == 1)
								imgtype = ImgType.主要.ordinal();
							// 把数据添加到数据库
							Images img = new Images(0, goodsid, fname, imgtype);
							int row = idao.addImg(img);
							// 当添加数据库成功后添加文件
							// 创建文件到指定位置fileUploadPath
							if (row > 0) {
								File save = new File(fileUploadPath, fname);
								// 把文件流写入文件
								item.write(save);
								// 获取保存的文件名
								uploadFileName = save.getName();
								s = "上传成功!";
							} else {
								s = "上传失败!";
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			String redicrt = "window.location.href='goods/imgEdit.jsp?id=" + goodsid + "';";
			// 弹出提示
			String ss = "<script type=\"text/javascript\">" + "alert('" + s + "');" + redicrt + "</script>";
			out.print(ss);
		}

	}
}
