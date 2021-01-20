package com.my.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.vo.testVO;

@Repository
public class testDAO implements testDAOInterFace {
	
	@Autowired
    private SqlSession sqlSession;
	
    //private static final String Namespace = "studyMapper";
	//namespace+".selectTest"
	
	//목록 출력
	@Override
	public List<testVO> selectTest() throws Exception {
		return sqlSession.selectList("studyMapper.selectTest");
	}

	//한 개만 출력
	@Override
	public testVO selectOneTest(String str) throws Exception {
		return sqlSession.selectOne("studyMapper.selectOneTest",str);
	}
	
	//삽입
	@Override
	public void insertTest(testVO vo) throws Exception {
		sqlSession.insert("studyMapper.insertTest", vo);
		//sqlSession.commit();
	}

	@Override
	public void updateTest(testVO vo) throws Exception {
		sqlSession.update("studyMapper.updateTest", vo);
	}
}
