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
			getgoodinfo(out, request);// �õ���Ʒ��Ϣ
		} else if (type.equals("imgsc")) {
			imgsc(out, request);// �õ�ͼƬ
		} else if (type.equals("delImg")) {
			delImgById(out, request);// ɾ��ͼƬ
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
		// ����ͼ���������
		idao = new ImagesServiceImpl();
	}

	/**
	 * �õ���Ʒ��Ϣ
	 * 
	 * @param out
	 * @param request
	 */
	public void getgoodinfo(PrintWriter out, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Goods good = gs.getGoodById(id);
		out.print("<label class=\"ty-title\">��Ʒ����:</lebel>&nbsp;&nbsp&nbsp;&nbsp;<label>" + good.getGoodName()
				+ "</lebel>");
	}

	/**
	 * ɾ��ͼƬ
	 * 
	 * @param out
	 * @param request
	 */
	public void delImgById(PrintWriter out, HttpServletRequest request) {
		// �����������
		String info = "";
		// ��ȡ�ϴ�·��
		String fileUpLoadPath = request.getSession().getServletContext().getRealPath("upload/");
		int id = Integer.parseInt(request.getParameter("id"));
		Images img = idao.getImgById(id);
		// ƴ���ļ�·��
		fileUpLoadPath += "\\" + img.getImgName();
		// ��ȥupload�ļ�����ɾ���ļ�
		boolean falg = common.deleteFile(fileUpLoadPath);
		// ���ɾ���ļ��ɹ� ��ȥɾ�����ݿ������
		int row = 0;
		if (falg) {
			row = idao.delImg(id);
		}
		out.print(row);
	}

	/**
	 * ͼƬ�ϴ�
	 * 
	 * @param out
	 * @param request
	 */
	public void imgsc(PrintWriter out, HttpServletRequest request) {
		String uploadFileName = "";
		String filedname = "";
		int goodsid = 0;
		int x = 0;// �����ж�ͼƬ������
		// ��������Ƿ�Ϊmulipart����
		boolean mulipart = ServletFileUpload.isMultipartContent(request);
		// �����ϴ�·��
		String fileUploadPath = request.getSession().getServletContext().getRealPath("upload/");
		String s = "";// ������Ϣ
		if (mulipart) {// �ж��Ƿ�Ϊ�ϴ��ļ�
			// �ļ���Ŀ��������
			FileItemFactory factory = new DiskFileItemFactory();
			// ͨ������������servlet�ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// ͨ���ϴ������ȡ�����ļ���Ŀ
				List<FileItem> items = upload.parseRequest(request);
				// ת��Ϊiterator����
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					// ��ÿ��ѭ����ȡ�ĵ�ǰ�ļ���Ŀ
					FileItem item = iter.next();
					if (item.isFormField()) {// �ж������Ŀ�ǲ����ֶ�
						filedname = item.getFieldName();
						if (filedname.equals("gid")) {
							goodsid = Integer.parseInt(item.getString("utf-8"));
						}
					} else {
						String filename = item.getName();
						if (filename != null && !filename.equals("") && goodsid != 0) {
							x++;
							// ��fileitemת��Ϊfile����
							File lin = new File(filename);
							// �˴�Ϊ��ȥ��filename��·������
							String linname = lin.getName();
							// Ϊ��ȷ���ϴ����ļ������ظ�
							// ���ϴ����ļ�ǰ�沿�֣���������׺����+UUID�����ǰ10���ַ�+�ļ���׺��
							String type = linname.substring(linname.lastIndexOf('.'));
							String fname = linname.substring(0, linname.lastIndexOf('.'))
									+ common.getUUID().substring(0, 10) + type;
							// ����ͼƬ����
							int imgtype = ImgType.��Ҫ.ordinal();
							if (x == 1)
								imgtype = ImgType.��Ҫ.ordinal();
							// ��������ӵ����ݿ�
							Images img = new Images(0, goodsid, fname, imgtype);
							int row = idao.addImg(img);
							// ��������ݿ�ɹ�������ļ�
							// �����ļ���ָ��λ��fileUploadPath
							if (row > 0) {
								File save = new File(fileUploadPath, fname);
								// ���ļ���д���ļ�
								item.write(save);
								// ��ȡ������ļ���
								uploadFileName = save.getName();
								s = "�ϴ��ɹ�!";
							} else {
								s = "�ϴ�ʧ��!";
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			String redicrt = "window.location.href='goods/imgEdit.jsp?id=" + goodsid + "';";
			// ������ʾ
			String ss = "<script type=\"text/javascript\">" + "alert('" + s + "');" + redicrt + "</script>";
			out.print(ss);
		}

	}
}
