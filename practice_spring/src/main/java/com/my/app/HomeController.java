package com.my.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.List;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.service.categoryServiceInterface;
import com.my.vo.categoryVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	 @Autowired
	 private categoryServiceInterface categoryService;
	 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//메뉴용
		List<categoryVO> categoryList = categoryService.selectCategoryList();
	    model.addAttribute("categoryList", categoryList);
	    
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/include/boardList", method = RequestMethod.GET)
	public String getBoardList(Model model) throws Exception {
		//메뉴용
		List<categoryVO> categoryList = categoryService.selectCategoryList();
	    model.addAttribute("categoryList", categoryList);
	    
	    return "/include/boardList";
	}
	
}
