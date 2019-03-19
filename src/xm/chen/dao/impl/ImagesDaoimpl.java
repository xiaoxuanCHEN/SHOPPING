package xm.chen.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.ImagesDao;
import xm.chen.modal.Images;
import xm.chen.modal.ImgType;

public class ImagesDaoimpl implements ImagesDao {

	@Override
	public int addImg(Images img) {
		String sql = "insert into images values(?,?,?)";
		Object[] params = { img.getGoodid(), img.getImgName(), img.getImgType() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int delImg(int id) {
		String sql = "delete images where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public String[] goodsImg(int goodsid) {
		String s = "没有图片请上传";
		String Path = "../upload/";
		// 设定图片路径数组
		String[] strpath = { s, s, s, s, s, s, "0", "0", "0", "0", "0", "0" };
		String path = "upload/";
		List<Images> list = getImgByCondition("goodid='" + goodsid + "'");
		if (list != null && list.size() > 0) {
			int j = 0;
			int k = 6;
			for (Images img : list) {
				strpath[j] = Path + img.getImgName();
				strpath[k] = img.getId() + "";
				j++;
				k++;
			}
		}
		return strpath;
	}

	@Override
	public int goodImgTypeCount(int goodsid) {
		String sql = "select * from images where goodid=?";
		Object[] params = { goodsid };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] map = res.getRows();
		int row = map.length;
		return row;
	}

	@Override
	public List<Images> getImgByCondition(String Condition) {
		String sql = "select * from images where " + Condition;
		Result res = BaseDao.executeQuery(sql, null);
		List<Images> list = new ArrayList<Images>();
		Map[] map = res.getRows();
		for (Map map2 : map) {
			Images img = new Images();
			img.setId((Integer) map2.get("id"));
			img.setGoodid((Integer) map2.get("goodid"));
			img.setImgName(map2.get("imgName").toString());
			try {
				img.setImgType((Integer) map2.get("imgType"));
			} catch (Exception e) {

			}
			list.add(img);
		}
		return list;
	}

	@Override
	public Images getImgById(int id) {
		String sql = "select * from images where id=?";
		Object[] params = { id };
		Result res = BaseDao.executeQuery(sql, params);
		Map[] map = res.getRows();
		Images img = new Images();
		for (Map map2 : map) {
			img.setId((Integer) map2.get("id"));
			img.setGoodid((Integer) map2.get("goodid"));
			img.setImgName(map2.get("imgName").toString());
			try {
				img.setImgType((Integer) map2.get("imgType"));
			} catch (Exception e) {

			}
		}
		return img;
	}

	public static void main(String[] args) {
		ImagesDao imgdao = new ImagesDaoimpl();
		// Images img = new Images(0, 2, "1.jpg", null);
		// int row = imgdao.addImg(img);
		//List<Images> list = imgdao.getImgByCondition("goodid='4'");
		//for (Images images : list) {
		//	System.out.println(images.getImgName() + " " + images.getImgType());
		//}
		// String[] img = imgdao.goodsImg(4);
		// for (int i = 0; i < img.length; i++) {
		// System.out.println(img[i]);
		// }
		// Images img = imgdao.getImgById(41);
		// System.out.println(img.getImgName() + "" + img.getImgType());
		// int i = imgdao.goodImgTypeCount(2);
		// System.out.println(i);
	}
}
