package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class SelectBoardAllServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 서비스 객체 생성하기 
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		//2. 회원정보 조회
		List<BoardVO> boardList = boardService.AllBoardList();
		
		req.setAttribute("boardList", boardList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/boardList.jsp");
		
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
