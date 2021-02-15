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
	//4. get 방식일때 return을 안 해줘도 model.addAttribute로 값이 들어가니 자료형을 void로 해줘도 된다. (단, redirect가 아닌 경우)
	
	//login-get
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getLogin() throws Exception {
		System.out.println("start login - method : get");
	}
		
	//login-post, 세션 부재시 로그인으로 유도
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(userVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start login - method : post");
				
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
		System.out.println("start logout - method : get");
		
		userService.logout(session);
	   
		return "redirect:/";
	}
	
	//mypage-get, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/mypage", method = RequestMethod.GET)
	public void getMypage(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start user/mypage - method : get");
	}
	
	//changePW-get, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.GET)
	public void getChangePW(Model model) throws Exception {
		System.out.println("start user/changePW - method : get");
		
		model.addAttribute("myTask", "changePW");
	}
	
	//changePW-post, sql - update, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/changePW", method = RequestMethod.POST)
	public String postChangePW(userVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		System.out.println("start user/changePW - method : post");
		
		HttpSession session = request.getSession();
		userVO tempUser=(userVO)session.getAttribute("user");
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
	
	//categoryList-get, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/categoryList", method = RequestMethod.GET)
	public void getCategoryList(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start user/categoryList - method : get");
		
		model.addAttribute("myTask", "categoryList");
	}
	
	//categoryView-get, iframe용, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/categoryView", method = RequestMethod.GET)
	public void getCategoryView(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start user/categoryView - method : get");
		
		HttpSession session = request.getSession();
		userVO temp=(userVO)session.getAttribute("user");
		
		List<categoryVO> categoryList=categoryService.selectCategory(temp.getId());
		model.addAttribute("categoryList", categoryList);
	}
	
	//categoryView-post
	@RequestMapping(value = "/mypage/categoryView", method = RequestMethod.POST)
	public String postCategoryView(HttpServletRequest request, @RequestParam("category_function") String category_function,
			@RequestParam("orderNo") int orderNo, @RequestParam(value="categoryName",defaultValue="") String categoryName) throws Exception {
		System.out.println(category_function);
		System.out.println(orderNo);
		System.out.println(categoryName);
		System.out.println("start  user/categoryView - method : post");
		
		HttpSession session = request.getSession();
		userVO tempUser=(userVO)session.getAttribute("user");
		categoryVO tempCategory = new categoryVO();
		tempCategory.setOrderNo(orderNo);
		tempCategory.setUserID(tempUser.getId());
		
		if(category_function.equals("moveUp")) {
			System.out.println(tempCategory.getOrderNo());
			System.out.println(tempCategory.getUserID());
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
	
	//categoryAdd-get, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.GET)
	public void getCategoryAdd(HttpServletRequest request, RedirectAttributes rttr, Model model) throws Exception {
		System.out.println("start  user/categoryAdd - method : get");
		
		model.addAttribute("myTask", "categoryAdd");
	}
	
	//categoryAdd-post, sql - insert, com.my.util.urlInterceptor 적용
	@RequestMapping(value = "/mypage/categoryAdd", method = RequestMethod.POST)
	public String postCategoryAdd(HttpServletRequest request, RedirectAttributes rttr, categoryVO vo) throws Exception {
		System.out.println("start user/categoryAdd - method : post");
		
		HttpSession session = request.getSession();
		userVO tempUser=(userVO)session.getAttribute("user");

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
	
	//카테고리 삭제 재확인
	@RequestMapping(value = "/mypage/checkDelete", method = RequestMethod.GET)
	public void getCheckDelete() throws Exception {
		System.out.println("start user/categoryAdd - method : get");
	}
}
