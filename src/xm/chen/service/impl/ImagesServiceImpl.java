package xm.chen.service.impl;

import java.util.List;

import xm.chen.dao.ImagesDao;
import xm.chen.dao.impl.ImagesDaoimpl;
import xm.chen.modal.Images;
import xm.chen.modal.ImgType;
import xm.chen.service.ImagesService;

public class ImagesServiceImpl implements ImagesService {
	ImagesDao Idao = null;

	@Override
	public int addImg(Images img) {
		Idao = new ImagesDaoimpl();
		int row = Idao.addImg(img);
		return row;
	}

	@Override
	public String[] goodsImg(int goodsid) {
		Idao = new ImagesDaoimpl();
		String[] path = Idao.goodsImg(goodsid);
		return path;
	}

	@Override
	public int goodImgTypeCount(int goodsid) {
		Idao = new ImagesDaoimpl();
		return Idao.goodImgTypeCount(goodsid);
	}

	@Override
	public List<Images> getImgByCondition(String Condition) {
		Idao = new ImagesDaoimpl();
		List<Images> list = Idao.getImgByCondition(Condition);
		return list;
	}

	@Override
	public int delImg(int id) {
		Idao = new ImagesDaoimpl();
		int row = Idao.delImg(id);
		return row;
	}

	@Override
	public Images getImgById(int id) {
		Idao = new ImagesDaoimpl();
		Images img = Idao.getImgById(id);
		return img;
	}

}
