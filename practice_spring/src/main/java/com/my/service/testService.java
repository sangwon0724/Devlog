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
	
	//전체 출력
	@Override
	public List<testVO> selectTest() throws Exception {
		return dao.selectTest();
	}

	//1개만 출력
	@Override
	public testVO selectOneTest(String str) throws Exception {
		return dao.selectOneTest(str);
	}
	
	//삽입
	@Override
	public void insertTest(testVO vo) throws Exception {
		dao.insertTest(vo);
	}

	//수정
	@Override
	public void updateTest(testVO vo) throws Exception {
		dao.updateTest(vo);
	}

	//삭제
	@Override
	public void deleteTest(testVO vo) throws Exception {
		dao.deleteTest(vo);
	}
}
