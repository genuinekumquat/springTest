package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberDTO;

@Service
public class MemberService {  // @Inject가 @Service을 찾아감  
	// 할때마다 객체생성하지않고 멤버변수로 선언 
//	MemberDAO memberDAO = new MemberDAO();  
	// 멤버변수 정의 
	@Inject
	private MemberDAO memberDAO;   
	
	// set메서드 
//	@Inject
//	public void setMemberDAO(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
	
	// insertMember() 메서드 
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberService insertMember()");
		
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
//		MemberDAO 객체생성 
//		MemberDAO memberDAO = new MemberDAO();
		
//      insertMember(memberDTO) 메서드 호출 
		memberDAO.insertMember(memberDTO);
	}//insertMember()

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberService userCheck()");
		
//		MemberDTO memberDTO2 = memberDAO.userChect(memberDTO);
		
		return memberDAO.userCheck(memberDTO);
	}//userCheck

	public MemberDTO getMember(String id) {
		System.out.println("MemberService getMember()");
		return memberDAO.getMember(id);
	}//getMember

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberService updateMember()");
		
		memberDAO.updateMember(memberDTO);
	}

	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberService deleteMember()");
		
		memberDAO.deleteMember(memberDTO);
	}// getMemberList

	public List<MemberDTO> getMemberList() {
		System.out.println("MemberService getMemberList()");
		
		return memberDAO.getMemberList();
	}//getMemberList

	

}//class
