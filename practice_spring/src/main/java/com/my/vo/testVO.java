package com.my.vo;

public class testVO {
	private int no;
	private String test;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	//생성자 - insert시 사용
	public testVO(String test) {
		this.test=test;
	}
	
	//생성자 - select시 사용
	public testVO(int no, String test) {
		this.no=no;
		this.test=test;
	}
}
