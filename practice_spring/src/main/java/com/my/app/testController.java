package com.my.app;

import java.io.File;//파일 업로드
import java.io.IOException;//서머노트
import java.io.InputStream;//서머노트
import java.util.List;
import java.util.UUID;//서머노트

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;//서머노트

import org.apache.commons.io.FileUtils;//서머노트
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;//서머노트
import org.springframework.web.multipart.MultipartFile;//파일 업로드

import com.my.vo.testVO;
import com.google.gson.JsonObject;//서머노트
import com.my.service.testService;
import com.my.service.testServiceInterface;

@Controller
@RequestMapping(value = "/test")
public class testController {
	
	@Autowired
    private SqlSession sqlSession;
	
	@Inject
	private testServiceInterface service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//공부 요소 목록
	//1. @RequestMapping(value = "/test")처럼 method 구분이 없으면 method 구분없이 받는다.
	//2. @RequestParam의 경우에는 form에서 name의 값을 받아온다.
	//3. view나 delete같은 경우에는 vo로 받는게 아닌 @RequestParam나 @PathVariable으로 받는게 좋다.
	//4. @PathVariable는 write_{no}와 같은 경로로 매핑을 받을 때  @PathVariable int no처럼 인자를 받아서 사용한다.
	//5. 인자로 vo를 사용할 때에는 vo의 속성값들의 이름이 form 태그에서 쓰이는 각 요소들의 name과 일치해야 한다.
	
	//list-get
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		System.out.println("start list from test - method : get");
		
		List<testVO> testList = service.selectTest();
	    model.addAttribute("testList", testList);
	}
	
	//view-get
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("no") int no, Model model) throws Exception {
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
	public String postWrite(testVO vo) throws Exception {
		System.out.println("start write from test - method : post");
		System.out.println("DB 값 넘기기");
		
		service.insertTest(vo);
		
		return "redirect:/";
	}
	
	//update - get
	@RequestMapping(value = "/write_{no}", method = RequestMethod.GET)
	public String getUpdate(@PathVariable int no, Model model) throws Exception {
		System.out.println("start update from test - method : get");
		testVO vo = service.selectOneTest(no);
		model.addAttribute("testVO", vo);
		
		return "/test/write";
	}
		
	//update - post, sql - update
	@RequestMapping(value = "/write_{no}", method = RequestMethod.POST)
	public String postUpdate(testVO vo) throws Exception {
		System.out.println("start update from test - method : post");
		System.out.println("DB 값 넘기기");
		
		service.updateTest(vo);
			
		return "redirect:/test/list";
	}
	
	
	//delete
	@RequestMapping(value = "/delete_{no}", method = RequestMethod.GET)
	public String getDelete(@PathVariable int no) throws Exception {
		System.out.println("start delete from test- method : get");
		
		service.deleteTest(no);
			
		return "redirect:/test/list";
	}
	
	@RequestMapping(value="/image", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
		
		// 외부경로로 저장을 희망할때.
		//String realFileRoot = "C:\\Users\\you\\Desktop\\My_Space\\GitHub\\practice_spring\\practice_spring\\src\\main\\webapp\\resources\\image\\";
		
		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot+"resources/image/";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);
		//File targetFile = new File(realFileRoot+ savedFileName);//테스트	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/resources/image/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			//jsonObject.addProperty("url", fileRoot+savedFileName); 
			System.out.println(fileRoot+savedFileName);//경로 및 파일명 출력
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
}
