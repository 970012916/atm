package user;

import java.util.Date;

public class User {
	
	private int cardNum;
	private int balanece;
	private Date createTime;
	private Date modifyTime;
	
	
	public int getCarNum() {
		return cardNum;
	}
	public void setCarNum(int carNum) {
		this.cardNum = carNum;
	}
	
	public int getBalanece() {
		return balanece;
	}
	public void setBalanece(int balanece) {
		this.balanece = balanece;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
	
}
