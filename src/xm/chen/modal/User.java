package xm.chen.modal;

import java.math.BigDecimal;

public class User {
	private int id;
	/**
	 * ��ʵ����
	 */
	private String realname;
	/**
	 * �ֻ���
	 */
	private String phone;
	/**
	 * ����
	 */
	private String pwd;
	/**
	 * ��������
	 */
	private String transpwd;
	/**
	 * �˻����
	 */
	private BigDecimal balance;
	/**
	 * סַ
	 */
	private String address;
	/**
	 * �Ա�
	 */
	private String sex;
	/**
	 * ��·Ա/�û�
	 */
	private int role;

	public User() {
	}

	public User(int id, String realname, String phone, String pwd, String transpwd, BigDecimal balance, String address,
			String sex, int role) {
		super();
		this.id = id;
		this.realname = realname;
		this.phone = phone;
		this.pwd = pwd;
		this.transpwd = transpwd;
		this.balance = balance;
		this.address = address;
		this.sex = sex;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTranspwd() {
		return transpwd;
	}

	public void setTranspwd(String transpwd) {
		this.transpwd = transpwd;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
