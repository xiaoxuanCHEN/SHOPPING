package xm.chen.modal;

public class GoodType {
	private int id;
	/**
	 * 商品类型
	 */
	private String typeName;
	/**
	 * 备注
	 */
	private String remark;

	public GoodType() {
	}

	public GoodType(int id, String typeName, String remark) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
