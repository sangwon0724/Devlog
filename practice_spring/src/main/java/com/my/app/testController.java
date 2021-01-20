package com.my.app;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.vo.testVO;
import com.my.service.testService;
import com.my.service.testServiceInterface;

@Controller
@RequestMapping(value = "/test")//method 구분이 없으면 method 구분없이 받는다.
public class testController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private testServiceInterface service;
	
	//list-get
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		System.out.println("start list from test - method : get");
		
		List<testVO> testList = service.selectTest();
	    model.addAttribute("testList", testList);
	}
	
	//view-get
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("no") String no, Model model) throws Exception {
		System.out.println("start view from test - method : get");
			
		testVO vo = service.selectOneTest(no);
		model.addAttribute("testVO", vo);
	}
		
	//write - get
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite() throws Exception {
		System.out.println("start write from test - method : get");
	}
	
	//write - post, sql - insert
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(@RequestParam("testText") String testText) throws Exception {
		System.out.println("start write from test - method : post");
		System.out.println("DB 값 넘기기");
		
		testVO vo=new testVO(testText);
		service.insertTest(vo);
		
		return "redirect:/";
	}
	
	//update - get
	@RequestMapping(value = "/write_{no}", method = RequestMethod.GET)
	public String getUpdate(@PathVariable String no, Model model) throws Exception {
		System.out.println("start update from test - method : get");
		testVO vo = service.selectOneTest(no);
		model.addAttribute("testVO", vo);
		
		return "/test/write";
	}
		
	//update - post, sql - update
	@RequestMapping(value = "/write_{no}", method = RequestMethod.POST)
	public String postUpdate(@PathVariable String no, @RequestParam("testText") String testText) throws Exception {
		System.out.println("start update from test- method : post");
		System.out.println("DB 값 넘기기");
			
		testVO vo=new testVO(Integer.parseInt(no),testText);
		service.updateTest(vo);
			
		return "redirect:/";
	}
}
