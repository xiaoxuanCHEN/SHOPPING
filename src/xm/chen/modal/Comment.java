package xm.chen.modal;

public class Comment {
	/**
	 * 评论id
	 */
	private int id;
	/**
	 * 用户id
	 */
	private int uid;
	/**
	 * 订单id
	 */
	private int orderid;
	/**
	 * 评论时间
	 */
	private String commentTime;
	/**
	 * 评论内容
	 */
	private String comments;
	/**
	 * 评论状态(很不满意/不满意/一般/满意/非常满意)
	 */
	private int commentStatus;

	OrderDetail orderdetail;

	public OrderDetail getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(OrderDetail orderdetail) {
		this.orderdetail = orderdetail;
	}

	public Comment() {
	}

	public Comment(int id, int uid, int orderid, String commentTime, String comments, int commentStatus) {
		super();
		this.id = id;
		this.uid = uid;
		this.orderid = orderid;
		this.commentTime = commentTime;
		this.comments = comments;
		this.commentStatus = commentStatus;
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

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}
}
