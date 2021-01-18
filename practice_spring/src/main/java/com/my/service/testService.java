package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.my.dao.testDAO;
import com.my.vo.testVO;

@Service
public class testService implements testServiceInterface {

	@Autowired
    private testDAO dao;
	
	@Override
	public List<testVO> selectTest() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTest();
	}

}
