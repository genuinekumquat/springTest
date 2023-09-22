package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.MemberDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
//@RequestMapping("/board/*")
public class BoardController {
	
	//BoardService 객체생성 
	@Inject
	private BoardService boardService;

//	가상주소 http://localhost:8080/myweb/board/write
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		// board/write.jsp
		// WEB-INF/views/board/write.jsp
		return "board/write";
	}//

//	가상주소 http://localhost:8080/myweb/board/writePro
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		System.out.println(boardDTO);
		// DB에 글쓰기
		boardService.insertBoard(boardDTO);
		
		// 글목록 주소변경하면서 이동 /board/list
		return "redirect:/board/list";
	}//	
	
//	가상주소 http://localhost:8080/myweb/board/list
//	가상주소 http://localhost:8080/myweb/board/list?pageNum=2	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		
		int pageSize = 3;
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int currentPage=Integer.parseInt(pageNum);
		
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		
		int count = boardService.getBoardCount();
		int pageBlock =  3; // 페이지개수 
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage + pageBlock - 1;
		int pageCount = count/pageSize + (count%pageSize==0?0:1);
		if(endPage > pageCount) {
			endPage =  pageCount;
		}
		
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		model.addAttribute("boardList", boardList); // 키,값을 넘김 
		model.addAttribute("pageDTO", pageDTO);
		
		// board/list.jsp
		// WEB-INF/views/board/list.jsp
		return "board/list";
	}//
	
	//http://localhost:8080/myweb/board/content?num=num
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)//하이퍼링크는 전부 get방식 
//	public String boardContent(HttpServletRequest request, Model model) {
	public String Content(BoardDTO boardDTO, Model model) {
		
//		int num = Integer.parseInt(request.getParameter("num")); 
//		BoardDTO boardDTO = boardService.getBoard(num);
		
		// num값 게시물 조회수증가
		boardService.updateReadcount(boardDTO.getNum());
		
		// num값 게시물 가져오기
		boardDTO = boardService.getBoard(boardDTO.getNum());
		
		model.addAttribute("boardDTO", boardDTO);
		// board/content.jsp	
		// WEB-INF/views/board/content.jsp
		return "board/content";
	}//

	//http://localhost:8080/myweb/board/update?num=num
	@RequestMapping(value = "/board/update", method = RequestMethod.GET) 
//	public String boardUpdate(HttpServletRequest request, Model model) {
	public String Update(BoardDTO boardDTO, Model model) {
				
		// num값 게시물 가져오기
		boardDTO = boardService.getBoard(boardDTO.getNum());
		
		model.addAttribute("boardDTO", boardDTO);
		// board/content.jsp	
		// WEB-INF/views/board/update.jsp
		return "board/update";
	}//	
	
	//	http://localhost:8080/myweb/board/updatePro    POST방식
	@RequestMapping(value = "/board/updatePro", method = RequestMethod.POST)
	public String UpdatePro(BoardDTO boardDTO) {
		// request => num name subject content 저장되어 있음 =>BoardDTO
		System.out.println("BoardController updatePro()");
		System.out.println(boardDTO);
		// num값 게시물 수정
		boardService.updateBoard(boardDTO);
		
		// updateBoard(boardDTO) 수정
		// redirect:/board/list 이동	
		return "redirect:/board/list";	
	}// 
	
	//	http://localhost:8080/myweb/board/delete?num=num
//	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	@GetMapping("/board/delete")// request get 방식일때 겟매핑 바로해도된다
	public String delete(BoardDTO boardDTO) {
		System.out.println(boardDTO.getNum());

		boardService.deleteBoard(boardDTO);
		
		return "redirect:/board/list";	
	}//	
		
}// 클래스 
