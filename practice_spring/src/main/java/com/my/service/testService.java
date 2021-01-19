package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.my.dao.testDAO;
import com.my.dao.testDAOInterFace;
import com.my.vo.testVO;

@Service
public class testService implements testServiceInterface {

	@Autowired
    private testDAOInterFace dao;
	
	@Override
	public List<testVO> selectTest() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTest();
	}

	@Override
	public void insertTest(testVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("확인점 2-1 : "+vo);
		System.out.println("확인점 2-2 : "+vo.getTest());
		//int i=dao.insertTest(vo);
		//System.out.println("dao 실행 결과 : "+i);
		//return dao.insertTest(vo);
		dao.insertTest(vo);
	//	return 1;
	}

}
