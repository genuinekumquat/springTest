package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Repository
public class BoardDAO {
	// 마이바티스 객체생성	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwillbs.mappers.boardMapper";

	public Integer getMaxNum() {
		System.out.println("BoardDAO getMaxNum()");
		
		return sqlSession.selectOne(namespace+".getMaxNum");
	}//getMaxNum

	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO insertBoard()");
		
		sqlSession.insert(namespace+".insertBoard",boardDTO);
	}//insertBoard

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardDAO getBoardList()");		
		
		return sqlSession.selectList(namespace+".getBoardList",pageDTO);
	}//getBoardList

	public int getBoardCount() {
		System.out.println("BoardDAO getBoardCount()");
		
		return sqlSession.selectOne(namespace+".getBoardCount");
	}//getBoardCount
	
	
	public BoardDTO getBoard(int num) {
		System.out.println("BoardDAO getBoard()");
		
		return sqlSession.selectOne(namespace+".getBoard", num);
	}//getBoardCount

	public void updateReadcount(int num) {
		System.out.println("BoardDAO updateReadcount()");
		
		sqlSession.update(namespace+".updateReadcount", num);
	}//updateReadcount

	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO updateBoard()");
		
		sqlSession.update(namespace+".updateBoard", boardDTO);
	}//updateBoard

	public void deleteBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO deleteBoard()");
		
		sqlSession.delete(namespace+".deleteBoard",boardDTO);
	}//deleteBoard

}//클래스
