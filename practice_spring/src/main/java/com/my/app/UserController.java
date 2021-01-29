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

import com.my.service.boardService;
import com.my.service.boardServiceInterface;
import com.my.service.categoryServiceInterface;
import com.my.service.userServiceInterface;
import com.my.vo.boardVO;
import com.my.vo.categoryVO;
import com.my.vo.userVO;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private userServiceInterface userService;
	
	@Inject
	private categoryServiceInterface categoryService;
	
	@Inject
	private boardServiceInterface boardService;
	
	//==========할 일 목록==========
	
	//==========완료 목록=======
	//1. 로그인 기능 구현
	//2. 직접 경로로 로그인 필요 서비스에 이동 시 로그인 페이지로 유도 => url이 직접 노출되는 일부의 get 방식의 함수에 적용 (login이나 logout같은 url이 보여도 크게 상관없으면 적용 안함)
	//3. 비밀번호 변경 구현
	//4. 카테고리 추가 기능
	//5. 카테고리 순서 변경 기능
	//6. 카테고리 삭제 기능
	//7. 카테고리 삭제시 관련 게시글 삭제 (분류값이랑 아이디값 받아서 처리)
	
	//==========공부 요소 목록=======
	//1. redirect 시킬 때는 RedirectAttributes의 addFlashAttribute로 인자를 전달한다.
	//2. rediect가 아닐 때는 Model의 addAttribute로 인자를 전달한다.
	//3. 1과 2의 방법 모두 url에 인자가 표시되지 않는다.
	
	//login-get
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getLogin() throws Exception {
		System.out.println("start login from user - method : get");
	}
		
	//login-post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(userVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user - method : post");
				
		userVO result=userService.selectLogin(vo);
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
		
		userService.logout(session);
	   
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
	public String getChangePW(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start login from user/changePW - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		model.addAttribute("myTask", "changePW");
		return "/user/mypage/changePW";
	}
	
	//changePW-post, sql - update, 세션 부재시 login으로 유도
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.POST)
	public String postChangePW(userVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login from user/changePW - method : post");
		
		HttpSession session = request.getSession();
		userVO tempUser=(userVO)session.getAttribute("user");
		if(tempUser==null) {
			rttr.addFlashAttribute("result", "noSession");
			return "redirect:/user/login";
		}
		
		vo.setId(tempUser.getId());

		int result=userService.updatePW(vo);
		
		//실패한 경우
		if(result==0) {
			rttr.addFlashAttribute("result", "fail");
			return "redirect:/user/mypage/changePW";
		}
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/user/mypage/changePW";
	}
	
	//categoryList-get, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryList", method = RequestMethod.GET)
	public String getCategoryList(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start login from user/categoryList - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		model.addAttribute("myTask", "categoryList");
		return "/user/mypage/categoryList";
	}
	
	//categoryView-get, iframe용, 직접 주소 이동시 메인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryView", method = RequestMethod.GET)
	public String getCategoryView(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start login from user/categoryView - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "onlyManager");
			return "redirect:/";
		}
		userVO temp=(userVO)session.getAttribute("user");
		
		List<categoryVO> categoryList=categoryService.selectCategory(temp.getId());
		model.addAttribute("categoryList", categoryList);
		
		return "/user/mypage/categoryView";
	}
	
	//categoryView-post
	@RequestMapping(value = "/mypage/categoryView", method = RequestMethod.POST)
	public String postCategoryView(HttpServletRequest request, @RequestParam("category_function") String category_function,
			@RequestParam("orderNo") int orderNo, @RequestParam("categoryName") String categoryName) throws Exception {
		System.out.println("start login from user/categoryView - method : post");
		
		HttpSession session = request.getSession();
		userVO tempUser=(userVO)session.getAttribute("user");
		categoryVO tempCategory = new categoryVO();
		tempCategory.setOrderNo(orderNo);
		tempCategory.setUserID(tempUser.getId());
		
		if(category_function.equals("moveUp")) {
			categoryService.moveUp(tempCategory);
		}
		if(category_function.equals("moveDown")) {
			categoryService.moveDown(tempCategory);
		}
		if(category_function.equals("deleteList")) {
			boardVO tempBoard = new boardVO();
			tempBoard.setCategory(categoryName);
			tempBoard.setUserID(tempUser.getId());
			
			categoryService.deleteList(tempCategory);//카테고리 테이블에서 해당 카테고리 삭제
			boardService.deleteCategory(tempBoard);//게시판 테이블에서 해당 카테고리를 가지고 있는 게시글 삭제
		}
		
		return "redirect:/user/mypage/categoryView";
	}
	
	//categoryAdd-get, 직접 주소 이동시 로그인 페이지로 유도
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.GET)
	public String getCategoryAdd(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start login from user/categoryAdd - method : get");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			rttr.addFlashAttribute("result", "need");
			return "redirect:/user/login";
		}
		
		model.addAttribute("myTask", "categoryAdd");
		return "/user/mypage/categoryAdd";
	}
	
	//categoryAdd-post, sql - insert, 세션 부재시 login으로 유도
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.POST)
	public String postCategoryAdd(HttpServletRequest request, RedirectAttributes rttr, categoryVO vo) throws Exception {
		System.out.println("start login from user/categoryAdd - method : post");
		
		HttpSession session = request.getSession();
		userVO tempUser=(userVO)session.getAttribute("user");

		//세션 존재 여부 확인
		if(tempUser==null) {
			rttr.addFlashAttribute("result", "noSession");
			return "redirect:/user/login";
		}
		
		//기존 분류명 중에 존재하는가 확인
		vo.setUserID(tempUser.getId());
		String overlapTest=categoryService.selectCategoryOne(vo);
		if(overlapTest.equals("overlap")) {
			rttr.addFlashAttribute("result", "overlap");
			return "redirect:/user/mypage/categoryAdd";
		}
		
		//추가하기
		categoryService.insertCategory(vo);
		return "redirect:/user/mypage/categoryList";
	}
}
