package com.my.vo;

public class categoryVO {
	private String categoryName;
	private String categoryNameKor;
	private int orderNo;
	private String userID;
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setCategoryNameKor(String categoryNameKor) {
		this.categoryNameKor = categoryNameKor;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public String getCategoryNameKor() {
		return categoryNameKor;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
