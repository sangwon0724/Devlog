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
		
		/*String imgUploadPath = uploadPath + File.separator + "image";	//파일이 저장될 기본이 되는 폴더 . image라는 폴더에 저장됨.
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		
		if(file != null) {
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
		}else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}*/
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
	
	@RequestMapping(value="/image", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
		
		// 외부경로로 저장을 희망할때.
		//String fileRoot = "C:\\Users\\you\\Desktop\\My_Space\\GitHub\\practice_spring\\practice_spring\\src\\main\\webapp\\resources\\image\\";
		
		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot+"resources/image/";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
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
