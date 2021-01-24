package com.my.app;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.service.categoryServiceInterface;
import com.my.service.userServiceInterface;
import com.my.vo.userVO;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private userServiceInterface service;
	
	@Inject
	private categoryServiceInterface categoryService;
	
	//할 일 목록
	//3. 비밀번호 변경 구현
	//4. 카테고리 추가 기능
	//5. 카테고리 삭제 기능
	//6. 카테고리 순서 변경 기능
	
	//완료한 일 목록
	//1. 로그인 기능 구현
	//2. 직접 경로로 로그인 필요 서비스에 이동 시 로그인 페이지로 유도 => url이 직접 노출되는 일부의 get 방식의 함수에 적용 (login이나 logout같은 url이 보여도 크게 상관없으면 적용 안함)
	
	//login-get
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getLogin() throws Exception {
		System.out.println("start login from user - method : get");
	}
		
	//login-post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(userVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user - method : post");
				
		userVO result=service.selectLogin(vo);
		HttpSession session = request.getSession();
		
		if(result != null) {
			session.setAttribute("user", result);
		}
		else {
			rttr.addFlashAttribute("result", "fail"); //url에 안 나옴, 세션을 통해서 전달, jsp에서 비교시에는 표현은 ''로 해도 String으로 비교하기 때문에 값을 String으로 추가해 줘야 한다.
			return "redirect:/user/login";
		}
		
		return "redirect:/";
	}
	
	//logout-get
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogout(HttpSession session) throws Exception {
		System.out.println("start logout from user - method : get");
		
		service.logout(session);
	   
		return "redirect:/";
	}
	
	//mypage-get, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/mypage", method = RequestMethod.GET)
	public String getMypage(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/mypage - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		return "/user/mypage/mypage";
	}
	
	//changePW-get, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.GET)
	public String getChangePW(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/changePW - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		return "/user/mypage/changePW";
	}
	
	//changePW-post, sql - update
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.POST)
	public String postChangePW() throws Exception {
		System.out.println("start login from user/changePW - method : post");
		
		return "redirect:/mypage/changePW";
	}
	
	//categoryList-get, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryList", method = RequestMethod.GET)
	public String getCategoryList(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/categoryList - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		return "/user/mypage/categoryList";
	}
	
	//categoryView-get, iframe용, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryView", method = RequestMethod.GET)
	public String getCategoryView(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/categoryView - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		return "/user/mypage/categoryView";
	}
	
	//categoryListDelete_-get, sql - delete, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryList/delete_{orderNo}", method = RequestMethod.GET)
	public String getCategoryListDelete(@PathVariable int orderNo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/categoryListDelete - method : get");
		System.out.println("삭제 번호 : "+orderNo);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		return "redirect:/mypage/categoryList";
	}
	
	//categoryAdd-get, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.GET)
	public String getCategoryAdd(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/categoryAdd - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		return "/user/mypage/categoryAdd";
	}
	
	//categoryAdd-post, sql - insert
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.POST)
	public String postCategoryAdd() throws Exception {
		System.out.println("start login from user/categoryAdd - method : post");
		
		return "redirect:/mypage/categoryList";
	}
}
