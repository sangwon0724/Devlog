package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.categoryDAOInterface;
import com.my.vo.categoryVO;

@Service
public class categoryService implements categoryServiceInterface {
	@Autowired
    private categoryDAOInterface dao;
	
	@Override
	public List<categoryVO> selectCategoryList() throws Exception {
		return dao.selectCategoryList();
	}
	
	@Override
	public List<categoryVO> selectCategory(String userID) throws Exception {
		return dao.selectCategory(userID);
	}

	@Override
	public String selectCategoryOne(categoryVO vo) throws Exception {
		return dao.selectCategoryOne(vo);
	}
	
	@Override
	public categoryVO selectCategoryInfo(String categoryName) throws Exception {
		return dao.selectCategoryInfo(categoryName);
	}

	@Override
	public void insertCategory(categoryVO vo) throws Exception {
		dao.insertCategory(vo);
	}

	@Override
	public void moveUp(categoryVO vo) throws Exception {
		dao.moveUp(vo);
	}

	@Override
	public void moveDown(categoryVO vo) throws Exception {
		dao.moveDown(vo);
	}

	@Override
	public void countUp(categoryVO vo) throws Exception {
		dao.countUp(vo);
	}

	@Override
	public void countDown(categoryVO vo) throws Exception {
		dao.countDown(vo);
	}

	@Override
	public void deleteList(categoryVO vo) throws Exception {
		dao.deleteList(vo);
	}
}
