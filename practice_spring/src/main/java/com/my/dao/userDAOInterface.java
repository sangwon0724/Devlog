package com.my.dao;

import com.my.vo.userVO;

public interface userDAOInterface {
	//login
	public userVO selectLogin(userVO vo) throws Exception;
	//update password
	public int updatePW(userVO vo) throws Exception;
}
