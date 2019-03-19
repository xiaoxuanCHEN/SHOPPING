package xm.chen.modal;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Orders {
	/**
	 * ����id
	 */
	private int id;
	/**
	 * �û�id
	 */
	private int uid;
	/**
	 * �µ�ʱ��
	 */
	private Timestamp orderTime;
	/**
	 * ������
	 */
	private BigDecimal totalmoney;
	
	private String Time;
	
	private  BigDecimal zje;
	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public BigDecimal getZje() {
		return zje;
	}

	public void setZje(BigDecimal zje) {
		this.zje = zje;
	}

	/**
	 * ����״̬
	 */
	private int orderstatus;

	public Orders() {
	}

	public Orders(int id, int uid, Timestamp orderTime, BigDecimal totalmoney, int orderstatus) {
		super();
		this.id = id;
		this.uid = uid;
		this.orderTime = orderTime;
		this.totalmoney = totalmoney;
		this.orderstatus = orderstatus;
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

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public BigDecimal getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(BigDecimal totalmoney) {
		this.totalmoney = totalmoney;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

}
