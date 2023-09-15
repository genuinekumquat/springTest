package com.itwillbs.dao;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberDTO;

@Repository
public class MemberDAO {
	// 멤버변수
	// 마이바티스 객체생성 -> root-context.xml 에서 객체생성 받아옴 (주입)
	
	@Inject
	private SqlSession sqlSession;
	
	// memberMapper.xml -> sql 전체이름을 변수애 저장
	private static final String namespace="com.itwillbs.mappers.memberMapper";  //너무길어서 다적지말고 변수로저장 
	
//	private DataSource dataSource;
//	private SimpleJdbcTemplate template;
	
	//set 메서드 
//	@Inject
//	public void setDataSource(DataSource dataSource) {
////		this.dataSource = dataSource;
//		template = new SimpleJdbcTemplate(dataSource);
//	}	
//	
	// insertMember() 메서드
//	String sql ="insert into members(id,pass,name,date) values(?,?,?,?)";
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO insertMember()");
		
		sqlSession.insert(namespace+".insertMember", memberDTO);
		
//		template.update(sql, memberDTO.getId(), memberDTO.getPass(), memberDTO.getName(), memberDTO.getDate());
	}	// insertMember() 

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberDAO userCheck()");
		
		return sqlSession.selectOne(namespace+".userCheck", memberDTO);
	}//userCheck

	public MemberDTO getMember(String id) {
		System.out.println("MemberDAO getMember()");
		return sqlSession.selectOne(namespace+".getMember", id);
	}//getMember

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO updateMember()");
		
		sqlSession.update(namespace+".updateMember", memberDTO);
	}//updateMember

	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO deleteMember()");
		
		sqlSession.delete(namespace+".deleteMember", memberDTO);
	}//deleteMember

}//
