package com.my.app;

import java.io.File;//파일 업로드
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.web.multipart.MultipartFile;//파일 업로드

import com.my.vo.testVO;
import com.my.service.testService;
import com.my.service.testServiceInterface;
import com.my.util.UploadFileUtils;

@Controller
@RequestMapping(value = "/test")//method 구분이 없으면 method 구분없이 받는다.
public class testController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private testServiceInterface service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
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
	public String postWrite(@RequestParam("testText") String testText, @RequestParam("summernote") String note, MultipartFile file) throws Exception {
		System.out.println("start write from test - method : post");
		System.out.println("DB 값 넘기기");
		
		String imgUploadPath = uploadPath + File.separator + "image";	//파일이 저장될 기본이 되는 폴더 . image라는 폴더에 저장됨.
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		
		if(file != null) {
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
		}else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		
		testVO vo=new testVO(testText,note);
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
	public String postUpdate(@PathVariable String no, @RequestParam("testText") String testText, @RequestParam("summernote") String note) throws Exception {
		System.out.println("start update from test- method : post");
		System.out.println("DB 값 넘기기");
			
		testVO vo=new testVO(Integer.parseInt(no),testText,note);
		service.updateTest(vo);
			
		return "redirect:/";
	}
	
	
	//delete
	@RequestMapping(value = "/delete_{no}", method = RequestMethod.GET)
	public String getDelete(@PathVariable String no) throws Exception {
		System.out.println("start delete from test- method : get");
			
		testVO vo=new testVO(Integer.parseInt(no));
		service.deleteTest(vo);
			
		return "redirect:/test/list";
	}
}
