package com.itwillbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

//	가상주소 http://localhost:8080/myweb/member/insert
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String memberInsert() {
		// member/insert.jsp
		// WEB-INF/views/member/insert.jsp
		return "member/insert";
	}//
	
//	가상주소 http://localhost:8080/myweb/member/login
//	-> member/login.jsp 이동
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String memberLogin() {
		// member/login.jsp
		// WEB-INF/views/member/login.jsp
		return "member/login";
	}//
	
//	가상주소 http://localhost:8080/myweb/member/main
//	-> member 폴더의 main.jsp 로 이동 	
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		return "member/main"; 
	}//
	
	
}//클래스
