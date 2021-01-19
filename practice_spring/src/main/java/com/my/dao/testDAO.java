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
		//Mapper 파일의 namespace명 + SQL 구문의 id명
		return sqlSession.selectList("studyMapper.selectTest");
	}
	
	//삽입
	@Override
	public void insertTest(testVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("확인점 3-1 : "+vo);
		System.out.println("확인점 3-2 : "+vo.getTest());
		//return sqlSession.insert("studyMapper.insertTest", vo);
		sqlSession.insert("studyMapper.insertTest", vo);
	}

}
