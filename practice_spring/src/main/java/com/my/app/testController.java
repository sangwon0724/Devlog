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
@RequestMapping(value = "/test", method = RequestMethod.GET)
public class testController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private testServiceInterface service;
	
	//write
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String getWrite() throws Exception {
		
		return "/test/write";
	}
	
	//writeDB
	@RequestMapping(value = "/writeDB", method = RequestMethod.POST)
	public String getWriteDB(@RequestParam("test") String test) throws Exception {
		System.out.println("확인점 1-1 : "+test);//성공
		testVO vo=new testVO(test);
		//System.out.println("확인점 1-2 : "+vo.getTest());//성공
		System.out.println("확인점 1-2 : "+vo);//성공
		service.insertTest(vo);
		
		return "redirect:/test/write";
	}
}
