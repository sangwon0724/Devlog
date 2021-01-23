package com.my.app;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.service.categoryServiceInterface;
import com.my.service.userServiceInterface;
import com.my.vo.UserVO;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private userServiceInterface userService;
	
	@Inject
	private categoryServiceInterface categoryService;
	
	//할 일 목록
	//1. 로그인 구현
	//2. 비밀번호 변경 구현
	//3. 카테고리 추가 기능
	//4. 카테고리 삭제 기능
	//5. 카테고리 순서 변경 기능
	
	//login-get
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getLogin() throws Exception {
		System.out.println("start login from user - method : get");
	}
		
	//login-post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(UserVO vo, Model model) throws Exception {
		System.out.println("start login from user - method : post");
				
		//service.selectOneTest(no);
		
		return "redirect:/";
	}
	
	//mypage-get
	@RequestMapping(value = "/mypage/mypage", method = RequestMethod.GET)
	public void getMypage() throws Exception {
		System.out.println("start login from user/mypage - method : get");
	}
	
	//changePW-get
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.GET)
	public void getChangePW() throws Exception {
		System.out.println("start login from user/changePW - method : get");
	}
	
	//changePW-post, sql - update
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.POST)
	public String postChangePW() throws Exception {
		System.out.println("start login from user/changePW - method : post");
		
		return "redirect:/mypage/changePW";
	}
	
	//categoryList-get
	@RequestMapping(value = "/mypage/categoryList", method = RequestMethod.GET)
	public void getCategoryList() throws Exception {
		System.out.println("start login from user/categoryList - method : get");
	}
	
	//categoryListDelete_-get, sql - delete
	@RequestMapping(value = "/mypage/categoryList/delete_{orderNo}", method = RequestMethod.GET)
	public String getCategoryListDelete(@PathVariable int orderNo) throws Exception {
		System.out.println("start login from user/categoryListDelete - method : get");
		System.out.println("삭제 번호 : "+orderNo);
		
		return "redirect:/mypage/categoryList";
	}
	
	//categoryAdd-get
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.GET)
	public void getCategoryAdd() throws Exception {
		System.out.println("start login from user/categoryAdd - method : get");
	}
	
	//categoryAdd-post, sql - insert
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.POST)
	public String postCategoryAdd() throws Exception {
		System.out.println("start login from user/categoryAdd - method : post");
		
		return "redirect:/mypage/categoryList";
	}
}
