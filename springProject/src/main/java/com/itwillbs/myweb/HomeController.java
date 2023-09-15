package com.itwillbs.myweb;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
//1) @Controller 스캔하고 자동으로 동작 => 주소매핑하는 파일
@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 2) @RequestMapping-> 3)주소를 자동을 뽑아서 value="/" 비교 , get방식일때
  //                  -> 메서드 자동으로 동작
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		// 로그출력 System.out.println();
//		logger.info("Welcome home! The client locale is {}.", locale);
//		// 자바 날짜 객체생성
//		Date date = new Date();
//		// 자바 날짜 -> 문자열 변경하는 객체생성
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		// 문자열 변경된 내용을 변수에 저장
//		String formattedDate = dateFormat.format(date);
//		
////		3) request.setAttribute("이름",값) ->  model 데이터를 담아서 jsp 이동
//		model.addAttribute("serverTime", formattedDate );
//		
		//4) WEB-INF/views/파일이름.jsp
		//WEB-INF/views/home.jsp => 이동
		return "redirect:/member/main";
	}//메서드
	

	
	
}//클래스 




