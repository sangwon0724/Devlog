package com.my.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.vo.userVO;

@Repository
public class userDAO implements userDAOInterface {
	@Autowired
    private SqlSession sqlSession;

	@Override
	public userVO selectLogin(userVO vo) throws Exception {
		return sqlSession.selectOne("userMapper.selectLogin",vo);
	}

	@Override
	public int updatePW(userVO vo) throws Exception {
		return sqlSession.update("userMapper.updatePW",vo);
	}
	
	
}
