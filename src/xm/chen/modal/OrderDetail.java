package xm.chen.modal;

public class OrderDetail {
	/**
	 * 订单详情id
	 */
	private int id;
	/**
	 * 订单id
	 */
	private int orderid;
	/**
	 * 商品id
	 */
	private int goodid;
	/**
	 * 商品数量
	 */
	private int count;

	public OrderDetail() {
	}

	public OrderDetail(int id, int orderid, int goodid, int count) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.goodid = goodid;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
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
