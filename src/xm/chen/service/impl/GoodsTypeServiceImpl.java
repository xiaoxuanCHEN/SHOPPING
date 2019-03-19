package xm.chen.service.impl;

import java.util.List;

import xm.chen.dao.GoodTypeDao;
import xm.chen.dao.impl.GoodTypeDaoimpl;
import xm.chen.modal.GoodType;
import xm.chen.service.GoodTypeService;

public class GoodsTypeServiceImpl implements GoodTypeService {
	private GoodTypeDao gtdao;

	@Override
	public int addGoodType(GoodType gt) {
		gtdao = new GoodTypeDaoimpl();
		return gtdao.addGoodType(gt);
	}

	@Override
	public int editGoodType(GoodType gt) {
		gtdao = new GoodTypeDaoimpl();
		return gtdao.editGoodType(gt);
	}

	@Override
	public int delGoodType(int id) {
		gtdao = new GoodTypeDaoimpl();
		int row = gtdao.delGoodType(id);
		return row;
	}

	@Override
	public List<GoodType> getGoodType(String Condition) {
		gtdao = new GoodTypeDaoimpl();
		List<GoodType> list = gtdao.getGoodType(Condition);
		return list;
	}

	@Override
	public GoodType getGoodTypeById(int id) {
		gtdao = new GoodTypeDaoimpl();
		GoodType gt = gtdao.getGoodTypeById(id);
		return gt;
	}

	@Override
	public int getcount(String condition) {
		gtdao = new GoodTypeDaoimpl();
		int row = gtdao.getcount(condition);
		return row;
	}

}
