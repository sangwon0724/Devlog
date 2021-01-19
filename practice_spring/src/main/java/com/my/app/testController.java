package com.my.app;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
	
	//write - get
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite() throws Exception {
		System.out.println("start /test/write.jsp - method : get");
	}
	
	//write - post, sql - insert
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(@RequestParam("testText") String testText) throws Exception {
		System.out.println("start /test/write.jsp - method : post");
		System.out.println("DB 값 넘기기");
		
		testVO vo=new testVO(testText);
		service.insertTest(vo);
		
		return "redirect:/";
	}
}
