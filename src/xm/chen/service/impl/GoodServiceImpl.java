package xm.chen.service.impl;

import java.util.List;

import xm.chen.dao.GoodDao;
import xm.chen.dao.impl.GoodDaoimpl;
import xm.chen.modal.Goods;
import xm.chen.service.GoodService;

public class GoodServiceImpl implements GoodService {
	private GoodDao gdao;

	public GoodServiceImpl() {
		gdao = new GoodDaoimpl();
	}

	@Override
	public int addGood(Goods good) {
		int row = gdao.addGood(good);
		return row;
	}

	@Override
	public int updateGood(Goods good) {
		int row = gdao.updateGood(good);
		return row;
	}

	@Override
	public Goods getGoodById(int id) {
		Goods good = gdao.getGoodById(id);
		return good;
	}

	@Override
	public List<Goods> getGoodByCondition(String Condition) {
		List<Goods> list = gdao.getGoodByCondition(Condition);
		return list;
	}

	@Override
	public int deleteGood(int id) {
		int row = gdao.deleteGood(id);
		return row;
	}

	@Override
	public List<Goods> getByGoodPage(int pagesize, int page, String condition, boolean order) {
		List<Goods> list = gdao.getByGoodPage(pagesize, page, condition, order);
		return list;
	}

	@Override
	public List<Goods> getGoodByKey(String key, int n) {
		try {
			if (!key.isEmpty()) {
				List<Goods> list = gdao.getGoods("where goodname like '%" + key + "%'", n);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int Count(String condition) {
		int row = gdao.Count(condition);
		return row;
	}
}
