package com.my.dao;

import java.util.List;

import com.my.vo.categoryVO;

public interface categoryDAOInterface {
	public List<categoryVO> selectCategoryList() throws Exception;
	public List<categoryVO> selectCategory(String userID) throws Exception;
	public String selectCategoryOne(categoryVO vo) throws Exception;
	public categoryVO selectCategoryInfo(String categoryName) throws Exception;
	public void insertCategory(categoryVO vo) throws Exception;
	public void moveUp(categoryVO vo) throws Exception;
	public void moveDown(categoryVO vo) throws Exception;
	public void countUp(categoryVO vo) throws Exception;
	public void countDown(categoryVO vo) throws Exception;
	public void deleteList(categoryVO vo) throws Exception;
}
