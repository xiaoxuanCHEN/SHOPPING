package xm.chen.service.impl;

import java.util.List;

import xm.chen.dao.myCartDao;
import xm.chen.dao.impl.myCartDaoimpl;
import xm.chen.modal.MyCart;
import xm.chen.service.mycartService;

public class mycartServiceIpml implements mycartService {
	private myCartDao cdao;

	@Override
	public int addcart(MyCart cart) {
		cdao = new myCartDaoimpl();
		int row = cdao.addcart(cart);
		return row;
	}

	@Override
	public int updatecart(MyCart cart) {
		cdao = new myCartDaoimpl();
		int row = cdao.updatecart(cart);
		return row;
	}

	@Override
	public List<MyCart> getAllCart(int uid) {
		cdao = new myCartDaoimpl();
		List<MyCart> list = cdao.getAllCart(uid);
		return list;
	}

	@Override
	public List<MyCart> getAllCartByCondtion(String condition) {
		cdao = new myCartDaoimpl();
		List<MyCart> list = cdao.getAllCartByCondtion(condition);
		return list;
	}

	@Override
	public List<MyCart> getCartBypage(int pagesize, int page, String condition, boolean order) {
		cdao = new myCartDaoimpl();
		List<MyCart> list = cdao.getCartBypage(pagesize, page, condition, order);
		return list;
	}

	@Override
	public int delcart(int gid) {
		cdao = new myCartDaoimpl();
		int row = cdao.delcart(gid);
		return row;
	}

	@Override
	public int delcart1(int uid) {
		cdao = new myCartDaoimpl();
		int row = cdao.delcart1(uid);
		return row;
	}

	@Override
	public MyCart getCart(int id) {
		cdao = new myCartDaoimpl();
		MyCart cart = cdao.getCart(id);
		return cart;
	}

	@Override
	public int delcart2(int id) {
		cdao = new myCartDaoimpl();
		int row = cdao.delcart2(id);
		return row;
	}

}
