package com.my.service;

import java.util.List;

import com.my.vo.testVO;

public interface testServiceInterface {
	public List<testVO> selectTest() throws Exception;
	public void insertTest(testVO vo) throws Exception;
}
