package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardService {
	//BoardDAO 객페생성 
	@Inject
	private BoardDAO boardDAO;

	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardService insertBoard()");
		// name subject content => 폼
		// num readcount date => 처리
		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		if(boardDAO.getMaxNum() == null) {
			//글 없는 경우
			boardDTO.setNum(1); 
		}else {
			// 글 있는 경우 // max(num) +1
			boardDTO.setNum(boardDAO.getMaxNum()+1); 
		}
		
		boardDAO.insertBoard(boardDTO);
	}//insertBoard()

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardService getBoardList()");
		//시작하는 행번호
		int startRow=(pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1; 
		//끝나는 행번호
		int endRow = startRow+pageDTO.getPageSize()-1; 
		
		//DB작업 startRow -1
		pageDTO.setStartRow(startRow-1);
		pageDTO.setEndRow(endRow);
		
		
		return boardDAO.getBoardList(pageDTO);
	}//getBoardList

	public int getBoardCount() {
		System.out.println("BoardService getBoardCount()");

		return boardDAO.getBoardCount();
	}//getBoardCount

	public BoardDTO getBoard(int num) {
		System.out.println("BoardService getBoard()");		
		
		return boardDAO.getBoard(num);
	}//getBoard

	public void updateReadcount(int num) {
		System.out.println("BoardService updateReadcount()");	
		boardDAO.updateReadcount(num);
	}//updateReadcount

	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService updateBoard()");	
		
		boardDAO.updateBoard(boardDTO);
	}//updateBoard

	public void deleteBoard(BoardDTO boardDTO) {
		System.out.println("BoardService deleteBoard()");
		
		boardDAO.deleteBoard(boardDTO);		
	}//deleteBoard

}//클래스 
