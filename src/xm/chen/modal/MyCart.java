package xm.chen.modal;

public class MyCart {
	/**
	 * 购物车id
	 */
	private int id;
	/**
	 * 用户id
	 */
	private int uid;
	/**
	 * 商品id
	 */
	private int goodid;
	/**
	 * 数量
	 */
	private int count;

	public MyCart() {
	}

	public MyCart(int id, int uid, int goodid, int count) {
		super();
		this.id = id;
		this.uid = uid;
		this.goodid = goodid;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGoodid() {
		return goodid;
	}

	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
