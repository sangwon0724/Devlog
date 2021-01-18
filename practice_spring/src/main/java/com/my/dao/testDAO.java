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
	
	// 상품 목록 출력
	@Override
	public List<testVO> selectTest() throws Exception {
		//Mapper 파일의 namespace명 + SQL 구문의 id명
		return sqlSession.selectList("studyMapper.selectTest");
	}

}
