package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.vo.categoryVO;

@Repository
public class categoryDAO implements categoryDAOInterface {
	
	@Autowired
    private SqlSession sqlSession;

	@Override
	public List<categoryVO> selectCategoryList() throws Exception {
		return sqlSession.selectList("categoryMapper.selectCategoryList");
	}
	@Override
	public List<categoryVO> selectCategory(String userID) throws Exception {
		return sqlSession.selectList("categoryMapper.selectCategory",userID);
	}

	@Override
	public String selectCategoryOne(categoryVO vo) throws Exception {
		categoryVO temp=sqlSession.selectOne("categoryMapper.selectCategoryOne",vo);
		if(temp!=null) {return "overlap";}
		return "none";
	}
	
	@Override
	public int selectPostCount(String categoryName) throws Exception {
		return sqlSession.selectOne("categoryMapper.selectCount",categoryName);
	}

	@Override
	public void insertCategory(categoryVO vo) throws Exception {
		sqlSession.insert("categoryMapper.insertCategory", vo);
	}

	@Override
	public void moveUp(categoryVO vo) throws Exception {
		sqlSession.update("categoryMapper.moveUp", vo);
	}

	@Override
	public void moveDown(categoryVO vo) throws Exception {
		sqlSession.update("categoryMapper.moveDown", vo);
	}

	@Override
	public void countUp(categoryVO vo) throws Exception {
		sqlSession.update("categoryMapper.countUp", vo);
	}

	@Override
	public void countDown(categoryVO vo) throws Exception {
		sqlSession.update("categoryMapper.countDown", vo);
	}

	@Override
	public void deleteList(categoryVO vo) throws Exception {
		sqlSession.delete("categoryMapper.deleteList", vo);
	}
}
