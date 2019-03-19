package xm.chen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import xm.chen.dao.GoodTypeDao;
import xm.chen.modal.GoodType;

public class GoodTypeDaoimpl implements GoodTypeDao {

	@Override
	public int addGoodType(GoodType gt) {
		String sql = "insert into goodtype values(?,?)";
		Object[] params = { gt.getTypeName(), gt.getRemark() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int editGoodType(GoodType gt) {
		String sql = "update goodtype set typename=?,remark=? where id=?";
		Object[] params = { gt.getTypeName(), gt.getRemark(), gt.getId() };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public int delGoodType(int id) {
		String sql = "delete goodtype where id=?";
		Object[] params = { id };
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	@Override
	public List<GoodType> getGoodType(String Condition) {
		String sql = "select * from goodtype where " + Condition;
		Result res = BaseDao.executeQuery(sql, null);
		List<GoodType> list = new ArrayList<GoodType>();
		Map[] map = res.getRows();
		for (Map map2 : map) {
			GoodType gt = new GoodType();
			gt.setId((Integer) map2.get("id"));
			gt.setTypeName(map2.get("typeName").toString());
			gt.setRemark(map2.get("remark").toString());
			list.add(gt);
		}
		return list;
	}

	@Override
	public GoodType getGoodTypeById(int id) {
		String sql = "select * from goodtype where id=" + id;
		Result res = BaseDao.executeQuery(sql, null);
		Map[] map = res.getRows();
		GoodType gt = new GoodType();
		for (Map map2 : map) {
			gt.setId((Integer) map2.get("id"));
			gt.setTypeName(map2.get("typeName").toString());
			gt.setRemark(map2.get("remark").toString());
		}
		return gt;
	}

	public static void main(String[] args) {
		// GoodTypeDao gtdao = new GoodTypeDaoimpl();
		// GoodType gt = new GoodType(1, "小米手机", "为发烧而生,你值得拥有!");
		// int row = gtdao.editGoodType(gt);
		// String goodtype = "小米手机";
		// List<GoodType> list = gtdao.getGoodType("typename='" + goodtype + "
		// '");
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i).getTypeName() + " " +
		// list.get(i).getRemark());
		// }
		// GoodType gt = new GoodType(0, "小米电视", "曲屏看电视更爽!");
		// int row = gtdao.addGoodType(gt);
		// int row = gtdao.delGoodType(2);
		// int row=gtdao.getcount("");
		// System.out.println(row);
	}

	@Override
	public int getcount(String condition) {
		int i = 0;// 默认值为0
		String sql = "select count(*) row from goodtype";
		if (!condition.isEmpty())
			sql += " " + condition;
		Result rs = BaseDao.executeQuery(sql, null);
		Map[] maps = rs.getRows();
		if (maps != null && maps.length > 0)
			i = (Integer) maps[0].get("row");
		return i;
	}
}
