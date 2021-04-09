package kr.or.ddit.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class InsertBoardServlet extends HttpServlet{
	
	/**
	 * RequestDispatcher : 클라이언트로부터 요청받은 정보를 서버의 다른 자원(HTML, JSP, Servlet 등)에 보내는 역할을 하는 인터페이스
	 * 
	 * ServletContext에서 제공되는 getRequestDispatcher() 팩토리 메서드 
	 * ---->> 요청이 재지정 될 대상에 대한 정보를 path형식으로 지정
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/insertBoardForm.jsp");
		dispatcher.forward(req, resp);
	//forward() 메소드 : 클라이언트에서 수행요청을 받는 Servlet에서 다른 Servlet, JSP 또는 HTML 파일등
	//					수행 권한을 넘겨서 다른 자원의 수행 결과가 대신 클라이언트로 응답하는 기능을 지원
	// 
	}
	
	@Override  //board_seq.nextval, #board_title#, #board_writer#, sysdate, #board_content#
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//board_no, board_title, board_writer, board_date, board_content
		//1. 요청파라미터 정보 가져오기 
		String boardNo = req.getParameter("board_no");
		String boardTitle = req.getParameter("board_title");
		String boardWriter = req.getParameter("board_writer");
		String boardDate = req.getParameter("board_date");
		String boardContent = req.getParameter("board_content");
		
		//2. 서비스 객체 생성하기
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		//3. 회원정보 등록하기
		BoardVO bv = new BoardVO();
		bv.setBoard_no(boardNo);
		bv.setBoard_title(boardTitle);
		bv.setBoard_writer(boardWriter);
		bv.setBoard_date(boardDate);
		bv.setBoard_content(boardContent);
		
		int cnt = boardService.insertBoard(bv);
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//4.목록 조회화면으로 이동 Redirect
		String redirectUrl = req.getContextPath() + "/board/boardList.do?msg=" + URLEncoder.encode(msg, "UTF-8");
		resp.sendRedirect(redirectUrl);
	}
}
