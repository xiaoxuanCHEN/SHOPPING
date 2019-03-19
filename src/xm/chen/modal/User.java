package xm.chen.modal;

import java.math.BigDecimal;

public class User {
	private int id;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 交易密码
	 */
	private String transpwd;
	/**
	 * 账户金额
	 */
	private BigDecimal balance;
	/**
	 * 住址
	 */
	private String address;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 管路员/用户
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
