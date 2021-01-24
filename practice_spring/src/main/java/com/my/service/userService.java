package com.my.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.userDAOInterface;
import com.my.vo.userVO;

@Service
public class userService implements userServiceInterface {
	@Autowired
    private userDAOInterface dao;
	
	//login
	@Override
	public userVO selectLogin(userVO vo) throws Exception {
		return dao.selectLogin(vo);
	}

	//change password
	@Override
	public int updatePW(userVO vo) throws Exception {
		return dao.updatePW(vo);
	}

	//logout
	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
	}

}
