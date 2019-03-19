package xm.chen.modal;

import java.math.BigDecimal;

public class Goods {
	private int id;
	/**
	 * ��Ʒ���ͱ��
	 */
	private int typeid;
	/**
	 * ��Ʒ����
	 */
	private String goodName;
	/**
	 * �۸�
	 */
	private BigDecimal price;
	/**
	 * ����
	 */
	private String config;
	/**
	 * ��������
	 */
	private String productDate;
	/**
	 * ����
	 */
	private int count;
	/**
	 * ����
	 */
	private String introduction;
	/**
	 * ����
	 */
	private String description;

	public Goods() {
	}

	public Goods(int id, int typeid, String goodName, BigDecimal price, String config, String productDate, int count,
			String introduction, String description) {
		super();
		this.id = id;
		this.typeid = typeid;
		this.goodName = goodName;
		this.price = price;
		this.config = config;
		this.productDate = productDate;
		this.count = count;
		this.introduction = introduction;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
