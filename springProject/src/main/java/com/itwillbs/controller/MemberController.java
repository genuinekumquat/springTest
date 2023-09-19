package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@Controller
public class MemberController {
	// 할때마다 객체생성하지않고 멤버변수로 선언 
//	MemberService memberService = new MemberService();
	
	// 기존 자바형태로 해도되지만 스프링에서 수정을 최소하하는 방법을 고안해냄
	// new MemberService 객체생성을 스프링파일인 root-context.xml에서 객체생성하고 MemberService memberService에 전달 
	// top.jsp include 시켜서 쓰는것처럼 xml에서 객체생성해서 필요한곳에 전달해서 쓸수있게
	
	@Inject
	private MemberService memberService;  // 멤버변수위에 @Inject : MemberService 자동으로 찾겠다 
	
	// (root-context.xml에서 객체생성한) 외부에서 멤버변수의 값 전달
	//  스프링 3방식
	// 1) 생성자 통해서 전달
	// 2) set 메서드 통해서 전달
	
	//생성자만듬 
//	@Inject
//	public MemberController(MemberService memberService){
//		this.memberService = memberService;
//	}
	
//	// set메서드 만듬 
//	@Inject
//	public void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}

//	가상주소 http://localhost:8080/myweb/member/insert
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String memberInsert() {
		// member/insert.jsp
		// WEB-INF/views/member/insert.jsp
		return "member/insert";
	}//
	



	//	가상주소 http://localhost:8080/myweb/member/insert
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String memberInsertPro(MemberDTO memberDTO) {  // MemberDTO만 선언하면 자동으로 받아서 memberDTO에저장     
		System.out.println("MemberController memberInsertPro");
		// 회원가입 처리 : 화면에서입력한 정보를 받아오기  (원래했던 수동방식)
//		HttpServletRequest request :리퀘스트 받겠다 (함수부분에서 모든것을 받아서 가져올 수 있음)
//		request.setCharacterEncoding("utf-8");
//		String id = request.getParameter("id");
//		String pass = request.getParameter("pass");		
//		String name = request.getParameter("name");	
//		MemberDTO memberDTO = new MemberDTO();
//		memberDTO.setId(id);
//		memberDTO.setPass(pass);
//		memberDTO.setName(name);
		// 스프링에서 위에 과정은 전부 생략가능다
		
//		MemberDTO memberDTO 변수를 선언하면 자동으로 동작		
//		조건 -> form태그 name 이름, MemberDTO 멤버변수 이름이 일치해야함 
//		: 자동으로 request에서 값 가져와서 MemberDTO 멤버변슈 저장
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());

//		한글처리도 수동으로아니고 자동으로 할수다 web.xml에 해놨음 참고 
		
		// 자바형태로 객체생성 (기존에수동으로한거)
//		com.itwillbs.controller.MemberController 주소매핑 
//		-> com.itwillbs.service.MemberService 처리작업 
//		-> com.itwillbs.dao.MemberDAO DB작업	
//		MemberService 객체생성 
//		MemberService memberService = new MemberService();
//      insertMember(memberDTO) 메서드 호출 
		memberService.insertMember(memberDTO);
		
		// 로그인으로 이동 -> 주소 변경하면서 이동해야함 
		//response.sendRedirect()
		return "redirect:/member/login";
	}//	
	
//	가상주소 http://localhost:8080/myweb/member/login
//	-> member/login.jsp 이동
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String memberLogin() {
		// member/login.jsp
		// WEB-INF/views/member/login.jsp
		return "member/login";
	}//
	
//	가상주소 http://localhost:8080/myweb/member/loginPro POST방식 
//  -> 로그인 처리 -> 주소 변경되면서 이동 /member/main	
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String memberLoginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController memberLoginPro");
		// 로그인 처리 
		System.out.println("받은 아이디 : "+memberDTO.getId());
		System.out.println("받은 비밀번호 : "+memberDTO.getPass());
		
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		
		if(memberDTO2!=null) {
			// 아이디 비밀번호 일치 -> 세션값 생성 -> redirect:/member/main 이동
			session.setAttribute("id", memberDTO.getId());
			// 메인으로 이동 -> 주소 변경하면서 이동해야함 
			//response.sendRedirect()
			return "redirect:/member/main";
		} else {
			// 아이디 비밀번호 틀림 => member/msg.jsp
			return "member/msg";
		}
	}//

//	가상주소 http://localhost:8080/myweb/member/info 
//  주소변경없이 이동 member/info.jsp
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)//하이퍼링크는 전부 get방식 
	public String logout(HttpSession session) {
		// 로그아웃 
		session.invalidate();
		// WEB-INF/views/member/info.jsp
		return "redirect:/member/main";
	}//
	
//	가상주소 http://localhost:8080/myweb/member/info 
//  주소변경없이 이동 member/info.jsp
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)//하이퍼링크는 전부 get방식 
	public String memberInfo(HttpSession session, Model model) {
		// 세션값 가져오기 -> String id에 저장 
		String id = (String) session.getAttribute("id");
		// DB에 id에 대한 정보를 가져오기 
		MemberDTO memberDTO = memberService.getMember(id);
		// 가져온 데이터 담아서 info.jsp로 이동 
		//request.setAttribute("memberDTO",memberDTO);
		//request에 안담고 대신 Model model에 데이터를 담아서 info.jsp에 가서 바로 사용 
		model.addAttribute("memberDTO", memberDTO);
		// member/info.jsp
		// WEB-INF/views/member/info.jsp
		return "member/info";
	}//

//	가상주소 http://localhost:8080/myweb/member/update 
//  주소변경없이 이동 member/update.jsp
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String memberUpdate(HttpSession session, Model model) {
		// 수정하기전에 수정할정보 먼저 부려줌
		// 세션값 가져오기 -> String id에 저장 
		String id = (String) session.getAttribute("id");
		// DB에 id에 대한 정보를 가져오기 수정할정보 먼저 부려줌-> 새로만들필요없이 getMember쓰기
		MemberDTO memberDTO = memberService.getMember(id);	
		//request에 안담고 대신 Model model에 데이터를 담아서 info.jsp에 가서 바로 사용 
		model.addAttribute("memberDTO", memberDTO);		
		// member/update.jsp
		// WEB-INF/views/member/update.jsp
		return "member/update";
	}//memberUpdate

//	가상주소 http://localhost:8080/myweb/member/updatePro 
//  수정 -> 주소변경 이동 ->"redirect:/member/main";
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)//폼태그에서 넘어오는거는 post
	public String memberUpdatePro(MemberDTO memberDTO) {
		System.out.println("MemberController memberUpdatePro");
		// 회원정보수정 처리 
		// memberDTO toString 처리 -> memberDTO라는 주소값 출력하면 멤버변수 내용 나오도록 
		System.out.println(memberDTO); 
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO); // memberDTO2는 들고가는게아녀
		
		if(memberDTO2!=null) {
			// 아이디패스 일치하면 
		    // 멤버인포 -> 주소 변경하면서 이동해야함 
			memberService.updateMember(memberDTO);
		    //response.sendRedirect()
		 return "redirect:/member/main";			
		}else{
			// 아이디패스 일치안하면
			return "member/msg";			
		}
	}//	memberUpdatePro
	
//	가상주소 http://localhost:8080/myweb/member/delete 
//  주소변경없이 이동 member/delete.jsp
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String membeDelete() {
		// member/delete.jsp
		// WEB-INF/views/member/delete.jsp
		return "member/delete";
	}//
	
//	가상주소 http://localhost:8080/myweb/member/deletePro 
//  삭제 -> 주소변경 이동 ->"redirect:/member/main";
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)//폼태그에서 넘어오는거는 post
	public String memberDeletePro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController memberDeletePro");
		// 회원정보삭제 처리 
		// memberDTO 출력
		System.out.println(memberDTO);
		// MemberDTO memberDTO2 = userCheck() 호출
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		// memberDTO2 != null; -> 아이디패스 일치 -> 삭제(deleteMember(memberDTO)) -> 세션초기화 -> 메인으로 이동 
		if (memberDTO2!=null) {
			memberService.deleteMember(memberDTO);
			session.invalidate();
			System.out.println(memberDTO);
			// 멤버딜리트 -> 주소 변경하면서 이동해야함 
			//response.sendRedirect()
			return "redirect:/member/main";
		} else {
			// memberDTO2 == null; -> 아이디패스 불일치 -> member/msg.jsp 
			return "member/msg";	
		}	
	}//	memberDeletePro
	
//	가상주소 http://localhost:8080/myweb/member/list 
//  주소변경없이 이동 member/list.jsp
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)//하이퍼링크는 전부 get방식 
	public String memberList(HttpSession session,Model model) {
		// DB에 회원 정보를 가져오기 
		List<MemberDTO> memberList = memberService.getMemberList();
		// 가져온 데이터 담아서 list.jsp로 이동 
		//request.setAttribute("memberDTO",memberDTO);
		//request에 안담고 대신 Model model에 데이터를 담아서 list.jsp에 가서 바로 사용 
		model.addAttribute("memberList", memberList);
		// member/list.jsp
		// WEB-INF/views/member/list.jsp
		return "member/list";
	}//
	
//	가상주소 http://localhost:8080/myweb/member/main
//	-> member 폴더의 main.jsp 로 이동 	
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		return "member/main"; 
	}//
	
	
}//클래스
