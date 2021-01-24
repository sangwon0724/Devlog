package com.my.service;

import javax.servlet.http.HttpSession;

import com.my.vo.userVO;

public interface userServiceInterface {
	//login
	public userVO selectLogin(userVO vo) throws Exception;
	//update password
	public int updatePW(userVO vo) throws Exception;
	//logout
	public void logout(HttpSession session) throws Exception;
}
