package xm.chen.modal;

public class Images {
	private int id;
	private int goodid;
	private String imgName;
	private int imgType;

	public Images() {
	}

	public Images(int id, int goodid, String imgName, int imgType) {
		super();
		this.id = id;
		this.goodid = goodid;
		this.imgName = imgName;
		this.imgType = imgType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodid() {
		return goodid;
	}

	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public int getImgType() {
		return imgType;
	}

	public void setImgType(int imgType) {
		this.imgType = imgType;
	}
}
