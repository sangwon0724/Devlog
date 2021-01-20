package com.my.dao;

import java.util.List;

import com.my.vo.testVO;

public interface testDAOInterFace {
	public List<testVO> selectTest() throws Exception;
	public testVO selectOneTest(String str) throws Exception;
	public void insertTest(testVO vo) throws Exception;
	public void updateTest(testVO vo) throws Exception;
	public void deleteTest(testVO vo) throws Exception;
}
