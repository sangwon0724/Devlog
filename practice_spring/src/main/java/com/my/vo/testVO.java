package com.my.vo;

public class testVO {
	private int no;
	private String test;
	private String note;
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
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	//생성자 - insert시 사용
	public testVO(String test, String note) {
		this.test=test;
		this.note=note;
	}
	
	//생성자 - delete시 사용
	public testVO(int no) {
		this.no=no;
	}
	
	//생성자 - select시 사용
	public testVO(int no, String test, String note) {
		this.no=no;
		this.test=test;
		this.note=note;
	}
}
